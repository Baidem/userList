package fr.baidem.userList.model;

import java.time.LocalDate;
import javax.persistence.*;
import javax.validation.constraints.*;


@Entity
@Table(name = "users")

public class User {

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="first_name", length=150)
	@NotBlank
	private String firstName;
	
	@Column(name="last_name", length=150)
	@NotBlank
	private String lastName;
	
	@Column(name="birthday", length=150)
	private LocalDate birthday;
	
	@Column(name="email", length=150, unique = true, nullable = false)
	@NotBlank
	@Email
	private String email;

	@Column(name="phone", length=150)
	private String phone;

	@Column(name = "address", columnDefinition = "TEXT")
	private String address;
	
	@JoinColumn(name = "admin_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private Administrator administrator;

	public User() {
	}

	public User(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public User(String firstName, String lastName, LocalDate birthday, String email, String phone, String address) {
		this(firstName, lastName);
		this.birthday = birthday;
		this.email = email;
		this.phone = phone;
		this.address = address;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Administrator getAdministrator() {
		return administrator;
	}

	public void setAdministrator(Administrator administrator) {
		this.administrator = administrator;
	}
	
	
	
}


