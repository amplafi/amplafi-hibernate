// $Id: DummyNamingStrategy.java 19925 2010-07-09 17:52:47Z steve.ebersole@jboss.com $
package org.hibernate.test.annotations.namingstrategy;

import org.hibernate.cfg.EJB3NamingStrategy;

@SuppressWarnings("serial")
public class DummyNamingStrategy extends EJB3NamingStrategy {
	
	public String tableName(String tableName) {
		return "T" + tableName;
	}

}
