package pro.jpa2.ch05_collections.maps;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.MapKey;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;

@Entity
public class Student {
	@Id
	private int id;

	@ManyToMany
	@MapKeyColumn(name = "SUBJECT_NAME")
	@JoinTable(joinColumns = @JoinColumn(name = "STUD_ID"), inverseJoinColumns = @JoinColumn(name = "PROJ_ID"))
	private Map<String, Project> projects = new HashMap<String, Project>();


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Map<String, Project> getProjects() {
		return projects;
	}

	public void setProjects(Map<String, Project> projects) {
		this.projects = projects;
	}

}
