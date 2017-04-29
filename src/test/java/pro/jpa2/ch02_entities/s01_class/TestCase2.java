package pro.jpa2.ch02_entities.s01_class;

import org.junit.Assert;
import org.junit.Test;

import pro.jpa2.BaseTestCase;
import pro.jpa2.ch05_collections.maps.Project;

public class TestCase2 extends BaseTestCase {

	@Test
	public void testIdentity() {
		em.getTransaction().begin();

		Project p = new Project();
		p.setId(1);
		p.setName("projectName");
		em.persist(p);
		
		Project pFind = em.find(Project.class, p.getId());

		Assert.assertTrue(p == pFind);
		
		Project pQuerySingleById = (Project) em.createQuery(
				"from Project where id = :id").setParameter("id", p.getId()).getSingleResult();
		Assert.assertTrue(p == pQuerySingleById);
		
		Project pQueryListById = (Project) em.createQuery(
				"from Project where id = :id").setParameter("id", p.getId()).getResultList().get(0);
		Assert.assertTrue(p == pQueryListById);
		
		Project pQuerySingleByProperty = (Project) em.createQuery(
				"from Project where name = :name").setParameter("name", p.getName()).getSingleResult();
		Assert.assertTrue(p == pQuerySingleByProperty);
		
		Project pQueryListByName = (Project) em.createQuery(
				"from Project where name = :name").setParameter("name", p.getName()).getResultList().get(0);
		Assert.assertTrue(p == pQueryListByName);

		em.getTransaction().commit();
		

	}
}
