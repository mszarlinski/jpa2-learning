package pro.jpa2.listeners;

import javax.persistence.PrePersist;

public class ChildPrePersistListener {

	@PrePersist
	private void callback(Object o) {
		System.out.println("#ChildPrePersistListener.callback: " + o);
	}
}
