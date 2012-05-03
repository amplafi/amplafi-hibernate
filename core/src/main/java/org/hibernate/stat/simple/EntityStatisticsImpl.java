/*
 * Hibernate, Relational Persistence for Idiomatic Java
 *
 * Copyright (c) 2008, Red Hat Middleware LLC or third-party contributors as
 * indicated by the @author tags or express copyright attribution
 * statements applied by the authors.  All third-party contributions are
 * distributed under license by Red Hat Middleware LLC.
 *
 * This copyrighted material is made available to anyone wishing to use, modify,
 * copy, or redistribute it subject to the terms and conditions of the GNU
 * Lesser General Public License, as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License
 * for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this distribution; if not, write to:
 * Free Software Foundation, Inc.
 * 51 Franklin Street, Fifth Floor
 * Boston, MA  02110-1301  USA
 *
 */
package org.hibernate.stat.simple;

import org.hibernate.stat.EntityStatistics;



/**
 * Entity related statistics
 *
 * @author Gavin King
 * @author Patrick Moore
 */
public class EntityStatisticsImpl extends CategorizedStatistics implements EntityStatistics {

	public EntityStatisticsImpl(String name) {
		super(name);
	}

	private long loadCount;
	private long updateCount;
	private long insertCount;
	private long deleteCount;
	private long fetchCount;
	private long optimisticFailureCount;

	public synchronized long getDeleteCount() {
		return deleteCount;
	}
	public synchronized long incrDeleteCount() {
		return ++deleteCount;
	}
	public synchronized long getInsertCount() {
		return insertCount;
	}
	public synchronized long incrInsertCount() {
		return ++insertCount;
	}
	public synchronized long getLoadCount() {
		return loadCount;
	}
	public synchronized long incrLoadCount() {
		return ++loadCount;
	}
	public synchronized long getUpdateCount() {
		return updateCount;
	}
	public synchronized long incrUpdateCount() {
		return ++updateCount;
	}
	public synchronized long getFetchCount() {
		return fetchCount;
	}
	public synchronized long incrFetchCount() {
		return ++fetchCount;
	}
	public synchronized long getOptimisticFailureCount() {
		return optimisticFailureCount;
	}
	public synchronized long incrOptimisticFailureCount() {
		return ++optimisticFailureCount;
	}

	public String toString() {
		return new StringBuffer()
		    .append("EntityStatistics")
			.append("[loadCount=").append(this.loadCount)
			.append(",updateCount=").append(this.updateCount)
			.append(",insertCount=").append(this.insertCount)
			.append(",deleteCount=").append(this.deleteCount)
			.append(",fetchCount=").append(this.fetchCount)
			.append(",optimisticLockFailureCount=").append(this.optimisticFailureCount)
			.append(']')
			.toString();
	}

}
