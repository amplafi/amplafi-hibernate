// $Id: A.java 19925 2010-07-09 17:52:47Z steve.ebersole@jboss.com $
package org.hibernate.test.annotations.fkcircularity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

/**
 * Test entities ANN-722.
 * 
 * @author Hardy Ferentschik
 *
 */
@Entity
public class A {
	private A_PK id;

	@EmbeddedId
	public A_PK getId() {
		return id;
	}

	public void setId(A_PK id) {
		this.id = id;
	}
}
