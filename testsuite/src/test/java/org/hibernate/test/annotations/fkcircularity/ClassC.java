// $Id: ClassC.java 19925 2010-07-09 17:52:47Z steve.ebersole@jboss.com $
package org.hibernate.test.annotations.fkcircularity;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * Test entities ANN-730.
 * 
 * @author Hardy Ferentschik
 * 
 */
@Entity
@Table(name = "class_c")
@PrimaryKeyJoinColumn(name = "id", referencedColumnName = "id")
public class ClassC extends ClassB {
}
