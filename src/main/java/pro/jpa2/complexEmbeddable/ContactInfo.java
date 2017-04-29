package pro.jpa2.complexEmbeddable;

import java.util.Map;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapKey;

@Access(AccessType.FIELD)
@Embeddable
public class ContactInfo {
	@Embedded
	CeAddress adres;
	
	@ManyToOne
	@JoinColumn(name = "pri_num")
	CePhone primaryPhone;
	
	@ManyToMany
	@MapKey(name="type")
	Map<String, CePhone> phones;
}
