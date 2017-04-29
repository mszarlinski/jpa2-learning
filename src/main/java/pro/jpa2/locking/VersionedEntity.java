package pro.jpa2.locking;

import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Version;

@Inheritance(strategy=InheritanceType.JOINED)
@Entity
public class VersionedEntity {
	@Id
	@GeneratedValue
	public int id;

	@Version
	public int version;

	public int quantity;
}
