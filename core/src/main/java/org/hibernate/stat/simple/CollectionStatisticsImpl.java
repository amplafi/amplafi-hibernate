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

import org.hibernate.stat.CollectionStatistics;

/**
 * Collection related statistics
 *
 * @author Gavin King
 */
public class CollectionStatisticsImpl extends CategorizedStatistics implements CollectionStatistics {

	private long loadCount;
	private long fetchCount;
	private long updateCount;
	private long removeCount;
	private long recreateCount;

	public CollectionStatisticsImpl(String role) {
		super(role);
	}

	public synchronized long getLoadCount() {
		return loadCount;
	}

	public synchronized long incrLoadCount() {
		return ++loadCount;
	}

	public synchronized long getFetchCount() {
		return fetchCount;
	}

	public synchronized long incrFetchCount() {
		return ++fetchCount;
	}
	public synchronized long getRecreateCount() {
		return recreateCount;
	}

	public synchronized long incrRecreateCount() {
		return ++recreateCount;
	}
	public synchronized long getRemoveCount() {
		return removeCount;
	}
	public synchronized long incrRemoveCount() {
		return ++removeCount;
	}
	public synchronized long getUpdateCount() {
		return updateCount;
	}
	public synchronized long incrUpdateCount() {
		return ++updateCount;
	}

	public String toString() {
		return new StringBuffer()
		    .append("CollectionStatistics")
			.append("[loadCount=").append(this.loadCount)
			.append(",fetchCount=").append(this.fetchCount)
			.append(",recreateCount=").append(this.recreateCount)
			.append(",removeCount=").append(this.removeCount)
			.append(",updateCount=").append(this.updateCount)
			.append(']')
			.toString();
	}
}