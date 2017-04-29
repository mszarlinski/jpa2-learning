package pro.jpa2.domain;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

import pro.jpa2.BaseTestCase;

public class TestDomainJPQL extends BaseTestCase implements TestDomain {

	@Rule public TestName name = new TestName();
	
	@Before
	public void printMethodName() {
		System.out.println("\n***** " + name.getMethodName().replace('_', ' ') + "\n");
	}
	
	@Test
	@Override
	public void departamenty_z_co_najmniej_1_pracownikiem() {
		em.createQuery(
				"select d "
				+ "from Department1 d "
				+ "where d.employees IS NOT EMPTY")
				.getResultList();
	}

	@Test
	@Override
	public void departamenty_pracownikow_z_CA_ktorzy_brali_udzial_w_projekcie_X_Project() {
		em.createQuery(
				"select distinct e.department "
				+ "from Employee1 e join e.projects p "
				+ "where e.address.state = 'CA' "
				+ "and p.name = 'X-Project'")
				.getResultList();
	}

	@Test
	@Override
	public void pracownicy_z_pensja_powyzej_10000() {
		em.createQuery(
				"select e "
				+ "from Employee1 e "
				+ "where e.salary > 10000")
				.getResultList();
	}

	@Test
	@Override
	public void pracownicy_z_max_pensja() {
		em.createQuery(
				"select e "
				+ "from Employee1 e "
				+ "where e.salary = (select MAX(e1.salary) from Employee1 e1)")
				.getResultList();
	}

	@Test
	@Override
	public void srednie_zarobki_per_nazwa_departamentu_powyzej_10000() {
		em.createQuery(
				"select d.name, AVG(e.salary) "
				+ "from Department1 d join d.employees e "
				+ "group by d having AVG(e.salary) > 10000") // ! zgrupowanie po d.id
				.getResultList();
	}
	
	@Test
	@Override
	public void pracownicy_na_projekcie_X_Project() {
		em.createQuery(
				"select e "
				+ "from Employee1 e "
				+ "where e IN (select e1 from Employee1 e1, IN(e1.projects) p where p.name = 'Project-X')") // bez sensu - mozna samo podzapytanie + distinct
				.getResultList();
		
		// prostsza wersja:
		em.createQuery(
				"select DISTINCT e "
				+ "from Employee1 e join e.projects p "
				+ "where p.name = 'Project-X')")
				.getResultList();
		
		// jeszcze prostsza:
		em.createQuery(
				"select e "
				+ "from Project1 p join p.employees e "
				+ "where p.name = 'Project-X')")
				.getResultList();
	}

	@Test
	@Override
	public void pracownicy_na_projekcie_X_Project_skorelowane() {
		em.createQuery(
				"select e "
				+ "from Employee1 e "
				+ "where EXISTS (select p from e.projects p where p.name = 'Project-X')")
				.getResultList();
	}
	
	@Test
	@Override
	public void pracownicy_z_liczba_projektow_wiecej_niz_2() {
//		em.createQuery(
//				"select e, COUNT(e.projects) "
//				+ "from Employee1 e "
//				+ "where COUNT(e.projects) > 2") // ! potrzebny GROUP BY, aby miec COUNT. SIZE() moze byc tylko w where
//				.getResultList();
		
		em.createQuery(
				"select e, COUNT(p) "
				+ "from Employee1 e join e.projects p " // gdyby byla dowolna ilosc (np. 0), to trzeba "left join"
				+ "group by e having COUNT(p) > 2")
				.getResultList();
	}
	
}
