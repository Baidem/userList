package fr.baidem.userList.Service;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.charset.Charset;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import fr.baidem.userList.model.Administrator;
import fr.baidem.userList.model.User;
import fr.baidem.userList.repository.AdministratorRepository;
import fr.baidem.userList.repository.UserRepository;
import fr.baidem.userList.service.AdministratorService;
import fr.baidem.userList.service.UserService;

@SpringBootTest
@DisplayName("UserService's test ")
class UserServiceTest {
	@Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;
    
    @Autowired
    AdministratorRepository administratorRepository;
    
    @Autowired
    AdministratorService administratorService;

    private List<User> users = new ArrayList<User>();;
    private List<Long> usersIdAfterDelete = new ArrayList<Long>();
    Administrator fakeAdmin = null;
    
    public static LocalDate between(LocalDate startInclusive, LocalDate endExclusive) {
        long startEpochDay = startInclusive.toEpochDay();
        long endEpochDay = endExclusive.toEpochDay();
        long randomDay = ThreadLocalRandom
          .current()
          .nextLong(startEpochDay, endEpochDay);

        return LocalDate.ofEpochDay(randomDay);
    }
    
    public String ramdomString() {   	 
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int) 
              (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        return buffer.toString();
    }
    
    @BeforeEach
    private void initData() {
    	System.out.println("@BeforeEach");
    	fakeAdmin = this.administratorService.createAdministrator("test01", "pass");
    	usersIdAfterDelete = new ArrayList<Long>();
    	for(int i = 0; i<3; i++) {
    		User userRandom = new User(ramdomString(), ramdomString(), LocalDate.now(), LocalDate.now(), ramdomString()+"@gmail.com", ramdomString(), ramdomString(), fakeAdmin);
    		userRandom = this.userRepository.save(userRandom);
    		usersIdAfterDelete.add(userRandom.getId());
    		userRandom.setAdministrator(fakeAdmin);
    	}
    	System.out.print("==> userRandom id : ");
    	for (Long id : usersIdAfterDelete) {
			System.out.print(id + ", ");
		}
    	System.out.println("");
	}
    
    @AfterEach
	public void destruct() {
    	System.out.println("@AfterEach");
    	for (Long adminId : usersIdAfterDelete) {
    		this.userService.deleteAdministrator(adminId);			
		}
    	administratorRepository.delete(fakeAdmin);    	
	}
    
	@Test
	@DisplayName("Test : createUser()")
	public void test() {
		System.out.println("Test : createUser()");
		// test01 create
		String firstName = "Nametest01";
		String lastName = "Familytest01";
		LocalDate birthday=LocalDate.of(2000,1,1);
		LocalDate enterDate = LocalDate.of(2020,1,1);
		String email = "nametest01.familytest01.com";
		String phone = "5555-0001";
		String address = "addresstest01";
		User user = this.userService.createUser(firstName, lastName, birthday, enterDate, email, phone, address, fakeAdmin.getId());
		usersIdAfterDelete.add(user.getId());
		users = userService.findAll();
		assertNotNull(user);
//		assertTrue(user.getEmail().equals("name01.family01.com"));
//		assertNotNull(users);
//		assertEquals(users.size(),1);
//		
//		firstName = "Lili";
//		lastName = "Roro";
//		birthday=LocalDate.of(2000,1,2);
//		enterDate = LocalDate.of(2020,1,2);
//		email = "jojo.coco@gmail.com";// test same email
//		phone = "5555-1238";
//		address = "address of Lili";
//		User user02 = this.userService.createUser(firstName, lastName, birthday, enterDate, email, phone, address, admin.getId());
//        assertNotNull(user02);
//		users = userService.findAll();
//        assertEquals(users.size(),1);
//		email = "lili.roro@gmail.com";// test new email
//		user02 = this.userService.createUser(firstName, lastName, birthday, enterDate, email, phone, address, admin.getId());
//		assertNotNull(user02);
//		users = userService.findAll();
//        assertEquals(users.size(),2);

	}

}
