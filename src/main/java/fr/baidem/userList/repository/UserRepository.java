package fr.baidem.userList.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.baidem.userList.model.*;

public interface UserRepository  extends JpaRepository<User, Long> {
	
	public List<User> findAll();
	
	public List<User> findByFirstName(String firstName);
	
	public List<User> findByLastName(String lastName);
	
	public List<User> findByAdministrator_Id(Long id);
	
}
