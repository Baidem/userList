package fr.baidem.userList.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import fr.baidem.userList.model.Administrator;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("Test : class Administrator ")
class AdministratorRepositoryTest {

	@Autowired
	AdministratorRepository administratorRepository;
	List<Administrator> adminsForDelete = new ArrayList<Administrator>();
	
	@BeforeAll
	public void setup() {
    	System.out.println("@BeforeAll");    
    }

    @AfterAll
    public void teardown() {
    	System.out.println("@AfterAll");
    	for (Administrator administrator : adminsForDelete) {
    		administratorRepository.delete(administrator);
    	} 	
    }
	
	@Test
	public void testSave() {
    	System.out.println("Test : save()");
		Administrator administrator01 = new Administrator("saveTest", "123456");
		administrator01 = this.administratorRepository.save(administrator01);
		adminsForDelete.add(administrator01);
		assertNotNull(administrator01);
	}
	
	@Test
	public void testFindAll() {
    	System.out.println("Test : findAll()");
    	List<Administrator> administrators = new ArrayList<Administrator>();
    	int sizeA =  this.administratorRepository.findAll().size();
		Administrator administrator02 = new Administrator("findAllTest", "123456");
		administrator02 = this.administratorRepository.save(administrator02);
		adminsForDelete.add(administrator02);
		administrators = this.administratorRepository.findAll();
		assertNotNull(administrators, "Message : assertNotNull(administrators) is null");
		assertEquals((administrators.size() - sizeA), 1, "Message : assertEquals( administrators.size() - sizeA, 1) is not equal to 1");
	}
	
	@Test
	public void testFindByName() {
		System.out.println("Test : findByName()");
		Administrator administrator03 = new Administrator("findByNameTest", "123456");
		administrator03 = this.administratorRepository.save(administrator03);
		adminsForDelete.add(administrator03);
		Administrator adminFinded = this.administratorRepository.findByName("findByNameTest");
		//System.out.println(adminFinded.getId());
		assertNotNull(adminFinded, "Message : assertNotNull(adminFinded) is null");
	}
	
	@Test
	public void testDelete() {
		System.out.println("Test : delete()");
		Administrator administrator04 = new Administrator("deleteTest", "123456");
		administrator04 = this.administratorRepository.save(administrator04);
		int sizeA = this.administratorRepository.findAll().size();
		administratorRepository.delete(administrator04);
		int sizeB = this.administratorRepository.findAll().size();
		//System.out.println(sizeB - sizeA);
		assertEquals(sizeB - sizeA, -1, "Message : assertEquals(sizeB - sizeA, -1) is false");
	}
	
}
