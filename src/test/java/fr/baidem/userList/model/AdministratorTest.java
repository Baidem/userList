package fr.baidem.userList.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("Test : class Administrator ")
class AdministratorTest {

	String name;
    String password;
    Administrator administrator;

    @BeforeAll
    public void setup() {
    	System.out.println("@BeforeAll");
        name = "name";
        password = "password";
    }

    @AfterAll
    public void teardown() {
    	System.out.println("@BeforeAll");
    	name = null;
        password = null;
    	administrator = null;
    }

    @Test
    public void newAdministratorTest() {
    	System.out.println("@Test : newAdministratorTest()");
    	administrator = new Administrator();
    	assertNotNull(administrator, "Message : assertNotNull(administrator) is null");
    	assertEquals(administrator.getName(), null, "Message : assertEquals(administrator.getName(), null) is not null");
    	assertEquals(administrator.getPassword(), null, "Message : assertEquals(administrator.getPassword(), null) is not null");
    	administrator = new Administrator("", "");
    	assertEquals(administrator.getName(), "", "Message : assertEquals(administrator.getName(), \"\") is not \"\"");
    	assertEquals(administrator.getPassword(), "", "Message : assertEquals(administrator.getPassword(), \"\") is not \"\"");
    	administrator.setName(name);
    	administrator.setPassword(password);
    	assertEquals(administrator.getName(), name, "Message : assertEquals(administrator.getName(), name) is not name");
    	assertEquals(administrator.getPassword(), password, "Message : assertEquals(administrator.getPassword(), password) is not password");
    }

}
