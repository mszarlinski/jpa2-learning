package pro.jpa2.complexEmbeddable;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PostLoad;

@Entity
public class Customer {
	@Id
	int id;

	@Embedded
	ContactInfo contactInfo;	
}
