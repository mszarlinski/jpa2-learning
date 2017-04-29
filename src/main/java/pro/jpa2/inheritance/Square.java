package pro.jpa2.inheritance;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

//@DiscriminatorValue(value = "3")
@Entity
public class Square extends Shape {
	public double a;
}
