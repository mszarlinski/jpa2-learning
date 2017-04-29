package pro.jpa2.locking;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

import javax.persistence.LockModeType;
import javax.persistence.OptimisticLockException;

import org.junit.Test;

import pro.jpa2.BaseTestCase;

public class OptimisticLockTest extends BaseTestCase {

	@Test
	public void testForceIncrement() {
		// given
		VersionedEntity e = new VersionedEntity();

		em.getTransaction().begin();
		em.persist(e);
		em.getTransaction().commit();

		// when
		for (int i = 0; i < 5; i++) {
			em.getTransaction().begin();
			em.find(VersionedEntity.class, e.id,
					LockModeType.OPTIMISTIC_FORCE_INCREMENT);
			em.getTransaction().commit();
		}

		// then
		e = em.find(VersionedEntity.class, e.id);
		assertEquals(5, e.version);
	}

	@Test
	public void testVersion() {
		// given
		VersionedEntity e = new VersionedEntity();
		em.getTransaction().begin();
		em.persist(e);
		em.getTransaction().commit();

		// when
		for (int i = 0; i < 5; i++) {
			em.getTransaction().begin();
			e = em.find(VersionedEntity.class, e.id);
			e.quantity++;
			em.getTransaction().commit();
		}

		// then
		e = em.find(VersionedEntity.class, e.id);
		assertEquals(5, e.version);
	}
	
	@Test
	public void testInheritedVersion() {
		// given
		EntityWithVersionInherited e = new EntityWithVersionInherited();
		em.getTransaction().begin();
		em.persist(e);
		em.getTransaction().commit();

		// when
		for (int i = 0; i < 5; i++) {
			em.getTransaction().begin();
			e = em.find(EntityWithVersionInherited.class, e.id);
			e.setName(Integer.toString(i));
			em.getTransaction().commit();
		}

		// then
		e = em.find(EntityWithVersionInherited.class, e.id);
		assertEquals(5, e.version);
	}
}
