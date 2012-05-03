package org.hibernate.envers.strategy;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.envers.RevisionType;
import org.hibernate.envers.configuration.AuditConfiguration;
import org.hibernate.envers.configuration.AuditEntitiesConfiguration;
import org.hibernate.envers.configuration.GlobalConfiguration;
import org.hibernate.envers.entities.mapper.PersistentCollectionChangeData;
import org.hibernate.envers.entities.mapper.id.IdMapper;
import org.hibernate.envers.entities.mapper.relation.MiddleComponentData;
import org.hibernate.envers.entities.mapper.relation.MiddleIdData;
import org.hibernate.envers.tools.query.Parameters;
import org.hibernate.envers.tools.query.QueryBuilder;

/**
 * Audit strategy which additionally manages the end-revision number: updates the end-revision field on the last
 * revision that was persisted before the current one.
 * 
 * @author Stephanie Pau
 * @author Adam Warski (adam at warski dot org)
 */
public class ValidTimeAuditStrategy implements AuditStrategy {
    public void perform(Session session, String entityName, AuditConfiguration auditCfg, Serializable id, Object data,
                        Object revision) {
        AuditEntitiesConfiguration audEntCfg = auditCfg.getAuditEntCfg();
        String auditedEntityName = audEntCfg.getAuditEntityName(entityName);

        // Update the end date of the previous row if this operation is expected to have a previous row
        if (getRevisionType(auditCfg, data) != RevisionType.ADD) {
            /*
             Constructing a query:
             select e from audited_ent e where e.end_rev is null and e.id = :id
             */

            QueryBuilder qb = new QueryBuilder(auditedEntityName, "e");

            // e.id = :id
            IdMapper idMapper = auditCfg.getEntCfg().get(entityName).getIdMapper();
            idMapper.addIdEqualsToQuery(qb.getRootParameters(), id, auditCfg.getAuditEntCfg().getOriginalIdPropName(), true);

            updateLastRevision(session, auditCfg, qb, id, auditedEntityName, revision);
        }

        // Save the audit data
        session.save(auditedEntityName, data);
    }

    @SuppressWarnings({"unchecked"})
    public void performCollectionChange(Session session, AuditConfiguration auditCfg,
                                        PersistentCollectionChangeData persistentCollectionChangeData, Object revision) {
        // Update the end date of the previous row if this operation is expected to have a previous row
        if (getRevisionType(auditCfg, persistentCollectionChangeData.getData()) != RevisionType.ADD) {
            /*
             Constructing a query (there are multiple id fields):
             select e from audited_middle_ent e where e.end_rev is null and e.id1 = :id1 and e.id2 = :id2 ...
             */

            QueryBuilder qb = new QueryBuilder(persistentCollectionChangeData.getEntityName(), "e");

            // Adding a parameter for each id component, except the rev number
            String originalIdPropName = auditCfg.getAuditEntCfg().getOriginalIdPropName();
            Map<String, Object> originalId = (Map<String, Object>) persistentCollectionChangeData.getData().get(
                    originalIdPropName);
            for (Map.Entry<String, Object> originalIdEntry : originalId.entrySet()) {
                if (!auditCfg.getAuditEntCfg().getRevisionFieldName().equals(originalIdEntry.getKey())) {
                    qb.getRootParameters().addWhereWithParam(originalIdPropName + "." + originalIdEntry.getKey(),
                            true, "=", originalIdEntry.getValue());
                }
            }

            updateLastRevision(session, auditCfg, qb, originalId, persistentCollectionChangeData.getEntityName(), revision);
        }

        // Save the audit data
        session.save(persistentCollectionChangeData.getEntityName(), persistentCollectionChangeData.getData());
    }

	public void addEntityAtRevisionRestriction(GlobalConfiguration globalCfg, QueryBuilder rootQueryBuilder,
			String revisionProperty,String revisionEndProperty, boolean addAlias,
            MiddleIdData idData, String revisionPropertyPath, String originalIdPropertyName,
            String alias1, String alias2) {
		Parameters rootParameters = rootQueryBuilder.getRootParameters();
		addRevisionRestriction(rootParameters, revisionProperty, revisionEndProperty, addAlias);
	}
	
	public void addAssociationAtRevisionRestriction(QueryBuilder rootQueryBuilder,  String revisionProperty, 
		    String revisionEndProperty, boolean addAlias, MiddleIdData referencingIdData, 
		    String versionsMiddleEntityName, String eeOriginalIdPropertyPath, String revisionPropertyPath,
		    String originalIdPropertyName, MiddleComponentData... componentDatas) {
		Parameters rootParameters = rootQueryBuilder.getRootParameters();
		addRevisionRestriction(rootParameters, revisionProperty, revisionEndProperty, addAlias);
	}
    
    private void addRevisionRestriction(Parameters rootParameters,  
			String revisionProperty, String revisionEndProperty, boolean addAlias) {
    	
		// e.revision <= _revision and (e.endRevision > _revision or e.endRevision is null)
		Parameters subParm = rootParameters.addSubParameters("or");
		rootParameters.addWhereWithNamedParam(revisionProperty, addAlias, "<=", "revision");
		subParm.addWhereWithNamedParam(revisionEndProperty + ".id", addAlias, ">", "revision");
		subParm.addWhere(revisionEndProperty, addAlias, "is", "null", false);
	}

    @SuppressWarnings({"unchecked"})
    private RevisionType getRevisionType(AuditConfiguration auditCfg, Object data) {
        return (RevisionType) ((Map<String, Object>) data).get(auditCfg.getAuditEntCfg().getRevisionTypePropName());
    }

    @SuppressWarnings({"unchecked"})
    private void updateLastRevision(Session session, AuditConfiguration auditCfg, QueryBuilder qb,
                                    Object id, String auditedEntityName, Object revision) {
        String revisionEndFieldName = auditCfg.getAuditEntCfg().getRevisionEndFieldName();

        // e.end_rev is null
        qb.getRootParameters().addWhere(revisionEndFieldName, true, "is", "null", false);

        List l = qb.toQuery(session).list();

        // There should be one entry
        if (l.size() == 1) {
            // Setting the end revision to be the current rev
            Object previousData = l.get(0);
            ((Map<String, Object>) previousData).put(revisionEndFieldName, revision);

            // Saving the previous version
            session.save(auditedEntityName, previousData);
        } else {
            throw new RuntimeException("Cannot find previous revision for entity " + auditedEntityName + " and id " + id);
        }
    }
}
