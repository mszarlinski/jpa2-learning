package pro.jpa2.ch02_entities.s01_class;

import java.util.List;

import org.junit.Test;

import pro.jpa2.BaseTestCase;
import pro.jpa2.ch05_collections.maps.Project;
import pro.jpa2.ch05_collections.maps.Student;

public class TestCase1 extends BaseTestCase {

	@Test
	public void testMap() {
		em.getTransaction().begin();

		Project p = new Project();
		p.setId(1);
		em.persist(p);

		Student s = new Student();
		s.getProjects().put("Bazy danych", p);
		em.persist(s);

		em.getTransaction().commit();
		em.clear();

		List<Student> students = em.createQuery(
				"from Student s join fetch s.projects").getResultList();
		System.out.println(students.get(0).getProjects());

	}
}
