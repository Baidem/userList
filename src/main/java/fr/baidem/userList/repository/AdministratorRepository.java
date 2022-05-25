package fr.baidem.userList.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.baidem.userList.model.Administrator;

@Repository
public interface AdministratorRepository extends JpaRepository<Administrator, Long> {
	
	Optional<Administrator> findByName(String name);
		
}
