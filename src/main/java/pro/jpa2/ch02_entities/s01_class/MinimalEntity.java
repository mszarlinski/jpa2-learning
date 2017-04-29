package pro.jpa2.ch02_entities.s01_class;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
class MinimalEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String name;

	@Basic(fetch = FetchType.LAZY)
	@Lob
	private byte[] wielkiKutas;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
