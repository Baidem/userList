package fr.baidem.userList.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.baidem.userList.model.Administrator;
import fr.baidem.userList.model.User;
import fr.baidem.userList.repository.UserRepository;

@Service
public class UserService {
	
	private UserRepository userRepository;
	private AdministratorService administratorService;
	
	@Autowired
	public UserService(UserRepository userRepository, AdministratorService administratorService) {
		this.userRepository = userRepository;
		this.administratorService = administratorService;
	}
	
	public List<User> findAll() {
		return userRepository.findAll();
	}
	
	public User getUserById(Long id) {
		return userRepository.getById(id);
	}
	
	public List<User> findAdminUsers(Long adminId) {
		return userRepository.findByAdministrator_Id(adminId);
	}

	public List<User> findAdminUsersByFirstName(String firstName, Long adminId) {
		return userRepository.findByFirstNameAndAdminId(firstName,adminId);
	}

	public List<User> findAdminUsersByLastName(String lastName, Long adminId) {
		return userRepository.findByLastNameAndAdminId(lastName,adminId);
	}
	
	public List<User> findAdminUsersByBirthday(LocalDate birthday, Long adminId) {
		return userRepository.findByBirthdayAndAdminId(birthday,adminId);
	}

	public List<User> findAdminUsersByEnterDate(LocalDate enterDate, Long adminId) {
		return userRepository.findByEnterDateAndAdminId(enterDate,adminId);
	}

	public User findAdminUsersByEmail(String email, Long adminId) {
		if (email == null) {
			return null;
		}
		return userRepository.findByEmailAndAdminId(email,adminId);
	}

	public List<User> findAdminUsersByPhone(String phone, Long adminId) {
		return userRepository.findByPhoneAndAdminId(phone,adminId);
	}
	
	public List<User> findAdminUsersByAddress(String address, Long adminId) {
		return userRepository.findByAddressAndAdminId(address,adminId);
	}
	
	public boolean existId(Long id) {
		List<User> users=this.userRepository.findAll();
		for(User user:users){
			if(user.getId() == id){
				return true;
			}
		}
		return false;
	}
	
	public User findByUserEmail(String email, Long adminId) {
		return this.findAdminUsersByEmail(email, adminId);
	}
	
	@Transactional
	public User createUser(String firstName, String lastName, LocalDate birthday, LocalDate enterDate,String email, String phone, String address, Long adminId) {
		if (email == null){ 
			return null; 
			}
		if (this.findByUserEmail(email, adminId) != null) { 
			return this.findByUserEmail(email, adminId); 
			}
		Administrator administrator = administratorService.getById(adminId);
		User user = new User(firstName, lastName, birthday, enterDate, email, phone, address, administrator);
		
		user = this.userRepository.save(user);
		return user;
	}
	
	@Transactional
	public void updateUser(Long id, String firstName, String lastName, LocalDate birthday, LocalDate enterDate,String email, String phone, String address, Long adminId) {
		if (email != null && this.existId(id)) {
			User u = this.findAdminUsersByEmail(email, adminId);
			if (u == null || u.getId() == id) {
				User user= this.getUserById(id);
				user.setFirstName(firstName);
				user.setLastName(lastName);
				user.setBirthday(birthday);
				user.setEnterdate(enterDate);
				user.setEmail(email);
				user.setPhone(phone);
				user.setAddress(address);
				user = this.userRepository.save(user);	
			}		
		}
	}
	
	@Transactional
	public void deleteAdministrator(Long id) {
		User user = this.getUserById(id);
		if (user != null) {
			this.userRepository.delete(user);
		}
	}

}
