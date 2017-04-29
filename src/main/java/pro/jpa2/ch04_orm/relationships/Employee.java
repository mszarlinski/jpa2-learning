package pro.jpa2.ch04_orm.relationships;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderColumn;
import javax.persistence.PostLoad;

@Entity
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
//	@OrderBy
//	@ManyToMany(mappedBy = "employee", fetch = FetchType.EAGER)
//	private List<Department> depos;
	
	@ManyToOne
	public Department department;


	@ElementCollection
	private Collection<Address> addresses;
	
	@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
	public ParkingSpace parkingSpace;

	
	@ElementCollection(targetClass=String.class)
	List labels;

	@ManyToMany(mappedBy = "employees")
	Set<Department> departments;
	
	@ElementCollection
	Map<ParkingSpace, BigDecimal> parkingCosts;
	
	@OneToMany(mappedBy = "myEmp")
	Map<String, ParkingSpace> parkingNames;
	
	@PostLoad
	public void foo() {
		System.out.println("##### postLoad");
	}
	
	//@Access(AccessType.PROPERTY)
	public int getAge() {
		return 0;
	}
	
	public void setAge(int a) {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

//	public List<Department> getDepos() {
//		return depos;
//	}
//
//	public void setDepos(List<Department> depos) {
//		this.depos = depos;
//	}

	public List<String> getLabels() {
		return labels;
	}

	public void setLabels(List<String> labels) {
		this.labels = labels;
	}

}
