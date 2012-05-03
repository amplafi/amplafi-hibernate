// $Id: D_PK.java 19925 2010-07-09 17:52:47Z steve.ebersole@jboss.com $
package org.hibernate.test.annotations.fkcircularity;

import java.io.Serializable;

import javax.persistence.ManyToOne;

/**
 * Test entities ANN-722.
 * 
 * @author Hardy Ferentschik
 *
 */
@SuppressWarnings("serial")
public class D_PK implements Serializable{
	private C c;
	
	@ManyToOne
	public C getC() {
		return c;
	}

	public void setC(C c) {
		this.c = c;
	}
}
