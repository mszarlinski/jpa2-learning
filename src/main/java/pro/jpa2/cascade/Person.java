package pro.jpa2.cascade;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Person {
	@Id
	public int id;
	@OneToOne
	public Account account;
}
