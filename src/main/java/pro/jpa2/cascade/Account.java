package pro.jpa2.cascade;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Account {
	@Id
	public int id;

	@OneToOne(mappedBy = "account", cascade = CascadeType.PERSIST)
	public Person person;
}
