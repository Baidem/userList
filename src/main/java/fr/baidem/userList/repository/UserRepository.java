package fr.baidem.userList.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.baidem.userList.model.*;

public interface UserRepository  extends JpaRepository<User, Long> {
	
	public User getById(Long id);
	
	public List<User> findAll();
	
	public List<User> findByAdministrator_Id(Long id);	
	
	public List<User> findByEmail(String email);
		
	@Query("select u from User u where u.firstName= :firstName and u.administrator= :id")
	List<User> findByFirstNameAndAdminId(String firstName, Long id);

	@Query("select u from User u where u.lastName= :lastName and u.administrator= :id")
	List<User> findByLastNameAndAdminId(String lastName, Long id);

	@Query("select u from User u where u.birthday= :birthday and u.administrator= :id")
	List<User> findByBirthdayAndAdminId(LocalDate birthday, Long id);

	@Query("select u from User u where u.enterDate= :enterDate and u.administrator= :id")
	List<User> findByEnterDateAndAdminId(LocalDate enterDate, Long id);
	
	@Query("select u from User u where u.email= :email and u.administrator= :id")
	User findByEmailAndAdminId(String email, Long id);

	@Query("select u from User u where u.phone= :phone and u.administrator= :id")
	List<User> findByPhoneAndAdminId(String phone, Long id);
	
	@Query("select u from User u where u.address= :address and u.administrator= :id")
	List<User> findByAddressAndAdminId(String address, Long id);

}
