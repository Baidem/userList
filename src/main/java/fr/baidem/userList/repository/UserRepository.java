package fr.baidem.userList.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.baidem.userList.model.*;

public interface UserRepository  extends JpaRepository<User, Long> {
	
	public User getById(Long id);
	
	public List<User> findAll();
	
	public List<User> findByFirstName(String firstName);
	
	public List<User> findByLastName(String lastName);

	public List<User> findByBirthday(Date birthday);
	
	public List<User> findByEmail(String email);
	
	public List<User> findByPhone(String Phone);
	
	public List<User> findByAddress(String address);
	
	public List<User> findByAdministrator_Id(Long id);
	
	@Query("select u from User u where u.administrator= :id")
	User findTest(Long id);
	
	@Query("select u from User u where u.administrator= :id")
	User findAllByAdminId(Long id);

	@Query("select u from User u where u.firstName= :firstName and u.administrator= :id")
	User findByFirstNameAndAdminId(String firstName, Long id);

	@Query("select u from User u where u.lastName= :lastName and u.administrator= :id")
	User findByLastNameAndAdminId(String lastName, Long id);

	@Query("select u from User u where u.email= :email and u.administrator= :id")
	User findByEmailAndAdminId(String email, Long id);

	@Query("select u from User u where u.phone= :phone and u.administrator= :id")
	User findByPhoneAndAdminId(String phone, Long id);
	
	@Query("select u from User u where u.address= :address and u.administrator= :id")
	User findByAddressAndAdminId(String address, Long id);

}
