package pro.jpa2.merge;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Worker {
	@Id
	@GeneratedValue
	int id;

	String name;

	public Worker() {
		// TODO Auto-generated constructor stub
	}

	public Worker(String name) {
		this.name = name;
	}

	@ElementCollection
	Set<String> phones = new HashSet<String>();

	// bidirectorial
	@OneToOne
	WorkerAddress address;

	// bidirectorial
	@OneToMany(mappedBy = "worker")
	Set<WorkingProject> projects = new HashSet<WorkingProject>();

	// unidirectorial
	@OneToMany
	Set<Vehicle> vehicles = new HashSet<Vehicle>();

	// bidirectorial
	@ManyToMany
	Set<WorkingDepartment> departments = new HashSet<WorkingDepartment>();

}
