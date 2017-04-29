package pro.jpa2.listeners;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.PrePersist;

@Entity
@EntityListeners(ParentPrePersistListener.class)
public class ParentWithListener {

	@Id
	int id;

	@PrePersist
	private void foo() {
		System.out.println("#ParentWithListener.prePersist");
	}
}
