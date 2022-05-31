package fr.baidem.userList.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.baidem.userList.model.User;

public interface UserRepository  extends JpaRepository<User, Long> {
	
	public List<User> findAll();
	
	public List<User> findBy_firstName();
	
	public List<User> findBy_lastName();
	
	public List<User> findBy_administrator();
	
}
