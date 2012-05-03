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

/**
 * Query statistics (HQL and SQL)
 * <p/>
 * Note that for a cached query, the cache miss is equals to the db count
 *
 * @author Gavin King
 * @author Patrick Moore
 * @author Alex Snaps
 */
public interface QueryStatistics extends CategorizedStats {
	/**
	 * @return queries executed to the DB
	 */
	long getExecutionCount();

	/**
	 * @return Queries retrieved successfully from the cache
	 */
	long getCacheHitCount();

	long getCachePutCount();

	long getCacheMissCount();

	/**
	 * Number of lines returned by all the executions of this query (from DB)
	 * For now, {@link org.hibernate.Query#iterate()}
	 * and {@link org.hibernate.Query#scroll()} do not fill this statistic
	 *
	 * @return The number of rows cumulatively returned by the given query; iterate
	 * and scroll queries do not effect this total as their number of returned rows
	 * is not known at execution time.
	 */
	long getExecutionRowCount();
	/**
	 * @return average time in ms taken by the execution of this query onto the DB
	 */
	long getExecutionAvgTime();
	/**
	 * @return max time in ms taken by the execution of this query onto the DB
	 */
	long getExecutionMaxTime() ;

	/**
	 * @return min time in ms taken by the excution of this query onto the DB
	 */
	long getExecutionMinTime();

	/**
	 * add statistics report of a DB query
	 *
	 * @param rows rows count returned
	 * @param time time taken
	 */
	void executed(long rows, long time);
	/**
	 *
	 */
	long incrCachePutCount();
	/**
	 *
	 */
	long incrCacheMissCount();

	/**
	 *
	 */
	long incrCacheHitCount();

}
