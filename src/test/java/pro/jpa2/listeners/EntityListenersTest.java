package pro.jpa2.listeners;

import org.junit.Test;

import pro.jpa2.cascade.TestCase;

public class EntityListenersTest extends TestCase {
	@Test
	public void test1() {
		ChildWithListener c = new ChildWithListener();
		em.persist(c);
	}
}
