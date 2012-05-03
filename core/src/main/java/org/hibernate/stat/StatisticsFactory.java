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
package org.hibernate.stat;

import org.hibernate.cfg.Configuration;
import org.hibernate.engine.SessionFactoryImplementor;
import org.hibernate.engine.SessionImplementor;

/**
 * Implementors are passed to {@link org.hibernate.cfg.Configuration}s. Configuration passes the 
 * StatisticsFactory to a {@link SessionFactoryImplementor} which uses the StatisticsFactory to create the
 * StatisticsImplementor.
 * 
 * Implementors must be serializable if {@link org.hibernate.cfg.Configuration} will be serialized.
 * @author Patrick Moore
 *
 */
public interface StatisticsFactory {
	/**
	 * create the StatisticsImplementor.
	 * @param sessionFactoryImplementor
	 * @param configuration
	 * @return must not be null.
	 */
    public StatisticsImplementor createStatisticsImplementor(SessionFactoryImplementor sessionFactoryImplementor, Configuration configuration);
    public SessionStatistics createSessionStatistics(StatisticsImplementor statistics, SessionImplementor sessionImplementor);
    
    /**
     * 
     * @return the value the factory supplies to the created {@link StatisticsImplementor}'s {@link StatisticsImplementor#setStatisticsEnabled(boolean)}.
     */
    public boolean isDefaultStatisticsEnabled();
    public void setDefaultStatisticsEnabled(boolean defaultStatisticsEnabled);
}
