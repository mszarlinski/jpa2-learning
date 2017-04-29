package pro.jpa2.domain;


public interface TestDomain {
	void departamenty_z_co_najmniej_1_pracownikiem();

	void departamenty_pracownikow_z_CA_ktorzy_brali_udzial_w_projekcie_X_Project();

	void pracownicy_z_pensja_powyzej_10000();

	void pracownicy_z_max_pensja();

	void srednie_zarobki_per_nazwa_departamentu_powyzej_10000();

	void pracownicy_na_projekcie_X_Project();
	
	void pracownicy_na_projekcie_X_Project_skorelowane();
	
	void pracownicy_z_liczba_projektow_wiecej_niz_2();
}
