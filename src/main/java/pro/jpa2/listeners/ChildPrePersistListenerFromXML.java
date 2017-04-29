package pro.jpa2.listeners;

import javax.persistence.PrePersist;

public class ChildPrePersistListenerFromXML {

	@PrePersist
	private void prePersist(Object o) {
		System.out.println("#ChildPrePersistListenerFromXML.callback: " + o);
	}
}
