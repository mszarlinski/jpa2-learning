package pro.jpa2.compoundId;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class EmpWithEmbeddedId {
	@AttributeOverrides({ 
		@AttributeOverride(name = "id", column = @Column(name = "employee_id")),
		@AttributeOverride(name = "country", column = @Column(name = "employee_country"))
		})
	@EmbeddedId
	EmbeddableEmpId id;
}
