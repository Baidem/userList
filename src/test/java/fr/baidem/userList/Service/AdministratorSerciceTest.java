package fr.baidem.userList.Service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import fr.baidem.userList.model.Administrator;
import fr.baidem.userList.repository.AdministratorRepository;
import fr.baidem.userList.service.AdministratorService;

@SpringBootTest
@DisplayName("AdministratorService's test ")
class AdministratorSerciceTest {
	@Autowired
    AdministratorService administratorService;
	
	@Autowired
	AdministratorRepository administratorRepository;

    private List<Administrator> administrators; 
    
    @BeforeAll
    static void initAll() {
    	System.out.println("@BeforeAll");
    }	
	
	@Test
	@DisplayName("createAdministrator()'s test")
	void test() {
		String name = "Admin";
		String password = "admin";
	
		Administrator admin = this.administratorService.createAdministrator(name, password);
		administrators = administratorService.findAll();
		assertNotNull(admin);
		assertTrue(admin.getName().equals("Admin"));
		assertNotNull(administrators);
		assertEquals(administrators.size(),1);

		name = "Admin";// test same name
		password = "admina";
		
		Administrator admin02 = this.administratorService.createAdministrator(name, password);
		administrators = administratorService.findAll();
		assertEquals(administrators.size(),1);
		
		name = "Admina";// test new name
		
		admin02 = this.administratorService.createAdministrator(name, password);
		administrators = administratorService.findAll();
		assertNotNull(admin02);
		assertTrue(admin02.getName().equals("Admina"));
		assertNotNull(administrators);
		assertEquals(administrators.size(),2);


	}
	
	

}
