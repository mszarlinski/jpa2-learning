package pro.jpa2.domain;

import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.Inheritance;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
import javax.persistence.criteria.Subquery;

import org.hibernate.criterion.Conjunction;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

import pro.jpa2.BaseTestCase;

public class TestDomainCriteria extends BaseTestCase implements TestDomain {

	private CriteriaBuilder cb;
	private CriteriaQuery<?> cq;
	
	@Rule public TestName name = new TestName();
	
	@Before
	public void printMethodName() {
		System.out.println("\n***** " + name.getMethodName().replace('_', ' ') + "\n");
	}
	
	@Before
	public void prepareCritieraBuilder() {
		cb = em.getCriteriaBuilder();
	}
	
	@After
	public void executeQuery() {
		em.createQuery(cq).getResultList();
	}

	@Test
	@Override
	public void departamenty_z_co_najmniej_1_pracownikiem() {
		// select type
		cq = cb.createQuery(Department1.class); // return result of Department class
		// from clause (identification variables)
		Root<Department1> d = cq.from(Department1.class); // ... from Department d ...
		// where
		cq.where(cb.isNotEmpty(d.<Set<Employee1>>get("employees")));
		// select clause pominiete - NOT PORTABLE!
	}

	@Test
	@Override
	public void departamenty_pracownikow_z_CA_ktorzy_brali_udzial_w_projekcie_X_Project() {
		cq = cb.createQuery(Department1.class);
		// from
		Root<Employee1> e = cq.from(Employee1.class);
		Join<Employee1, Project1> p = e.join("projects"); // inner join
		// where
		cq.where(
				cb.and(
						cb.equal(e.get("address").get("state"), "CA"),
						cb.equal(p.get("name"), "X-Project")
						)
				);
		// select
		((CriteriaQuery<Department1>) cq) // dlatego, że wyniesione jest CriteriaQuery<?>, normalnie nie potrzeba casta
			.select(e.<Department1>get("department"));
	}
	
	@Test
	public void departamenty_pracownikow_z_CA_ktorzy_brali_udzial_w_projekcie_X_Project_with_conjunction() {
		cq = cb.createQuery(Department1.class);
		// from
		Root<Employee1> e = cq.from(Employee1.class);
		Join<Employee1, Project1> p = e.join("projects"); // inner join
		// where
		Predicate conj = cb.conjunction(); // na poczatku bedzie "1=1"
		conj = cb.and(conj, cb.equal(e.get("address").get("state"), "CA"));
		conj = cb.and(conj, cb.equal(p.get("name"), "X-Project"));

		cq.where(conj);
		// select
		((CriteriaQuery<Department1>) cq)
			.select(e.<Department1>get("department"));
	}

	@Test
	@Override
	public void pracownicy_z_pensja_powyzej_10000() {
		cq = cb.createQuery(Employee1.class);
		
		Root<Employee1> e = cq.from(Employee1.class);
		
		cq.where(cb.gt(e.<BigDecimal>get("salary"), 10000));
	}

	@Test
	@Override
	public void pracownicy_z_max_pensja() {
		cq = cb.createQuery(Employee1.class);
		// from
		Root<Employee1> e = cq.from(Employee1.class);
		// subquery
		Subquery<BigDecimal> maxSalary = cq.subquery(BigDecimal.class);
		Root<Employee1> e1 = maxSalary.from(Employee1.class);
		maxSalary.select(cb.max(e1.<BigDecimal>get("salary")));	
		// where
		cq.where(cb.equal(e.get("salary"), maxSalary));
	}

	@Test
	public void pracownicy_z_max_pensja_z_ALL() {
		cq = cb.createQuery(Employee1.class);
		// from
		Root<Employee1> e = cq.from(Employee1.class);
		// subquery
		Subquery<BigDecimal> maxSalary = cq.subquery(BigDecimal.class);
		Root<Employee1> e1 = maxSalary.from(Employee1.class);
		maxSalary.select(cb.max(e1.<BigDecimal>get("salary")));	
		// where
		cq.where(cb.ge(e.<BigDecimal>get("salary"), cb.all(maxSalary)));
	}
	
	@Test
	@Override
	public void srednie_zarobki_per_nazwa_departamentu_powyzej_10000() {
		cq = cb.createTupleQuery();
		// from
		Root<Department1> d = cq.from(Department1.class);
		Join<Department1, Employee1> e = d.join("employees", JoinType.LEFT);
		// group by
		cq.groupBy(d.get("name"));
		cq.having(cb.gt(cb.avg(e.<BigDecimal>get("salary")), 10000));
		// select
		cq.distinct(true).multiselect(d.get("name"), cb.avg(e.<BigDecimal>get("salary")));
	}

	@Test
	@Override
	public void pracownicy_na_projekcie_X_Project() {
		cq = cb.createQuery(Employee1.class);
		// from
		Root<Employee1> e = cq.from(Employee1.class);
		// subquery - w ramach cwiczenia wersja z IN
		Subquery<Employee1> empsInXProject = cq.subquery(Employee1.class);
		Root<Employee1> e1 = empsInXProject.from(Employee1.class);
		Join<Employee1, Project1> p = e1.join("projects");
		empsInXProject.where(cb.equal(p.get("name"), "X-Project"));
		empsInXProject.select(e1);
		// where
		cq.where(cb.in(e).value(empsInXProject)); // specjalna skladnia IN!!!	
	}

	@Test
	@Override
	public void pracownicy_na_projekcie_X_Project_skorelowane() {
		cq = cb.createQuery(Employee1.class);
		// from
		Root<Employee1> e = cq.from(Employee1.class);
		// subquery
		Subquery<Project1> xProjectsOfEmployee = cq.subquery(Project1.class);
		Root<Employee1> e1 = xProjectsOfEmployee.correlate(e);
		Join<Employee1, Project1> p = e1.join("projects");
		xProjectsOfEmployee.where(cb.equal(p.get("name"), "X-Project"));
		xProjectsOfEmployee.select(p);
		// where
		cq.where(cb.exists(xProjectsOfEmployee));
	}

	@Test
	@Override
	public void pracownicy_z_liczba_projektow_wiecej_niz_2() {
		cq = cb.createTupleQuery();
		// from
		Root<Employee1> e = cq.from(Employee1.class);
/*		
		// where
		cq.where(cb.gt(cb.size(e.<Set<Project1>>get("projects")), 2));
		// select
		cq.multiselect(e, cb.size(e.<Set<Project1>>get("projects")));
	*/	
		// musi byc z group by, zeby zastosowac counta!
		
		Join<Employee1, Project1> p = e.join("projects");
		// group by
		cq.groupBy(e).having(cb.gt(cb.count(p), 2));
		// select
		cq.multiselect(e, cb.count(p));
	}
	
}
