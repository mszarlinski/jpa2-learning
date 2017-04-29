package pro.jpa2.merge;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class WorkingDepartment {
	@Id
	@GeneratedValue
	int id;

	@ManyToMany(mappedBy = "departments")
	Set<Worker> workers = new HashSet<Worker>();
}
