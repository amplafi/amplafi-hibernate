//$Id: AId.java 20650 2010-09-16 11:00:16Z stliu $
package org.hibernate.test.annotations.cid;

import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;


/**
 * @author Artur Legan
 */
@Embeddable
public class AId implements Serializable {

	@OneToOne
	@JoinColumn( name = "bid", nullable = false )
	private B b;

	@OneToOne
	@JoinColumn( name = "cid", nullable = false )
	private C c;


	public B getB() {
		return b;
	}

	public void setB(B b) {
		this.b = b;
	}

	public C getC() {
		return c;
	}

	public void setC(C c) {
		this.c = c;
	}
}
