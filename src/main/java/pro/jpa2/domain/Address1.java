package pro.jpa2.domain;

import java.util.Set;

import javax.persistence.Embeddable;
import javax.persistence.OneToMany;

@Embeddable
public class Address1 {
	private String street;
	private String state;
	
@OneToMany
	Set<Phone1> adresy;
	

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
