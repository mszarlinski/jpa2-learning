package pro.jpa2.ch04_orm.relationships;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ExcludeDefaultListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.OrderColumn;
@ExcludeDefaultListeners
@Entity
public class Department {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int id;
	
	@OrderBy("name DESC, id ASC")
	@OneToMany(mappedBy = "department")
//	@JoinTable(name = "tabela_linkujaca")
	public List<Employee> employee = new ArrayList<Employee>();
	
	@ElementCollection
	@CollectionTable(name="EMP_SENIORITY")
@MapKeyJoinColumn(name="EMP_ID")
//	@Column(name="SENIORITY")
	private Map<Employee, Integer> seniorities;
	
	@ManyToMany//(mappedBy = "departments")
	Set<Employee> employees;
	
	@ManyToOne
	Employee ceo;
}
