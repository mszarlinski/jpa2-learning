package pro.jpa2.inheritance;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

import javax.persistence.LockModeType;
import javax.persistence.OptimisticLockException;

import org.junit.Test;

import pro.jpa2.BaseTestCase;

public class DiscriminatorTest extends BaseTestCase {

	@Test
	public void test1() {
		// given
		Square sq = new Square();
		sq.a = 10;
		// when
		em.getTransaction().begin();
		em.persist(sq);
		em.getTransaction().commit();


		// then
		Shape shape = em.find(Shape.class, sq.id);
		int x = 1;
	}
}
