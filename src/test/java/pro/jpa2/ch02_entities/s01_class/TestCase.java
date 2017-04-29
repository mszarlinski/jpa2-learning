package pro.jpa2.ch02_entities.s01_class;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import pro.jpa2.BaseTestCase;
import pro.jpa2.ch04_orm.relationships.Department;
import pro.jpa2.ch04_orm.relationships.Employee;
import pro.jpa2.ch04_orm.relationships.ParkingSpace;

public class TestCase extends BaseTestCase {

	@Test
	public void testMinimalEntity() {
//		EntityTransaction tx = em.getTransaction();
//		tx.begin();

		em.getTransaction().begin();
		
		MinimalEntity e = new MinimalEntity();
//		e.setId(1);
		e.setName("Maciek");
		em.persist(e);
		
		Department d1 = new Department();
		em.persist(d1);
		Employee e1 = new Employee();

//		d1.employee.add(e1);
		e1.department = d1;
		
		ParkingSpace ps = new ParkingSpace();
		ps.setEmployee(e1);
		e1.parkingSpace = ps;
		ps.setId(1);
		
		em.persist(e1);
		em.getTransaction().commit();
		em.clear();

		em.getTransaction().begin();
		List<MinimalEntity> me = em.createQuery("from MinimalEntity").getResultList();
		System.out.println(me.get(0).getId());
		Employee found = em.find(Employee.class, 1);
		Assert.assertTrue(em.contains(found));
		System.out.println(found.department);
		System.out.println(found.parkingSpace);
		
		em.createNativeQuery("delete from MinimalEntity where id = :id").setParameter("id", 1).executeUpdate();
		em.remove(found.parkingSpace);
		em.getTransaction().commit();

	}
}
