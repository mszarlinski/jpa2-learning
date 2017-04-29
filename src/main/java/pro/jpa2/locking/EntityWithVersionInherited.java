package pro.jpa2.locking;

import javax.persistence.Entity;

@Entity
public class EntityWithVersionInherited extends VersionedEntity {
	
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
