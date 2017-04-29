package pro.jpa2;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.AfterClass;
import org.junit.BeforeClass;

public class BaseTestCase {
	protected static EntityManager em;
	private static EntityManagerFactory emf;

	@BeforeClass
	public static void initTest() {
		emf = Persistence.createEntityManagerFactory("hsqldbPU");
		em = emf.createEntityManager();
	}

	@AfterClass
	public static void closeTest() {
		em.close();
		emf.close();
	}
}
