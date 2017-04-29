package pro.jpa2.inheritance;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
@Inheritance
@Entity
@DiscriminatorColumn(discriminatorType= DiscriminatorType.STRING)
public class Shape {
	@Id
	int id;
}
