package fr.baidem.userList.service.dto;


import fr.baidem.userList.model.Administrator;

public class AdministratorDTO {
	
	public String name;
	public String password;

	public AdministratorDTO() {
	}

	public AdministratorDTO(String name) {
		this.name = name;
	}
	
	public AdministratorDTO(String name, String password) {
		this(name);
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
    public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public AdministratorDTO copyAdministrator(Administrator administrator){
        
        AdministratorDTO administratorDTO=new AdministratorDTO(administrator.getName());

        return administratorDTO;
    }
	

}
