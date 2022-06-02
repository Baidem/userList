package fr.baidem.userList.Service;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import fr.baidem.userList.model.Administrator;
import fr.baidem.userList.model.User;
import fr.baidem.userList.repository.UserRepository;
import fr.baidem.userList.service.UserService;

@SpringBootTest
@DisplayName("UserService's test ")
class UserServiceTest {
	@Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    private List<User> users; 
    
    @BeforeAll
    static void initAll() {
    	System.out.println("@BeforeAll");
    }
     
	@Test
	@DisplayName("createUser()'s test")
	public void test() {
		String firstName = "Jojo";
		String lastName = "Coco";
		LocalDate birthday=LocalDate.of(2000,1,1);
		LocalDate enterDate = LocalDate.of(2020,1,1);
		String email = "jojo.coco@gmail.com";
		String phone = "5555-1237";
		String address = "address of Jojo";
		Administrator admin = new Administrator("Admin testeur", "XXXXXX");
		
		User user = this.userService.createUser(firstName, lastName, birthday, enterDate, email, phone, address, admin.getId());
		users = userService.findAll();
		assertNotNull(user);
		assertTrue(user.getEmail().equals("jojo.coco@gmail.com"));
		assertNotNull(users);
		assertEquals(users.size(),1);
		firstName = "Lili";
		lastName = "Roro";
		birthday=LocalDate.of(2000,1,2);
		enterDate = LocalDate.of(2020,1,2);
		email = "jojo.coco@gmail.com";// test same email
		phone = "5555-1238";
		address = "address of Lili";
		User user02 = this.userService.createUser(firstName, lastName, birthday, enterDate, email, phone, address, admin.getId());
        assertNotNull(user02);
		users = userService.findAll();
        assertEquals(users.size(),1);
		email = "lili.roro@gmail.com";// test new email
		user02 = this.userService.createUser(firstName, lastName, birthday, enterDate, email, phone, address, admin.getId());
		assertNotNull(user02);
		users = userService.findAll();
        assertEquals(users.size(),2);

	}

}
