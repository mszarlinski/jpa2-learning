package pro.jpa2.merge;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Vehicle {
	@Id
	@GeneratedValue
	int id;

	String name;

	public Vehicle() {
		// TODO Auto-generated constructor stub
	}

	public Vehicle(String name) {
		this.name = name;
	}

}
