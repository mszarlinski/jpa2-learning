package pro.jpa2.merge;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class WorkerAddress {
	@Id
	@GeneratedValue
	int id;

	@OneToOne(mappedBy = "address")
	Worker worker;
}
