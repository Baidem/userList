package fr.baidem.userList.service.dto;

import java.time.LocalDate;

import fr.baidem.userList.model.User;


public class UserDTO {
	
	private String firstName;
	private String lastName;
	private LocalDate birthday;
	private LocalDate enterDate;
	private String email;
	private String phone;
	private String address;
	private Long adminId;
	
	public UserDTO() {	}

	public UserDTO(String firstName, String lastName, LocalDate birthday,LocalDate enterDate, String email, String phone, String address, Long adminId) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthday = birthday;
		this.enterDate = enterDate;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.adminId = adminId;
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

	public Long getAdminId() {
		return adminId;
	}

	public void setAdminId(Long adminId) {
		this.adminId = adminId;
	}
	
	public LocalDate getEnterDate() {
		return enterDate;
	}

	public void setEnterDate(LocalDate enterDate) {
		this.enterDate = enterDate;
	}

	public UserDTO copyUser(User user){
		AdministratorDTO administratorDto = new AdministratorDTO();
	 	Long administratorId = administratorDto.getId(user.getAdministrator());
	        
	 	UserDTO userDTO = new UserDTO(user.getFirstName(), user.getLastName(), user.getBirthday(), user.getEnterdate(), user.getEmail(), user.getPhone(), user.getAddress(), administratorId);

	 	return userDTO;
	}
}
