package fr.baidem.userList.Service;

import static org.junit.jupiter.api.Assertions.*;

import org.jeasy.random.EasyRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import fr.baidem.userList.model.Administrator;
import fr.baidem.userList.repository.AdministratorRepository;
import fr.baidem.userList.service.AdministratorService;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("Test : class AdministratorService")
class AdministratorServiceTest {
	@Autowired
    AdministratorService administratorService;
	
	@Autowired
	AdministratorRepository administratorRepository;
	
	
    private List<Administrator> administrators = new ArrayList<Administrator>();
    private List<Administrator> adminsAfterDelete = new ArrayList<Administrator>();

    // private List<Long> adminsIdAfterDelete = new ArrayList<Long>();
    
    @BeforeAll
    private void initAll() {
        System.out.println("@BeforeAll");       
    	System.out.println("initAll()");
    	EasyRandom easyRandom = new EasyRandom();
    	// adminsIdAfterDelete = new ArrayList<Long>();
    	for(int i = 0; i<3; i++) {
    		Administrator adminRandom = easyRandom.nextObject(Administrator.class);
    		adminRandom = this.administratorRepository.save(adminRandom);
    		//adminsIdAfterDelete.add(adminRandom.getId());
    		adminsAfterDelete.add(adminRandom);
    	}
	}
    
    @AfterAll
	public void destruct() {
    	System.out.println("@AfterEach");
    	System.out.println("destruct()");
    	for (Administrator admin : adminsAfterDelete) {
    		this.administratorRepository.delete(admin);			
		}
	}
    
    @Test
	@DisplayName("Test : findAll()")
	void testFindAll() {
		System.out.println("Test : findAll()");
    	int sizeA = -1;
    	assertNotNull(this.administratorService.findAll(), "Message : assertNotNull(this.administratorService.findAll()) is null");
    	sizeA = this.administratorService.findAll().size();
    	assertTrue(sizeA > -1, "Message : assertTrue(sizeA > -1) is false");
    	Administrator adminTestFindAll = new Administrator("nameTestFindAll", "123456");
    	adminTestFindAll = this.administratorRepository.save(adminTestFindAll);
    	int sizeB = this.administratorService.findAll().size();
    	assertEquals(sizeB - sizeA, 1, "Message : assertEquals(sizeB - sizeA, 1) is false");
    	this.administratorRepository.delete(adminTestFindAll);
    	sizeB = this.administratorService.findAll().size();
    	assertEquals(sizeB - sizeA, 0, "Message : assertEquals(sizeB - sizeA, 0) is false");
	}
    
    @Test
	@DisplayName("Test : findByName()")
	void testFindByName() {
		System.out.println("Test : findByName()");
    	Administrator adminTestFindByName = new Administrator("nameTestFindByName", "123456");
		adminTestFindByName = this.administratorRepository.save(adminTestFindByName);
    	assertNotNull(adminTestFindByName, "Message : assertNotNull(adminTestFindByName) is null");
		adminTestFindByName = this.administratorRepository.findByName("nameTestFindByName");
    	assertNotNull(adminTestFindByName, "Message : assertNotNull(adminTestFindByName) is null");
    	assertEquals(adminTestFindByName.getName(), "nameTestFindByName", "Message : assertEquals(adminTestFindByName.getName(), \"nameTestFindByName\") is false");
    	this.administratorRepository.delete(adminTestFindByName);
    	assertNull(this.administratorRepository.findByName("nameTestFindByName"), "Message : assertNull(this.administratorRepository.findByName(\"nameTestFindByName\")) is not null");   	
	}
    
    @Test
	@DisplayName("Test : existId()")
	void testExistId() {
		System.out.println("Test : existId()");
    	Administrator adminTestExistId = new Administrator("nameTestExistId", "123456");
		adminTestExistId = this.administratorRepository.save(adminTestExistId);
    	assertNotNull(adminTestExistId, "Message : assertNotNull(adminTestExistId) is null");
    	Long id = adminTestExistId.getId();
    	assertTrue(this.administratorService.existId(id), "Message : assertTrue(this.administratorService.existId(id)) is false");
    	this.administratorRepository.delete(adminTestExistId);
    	assertFalse(this.administratorService.existId(id), "Message : assertTrue(this.administratorService.existId(id)) is true");    	
    }
    
    @Test
	@DisplayName("Test : getById()")
	void testGetById() {
		System.out.println("Test : getById()");
    	Administrator adminTestGetById = new Administrator("nameTestGetById", "123456");
		adminTestGetById = this.administratorRepository.save(adminTestGetById);
    	assertNotNull(adminTestGetById, "Message : assertNotNull(adminTestGetById) is null");
    	Long id = adminTestGetById.getId();
    	Administrator adminControle = this.administratorService.getById(id);
    	assertEquals(adminTestGetById.getId(), adminControle.getId(), "Message : assertEquals(adminTestGetById, adminControle) is false");
    	this.administratorRepository.delete(adminTestGetById);
    	assertEquals(this.administratorRepository.findById(id), Optional.empty());
    }
    
	
	@Test
	@DisplayName("Test : createAdministrator()")
	void testCreateAdministrator() {
		System.out.println("Test : createAdministrator()");
		// test01 create
		administrators = new ArrayList<Administrator>();
		String name = "test01";
		String password = "pass";
		int sizeA = administratorService.findAll().size();
		Administrator test01 = this.administratorService.createAdministrator(name, password);
		adminsAfterDelete.add(test01);
		administrators = administratorService.findAll();
		int sizeB = administrators.size();
		assertNotNull(test01, "Message : assertNotNull(test01) is null");
		assertEquals(test01.getName(), "test01", "Message : assertEquals(test01.getName(), \"test01\") is false");
		assertNotNull(administrators, "Message : assertNotNull(administrators) is null");
		assertEquals(sizeB-sizeA, 1, "Message : assertEquals(sizeB-sizeA, 1) is false");
		// test02 create with exist name
		name = "test01";// test same name
		password = "pass";
		Administrator test02 = this.administratorService.createAdministrator(name, password);
		administrators = administratorService.findAll();
		sizeB = administrators.size();
		assertEquals(sizeB-sizeA, 1, "Message : assertEquals(sizeB-sizeA,1) is false");
		// test02 create with new name
		name = "test02";// test new name
		test02 = this.administratorService.createAdministrator(name, password);
		adminsAfterDelete.add(test02);
		administrators = administratorService.findAll();
		sizeB = administrators.size();
		assertNotNull(test02, "Message : assertNotNull(test02) is null");
		assertEquals(test02.getName(), "test02", "Message : assertEquals(test02.getName(), \"test02\") is false");
		assertNotNull(administrators, "Message : assertNotNull(administrators) is null");
		assertEquals(sizeB-sizeA, 2, "Message : assertEquals(sizeB-sizeA, 2) is false");
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
		System.out.println("0- sizeA " + sizeA);
		Administrator test03 = this.administratorService.createAdministrator(name, password);
		adminsAfterDelete.add(test03);
		Long idTest03 = test03.getId();
		assertTrue(this.administratorService.existId(idTest03), "Message : assertTrue(this.administratorService.existId(idTest03))");
		int sizeB = this.administratorService.findAll().size();
		assertEquals(sizeB-sizeA, 1, "Message : assertEquals(sizeB-sizeA,1) is false");
		// test04 create		
		name = "test04";
		password = "pass";
		Administrator test04 = this.administratorService.createAdministrator(name, password);
		adminsAfterDelete.add(test04);
		Long idTest04 = test04.getId();
		assertTrue(this.administratorService.existId(idTest04), "Message : assertTrue(this.administratorService.existId(idTest04)) is false");
		assertNotNull(administrators, "Message : assertNotNull(administrators)is null");
		sizeB = this.administratorService.findAll().size();
		assertEquals(sizeB-sizeA, 2, "Message : assertEquals(sizeB-sizeA, 2) is false");
		// test03 update exist name		
        this.administratorService.updateAdministrator(idTest03, "test04", "pass");
		sizeB = this.administratorService.findAll().size();
		assertEquals(sizeB-sizeA, 2, "Message : assertEquals(sizeB-sizeA, 2) is false");
        assertTrue(this.administratorService.getById(idTest03).getName().equals("test03"), "Message : assertTrue(this.administratorService.getById(idTest03).getName().equals(\"test03\")) is false");
		// test03 update new name		        
        this.administratorService.updateAdministrator(idTest03, "update_test", "pass");
		sizeB = this.administratorService.findAll().size();
		assertEquals(sizeB-sizeA, 2, "Message : assertEquals(sizeB-sizeA, 2) is false");
        assertTrue(this.administratorService.getById(idTest03).getName().equals("update_test"), "Message : assertTrue(this.administratorService.getById(idTest03).getName().equals(\"update_test\")) is false");
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
		//adminsAfterDelete.add(test05);
		Long idTest05 = test05.getId();		
		assertTrue(this.administratorService.existId(idTest05), "Message : assertTrue(this.administratorService.existId(idTest05)) is false");
		int sizeB = this.administratorService.findAll().size();
		assertEquals(sizeB-sizeA, 1, "Message : assertEquals(sizeB-sizeA, 1) is false");		
		// test05 delete
		this.administratorService.deleteAdministrator(idTest05);
		sizeB = this.administratorService.findAll().size();
		assertEquals(sizeB-sizeA, 0, "Message : assertEquals(sizeB-sizeA, 0) is false");
		assertTrue(!this.administratorService.existId(idTest05), "Message : assertTrue(!this.administratorService.existId(idTest05)) is false");
		// test delete fake id
		this.administratorService.deleteAdministrator(idTest05);
		sizeB = this.administratorService.findAll().size();
		assertEquals(sizeB-sizeA, 0, "Message : assertEquals(sizeB-sizeA, 0) is false");
    }	
}
