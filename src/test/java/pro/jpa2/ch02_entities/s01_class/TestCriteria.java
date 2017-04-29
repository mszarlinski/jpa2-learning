package pro.jpa2.ch02_entities.s01_class;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import org.junit.Test;

import pro.jpa2.BaseTestCase;
import pro.jpa2.ch04_orm.relationships.Address;
import pro.jpa2.ch04_orm.relationships.Employee;

public class TestCriteria extends BaseTestCase {

	@Test
	public void testIdentity() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Employee> q = cb.createQuery(Employee.class);

		Root<Employee> root = q.from(Employee.class);
		q.select(root);

		Subquery<String> subq = q.subquery(String.class);
		Root<Employee> emp = subq.correlate(root);
		Join<Employee, Address> address = emp.join("addresses");
		subq.select(address.<String>get("street")).where(cb.equal(address.get("street"), cb.parameter(String.class, "streetName")));
		
		q.where(cb.exists(subq));
		
		em.createQuery(q).setParameter("streetName", "Kinowa").getResultList();
	}
}
