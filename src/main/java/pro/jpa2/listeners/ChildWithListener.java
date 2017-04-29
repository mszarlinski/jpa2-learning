package pro.jpa2.listeners;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.PrePersist;

@Entity
@EntityListeners({ ParentPrePersistListener.class,
		ChildPrePersistListener.class })
public class ChildWithListener extends ParentWithListener {

	@PrePersist
	private void bar() {
		System.out.println("#ChildWithListener.prePersist");
	}
}
