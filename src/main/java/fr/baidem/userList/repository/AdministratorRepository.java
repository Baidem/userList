package fr.baidem.userList.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.baidem.userList.model.Administrator;

@Repository
public interface AdministratorRepository extends JpaRepository<Administrator, Long> {
	
	public List<Administrator> findAll();
	
	Administrator findByName(String name);
			
}
