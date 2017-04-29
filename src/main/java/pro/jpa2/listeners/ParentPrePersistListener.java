package pro.jpa2.listeners;

import javax.persistence.PrePersist;

public class ParentPrePersistListener {

	@PrePersist
	private void callback(Object o) {
		System.out.println("#ParentPrePersistListener.callback: " + o);
	}
}
