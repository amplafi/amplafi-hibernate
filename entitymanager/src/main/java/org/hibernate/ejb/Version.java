// $Id: Version.java 19999 2010-07-21 20:35:16Z steve.ebersole@jboss.com $
/*
 * Copyright (c) 2009, Red Hat Middleware LLC or third-party contributors as
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
 */

//$Id: Version.java 19999 2010-07-21 20:35:16Z steve.ebersole@jboss.com $
package org.hibernate.ejb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author Emmanuel Bernard
 */
public class Version {
	private static Logger log = LoggerFactory.getLogger( Version.class );

	public static String getVersionString() {
		return "[WORKING]";
	}

	static {
		log.info( "Hibernate EntityManager {}", getVersionString() );
	}

	public static void touch() {
	}
}
