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

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.hibernate.cache.CacheKey;
import org.hibernate.cache.Region;
import org.hibernate.stat.SecondLevelCacheStatistics;

/**
 * Second level cache statistics of a specific region
 *
 * @author Gavin King
 */
public class SecondLevelCacheStatisticsImpl extends CategorizedStatistics implements SecondLevelCacheStatistics {

    private transient Region region;
	private long hitCount;
	private long missCount;
	private long putCount;

	public SecondLevelCacheStatisticsImpl(Region region) {
		super( region.getName() );
		this.region = region;
	}
	public synchronized long getHitCount() {
		return hitCount;
	}
	public synchronized long incrHitCount() {
		return ++hitCount;
	}
	public synchronized long getMissCount() {
		return missCount;
	}
	public synchronized long incrMissCount() {
		return ++missCount;
	}
	public synchronized long getPutCount() {
		return putCount;
	}
	public synchronized long incrPutCount() {
		return ++putCount;
	}
	public synchronized long getElementCountInMemory() {
		return region.getElementCountInMemory();
	}
	public synchronized long getElementCountOnDisk() {
		return region.getElementCountOnDisk();
	}
	public long getSizeInMemory() {
		return region.getSizeInMemory();
	}

	public Map getEntries() {
		Map map = new HashMap();
		Iterator iter = region.toMap().entrySet().iterator();
		while ( iter.hasNext() ) {
			Map.Entry me = (Map.Entry) iter.next();
			map.put( ( (CacheKey) me.getKey() ).getKey(), me.getValue() );
		}
		return map;
	}

	public String toString() {
		StringBuffer buf = new StringBuffer()
		    .append("SecondLevelCacheStats")
			.append("[hitCount=").append(this.hitCount)
			.append(",missCount=").append(this.missCount)
			.append(",putCount=").append(this.putCount);
		//not sure if this would ever be null but wanted to be careful
		if ( region != null ) {
			buf.append(",elementCountInMemory=").append(this.getElementCountInMemory())
				.append(",elementCountOnDisk=").append(this.getElementCountOnDisk())
				.append(",sizeInMemory=").append(this.getSizeInMemory());
		}
		buf.append(']');
		return buf.toString();
	}
}
