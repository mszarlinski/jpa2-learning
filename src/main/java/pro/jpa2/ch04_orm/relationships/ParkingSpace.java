package pro.jpa2.ch04_orm.relationships;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class ParkingSpace {
	@Id
	private int id;
	
	private BigDecimal cost;

//	@OneToOne(cascade = CascadeType.REMOVE, mappedBy = "parkingSpace")
//	private Employee employee;

	@ManyToOne
	private Employee employee;
	
	@ManyToOne
	private Employee myEmp;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

}
