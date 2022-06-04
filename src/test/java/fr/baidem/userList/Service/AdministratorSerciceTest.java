package fr.baidem.userList.Service;

import static org.junit.jupiter.api.Assertions.*;

import org.jeasy.random.EasyRandom;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
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
	
	
    private List<Administrator> administrators = new ArrayList<Administrator>();
    private List<Long> adminsIdAfterDelete = new ArrayList<Long>();
    
        
    @BeforeEach
    private void initData() {
    	System.out.println("@BeforeEach");
    	EasyRandom easyRandom = new EasyRandom();
    	adminsIdAfterDelete = new ArrayList<Long>();
    	for(int i = 0; i<3; i++) {
    		Administrator adminRandom = easyRandom.nextObject(Administrator.class);
    		adminRandom = this.administratorRepository.save(adminRandom);
    		adminsIdAfterDelete.add(adminRandom.getId());
    	}
    	System.out.print("==> adminRandom id : ");
    	for (Long id : adminsIdAfterDelete) {
			System.out.print(id + ", ");
		}
    	System.out.println("");
	}
    
    
    @AfterEach
	public void destruct() {
    	System.out.println("@AfterEach");
    	for (Long adminId : adminsIdAfterDelete) {
    		this.administratorService.deleteAdministrator(adminId);			
		}
	}
	
	@Test
	@DisplayName("Test : createAdministrator()")
	void test() {
		System.out.println("Test : createAdministrator()");
		// test01 create
		administrators = new ArrayList<Administrator>();
		String name = "test01";
		String password = "pass";
		int sizeA = administratorService.findAll().size();
		Administrator test01 = this.administratorService.createAdministrator(name, password);
		adminsIdAfterDelete.add(test01.getId());
		administrators = administratorService.findAll();
		int sizeB = administrators.size();
		assertNotNull(test01);
		assertTrue(test01.getName().equals("test01"));
		assertNotNull(administrators);
		assertEquals(sizeB-sizeA,1);
		// test02 create with exist name
		name = "test01";// test same name
		password = "pass";
		Administrator test02 = this.administratorService.createAdministrator(name, password);
		administrators = administratorService.findAll();
		sizeB = administrators.size();
		assertEquals(sizeB-sizeA,1);
		// test02 create with new name
		name = "test02";// test new name
		test02 = this.administratorService.createAdministrator(name, password);
		adminsIdAfterDelete.add(test02.getId());
		administrators = administratorService.findAll();
		sizeB = administrators.size();
		assertNotNull(test02);
		assertTrue(test02.getName().equals("test02"));
		assertNotNull(administrators);
		assertEquals(sizeB-sizeA,2);


	}
	
	@Test
    @DisplayName("Test : updateAdministrator()")
    public void testUpdateUser(){
		System.out.println("Test : updateAdministrator()");
		administrators = new ArrayList<Administrator>();
		// test03 create		
		String name = "test03";
		String password = "pass";		
		int sizeA = this.administratorService.findAll().size();
		Administrator test03 = this.administratorService.createAdministrator(name, password);
		adminsIdAfterDelete.add(test03.getId());
		Long idTest03 = test03.getId();
		assertTrue(this.administratorService.existId(idTest03));
		int sizeB = this.administratorService.findAll().size();
		assertEquals(sizeB-sizeA,1);
		// test04 create		
		name = "test04";
		password = "pass";
		Administrator test04 = this.administratorService.createAdministrator(name, password);
		adminsIdAfterDelete.add(test04.getId());
		Long idTest04 = test04.getId();
		assertTrue(this.administratorService.existId(idTest04));
		assertNotNull(administrators);
		sizeB = this.administratorService.findAll().size();
		assertEquals(sizeB-sizeA,2);
		// test03 update exist name		
        this.administratorService.updateAdministrator(idTest03, "test04", "pass");
		sizeB = this.administratorService.findAll().size();
		assertEquals(sizeB-sizeA,2);
        assertTrue(this.administratorService.getById(idTest03).getName().equals("test03"));
		// test03 update new name		        
        this.administratorService.updateAdministrator(idTest03, "update_test", "pass");
		sizeB = this.administratorService.findAll().size();
		assertEquals(sizeB-sizeA,2);
        assertTrue(this.administratorService.getById(idTest03).getName().equals("update_test"));

    }	
	
    @Test
    @DisplayName("Test : deleteAdministrator()")
    public void testDeleteAdministrator(){
    	System.out.println("Test : deleteAdministrator()");
		administrators = new ArrayList<Administrator>();
		// test05 create		
		String name = "test05";
		String password = "pass";		
		int sizeA = this.administratorService.findAll().size();
		Administrator test05 = this.administratorService.createAdministrator(name, password);
		adminsIdAfterDelete.add(test05.getId());
		Long idTest05 = test05.getId();		
		assertTrue(this.administratorService.existId(idTest05));
		int sizeB = this.administratorService.findAll().size();
		assertEquals(sizeB-sizeA,1);
		// test delete fake id
		this.administratorService.deleteAdministrator(idTest05 + 1l);
		sizeB = this.administratorService.findAll().size();
		assertEquals(sizeB-sizeA,1);
		// test05 delete
		this.administratorService.deleteAdministrator(idTest05);
		sizeB = this.administratorService.findAll().size();
		assertEquals(sizeB-sizeA,0);
		assertTrue(!this.administratorService.existId(idTest05));

    }
	

}
