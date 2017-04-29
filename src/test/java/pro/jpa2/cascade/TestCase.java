package pro.jpa2.cascade;

import javax.persistence.PersistenceException;

import org.junit.Test;

import pro.jpa2.BaseTestCase;

public class TestCase extends BaseTestCase {

	@Test(expected = PersistenceException.class)
	public void testShouldNotPersistAccount() {
		Account acct = new Account();
		Person p = new Person();
		em.getTransaction().begin();
		p.account = acct;
		acct.person = p;
		em.persist(p);
//		em.persist(acct);
		em.getTransaction().commit();
	}
}
