package pro.jpa2.domain;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PostLoad;

@Entity
public class Employee1 {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;

	private BigDecimal salary;

	@ManyToOne(cascade = CascadeType.REMOVE)
	private Department1 department;

	@Embedded
	private Address1 address;

	@OneToMany
	private Map<String, Phone1> phones;

	@ManyToMany
	private Set<Project1> projects;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getSalary() {
		return salary;
	}

	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}

	public Department1 getDepartment() {
		return department;
	}

	public void setDepartment(Department1 department) {
		this.department = department;
	}

	public Address1 getAddress() {
		return address;
	}

	public void setAddress(Address1 address) {
		this.address = address;
	}

	public Map<String, Phone1> getPhones() {
		return phones;
	}

	public void setPhones(Map<String, Phone1> phones) {
		this.phones = phones;
	}

	public Set<Project1> getProject() {
		return projects;
	}

	public void setProject(Set<Project1> project) {
		this.projects = project;
	}

}
