
package fr.baidem.userList.service.web;

import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import fr.baidem.userList.model.User;
import fr.baidem.userList.service.UserService;
import fr.baidem.userList.service.dto.UserDTO;

@RestController
@RequestMapping("users")
public class UserController {

	Logger logger = LoggerFactory.getLogger(UserController.class);
	private UserService userService;
	
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping()
	public ResponseEntity<?> getUsers(@RequestBody Long adminId) {
		try {
			List<User> users = userService.findAdminUsers(adminId);
			List<UserDTO> userDTOs = new LinkedList<UserDTO>();
			UserDTO userDTO = new UserDTO(); 
			for (User user : users) {
				userDTOs.add(userDTO.copyUser(user));
			}		
			return new ResponseEntity<> (userDTOs, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("{id}")
	public ResponseEntity<?> getUserById(@PathVariable Long id){
		try {
			logger.info("User : {}", id);
			User findUser =  userService.getUserById(id);
			if(findUser == null){
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			UserDTO userDTO = new UserDTO();
			return new ResponseEntity<> (userDTO.copyUser(findUser), HttpStatus.OK);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
		}
	}
	
	@PostMapping()
	public ResponseEntity<?> addUser(@RequestBody UserDTO userDTO){

		try{
			User createdUser = userService.createUser(userDTO.getFirstName(),userDTO.getLastName(), userDTO.getBirthday(), userDTO.getEnterDate(), userDTO.getEmail(), userDTO.getPhone(), userDTO.getAddress(), userDTO.getAdminId());
			UserDTO userDTO1 = new UserDTO();
			return new ResponseEntity<Object>(userDTO1.copyUser(createdUser), HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error to create user", e);
		}
	}

	
}
