package pro.jpa2.complexEmbeddable;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class CePhone {
	@Id
	String num;
	
	String type;

	@ManyToMany(mappedBy = "contactInfo.phones")
	Set<CeEmployee> employees;
}
