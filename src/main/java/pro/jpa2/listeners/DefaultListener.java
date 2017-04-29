package pro.jpa2.listeners;

import javax.persistence.PrePersist;

public class DefaultListener {

	@PrePersist
	private void prePersist(Object o) {
		System.out.println("#DefaultListener.callback: " + o);
	}
}
