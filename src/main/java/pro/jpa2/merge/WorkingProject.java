package pro.jpa2.merge;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class WorkingProject {
	@Id
	@GeneratedValue
	int id;

	String name;

	@ManyToOne
	Worker worker;

	public WorkingProject() {
		// TODO Auto-generated constructor stub
	}

	public WorkingProject(String name, Worker worker) {
		this.name = name;
		this.worker = worker;
	}

}
