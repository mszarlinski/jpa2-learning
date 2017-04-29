package pro.jpa2.merge;

import static java.util.Arrays.asList;

import java.util.HashSet;

import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

import pro.jpa2.BaseTestCase;

public class MergeTest extends BaseTestCase {

	private Worker worker;

	@Rule
	public TestName name = new TestName();

	@Before
	public void setUp() {
		em.getTransaction().begin();
		worker = new Worker("Maciek");

		worker.phones.addAll(asList("+48600123456", "226801462"));

		WorkerAddress address = new WorkerAddress();
		address.worker = worker;
		worker.address = address;
		em.persist(address);

		Vehicle v1 = new Vehicle("Mercedes");
		Vehicle v2 = new Vehicle("Passat");
		worker.vehicles.addAll(asList(v1, v2));
		em.persist(v1);
		em.persist(v2);
		
		WorkingProject p1 = new WorkingProject("projekt1", worker);
		WorkingProject p2 = new WorkingProject("projekt2", worker);
		worker.projects.addAll(asList(p1, p2));
		em.persist(p1);
		em.persist(p2);

		em.persist(worker);
		em.getTransaction().commit();

		System.out.println("\n   ########## "
				+ name.getMethodName().replace('_', ' ') 
				+ " ##########\n");
	}
	
	@After
	public void finish() {
		System.out.println("\n   ########## TEST FINISHED ##########\n");
	}

	@Test
	public void should_update_worker_name() {
		// given
		worker.name = "Włodek";
		// when
		em.getTransaction().begin();
		worker = em.merge(worker);
		em.getTransaction().commit();
		// then
		Assert.assertEquals("Włodek", worker.name);
	}

	@Test
	public void should_update_collection() {
		// given
		worker.phones.add("248107067");
		worker.phones.remove("+48600123456");
		// when
		em.getTransaction().begin();
		worker = em.merge(worker);
		em.getTransaction().commit();
		// then
		Assertions.assertThat(worker.phones).containsExactly("248107067", "226801462");
	}
	
	@Test
	public void should_update_collection_new_set() {
		// given
		worker.phones = new HashSet<String>();
		worker.phones.add("248107067");
		// when
		em.getTransaction().begin();
		worker = em.merge(worker);
		em.getTransaction().commit();
		// then
		Assertions.assertThat(worker.phones).containsExactly("248107067");
	}
	
	@Test
	public void should_remove_owned_one_to_one() {
		// given
		worker.address = null;
		// when
		em.getTransaction().begin();
		worker = em.merge(worker);
		em.getTransaction().commit();
		// then
		Assertions.assertThat(worker.address).isNull();
	}
	
	@Test
	public void should_update_owned_one_to_one() {
		// given
		worker.address = null;
		// when
		em.getTransaction().begin();
		worker = em.merge(worker);
		em.getTransaction().commit();
		// then
		Assertions.assertThat(worker.address).isNull();
	}
	
	@Test
	public void should_update_unidirectorial_one_to_many() {
		// given
		em.getTransaction().begin();
		Vehicle newVehicle = new Vehicle("Punto");
		em.persist(newVehicle);
		em.getTransaction().commit();
		
		worker.vehicles.removeIf(v -> v.name.equals("Mercedes"));
		worker.vehicles.add(newVehicle);
		// when
		em.getTransaction().begin();
		worker = em.merge(worker);
		em.getTransaction().commit();
		// then
		Assertions.assertThat(worker.vehicles).hasSize(2);
	}

	@Test
	public void should_update_unidirectorial_one_to_many_new_set() {
		// given
		em.getTransaction().begin();
		Vehicle newVehicle = new Vehicle("Punto");
		em.persist(newVehicle);
		em.getTransaction().commit();
		
		worker.vehicles = new HashSet<Vehicle>();
		worker.vehicles.add(newVehicle);
		// when
		em.getTransaction().begin();
		worker = em.merge(worker);
		em.getTransaction().commit();
		// then
		Assertions.assertThat(worker.vehicles).hasSize(1);
	}
	
	@Test
	public void should_update_bidirectorial_one_to_many() {
		// given
		em.getTransaction().begin();
		WorkingProject newProject = new WorkingProject("projekt3", worker);
		em.persist(newProject);
		em.getTransaction().commit();
		
		worker.projects.removeIf(p -> p.name.equals("projekt1"));
		worker.projects.add(newProject);
		// when
		em.getTransaction().begin();
		worker = em.merge(worker);
		em.getTransaction().commit();
		// then
		Assertions.assertThat(worker.projects).hasSize(2);
	}
	
	@Test
	public void should_update_bidirectorial_one_to_many_new_set() {
		// given
		em.getTransaction().begin();
		WorkingProject newProject = new WorkingProject("projekt3", worker);
		em.persist(newProject);
		em.getTransaction().commit();
		
		worker.projects = new HashSet<WorkingProject>();
		worker.projects.add(newProject);
		// when
		em.getTransaction().begin();
		worker = em.merge(worker);
		em.getTransaction().commit();
		// then
		Assertions.assertThat(worker.projects).hasSize(1);
	}
}
