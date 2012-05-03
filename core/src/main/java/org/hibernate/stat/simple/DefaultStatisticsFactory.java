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

import java.io.Serializable;

import org.hibernate.cfg.Configuration;
import org.hibernate.engine.SessionFactoryImplementor;
import org.hibernate.engine.SessionImplementor;
import org.hibernate.stat.SessionStatistics;
import org.hibernate.stat.StatisticsFactory;
import org.hibernate.stat.StatisticsImplementor;

/**
 * Default implementation of {@link StatisticsFactory} which supplies a {@link StatisticsImpl}.
 * StatisticsImpl is synchronized on itself and can result in poor production behavior. 
 * @author patmoore
 *
 */
public class DefaultStatisticsFactory implements StatisticsFactory, Serializable {

	private boolean defaultStatisticsEnabled;
    /**
     * @see org.hibernate.stat.StatisticsFactory#createStatisticsImplementor(SessionFactoryImplementor, Configuration)
     */
    public StatisticsImplementor createStatisticsImplementor(SessionFactoryImplementor sessionFactoryImplementor, Configuration configuration) {
    	StatisticsImplementor statisticsImplementor = new StatisticsImpl(sessionFactoryImplementor, this);
    	statisticsImplementor.setStatisticsEnabled(isDefaultStatisticsEnabled());
    	return statisticsImplementor;
    }

	/**
	 * @see org.hibernate.stat.StatisticsFactory#createSessionStatistics(org.hibernate.stat.StatisticsImplementor, org.hibernate.engine.SessionImplementor)
	 */
	public SessionStatistics createSessionStatistics(StatisticsImplementor statistics, SessionImplementor sessionImplementor) {
		return new SessionStatisticsImpl(sessionImplementor);
	}

	/**
	 * @param defaultStatisticsEnabled the defaultStatisticsEnabled to set
	 */
	public void setDefaultStatisticsEnabled(boolean defaultStatisticsEnabled) {
		this.defaultStatisticsEnabled = defaultStatisticsEnabled;
	}

	/**
	 * @return the defaultStatisticsEnabled
	 */
	public boolean isDefaultStatisticsEnabled() {
		return defaultStatisticsEnabled;
	}

}
