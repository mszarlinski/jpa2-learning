package pro.jpa2.compoundId;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@IdClass(EmpIdClass.class)
@Entity
public class EmpWithIdClass {
	@Id
	@Column(name = "empId")
	int id;
	@Id
	String country;
}
