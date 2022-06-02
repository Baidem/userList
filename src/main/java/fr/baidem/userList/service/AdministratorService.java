package fr.baidem.userList.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.baidem.userList.model.Administrator;
import fr.baidem.userList.repository.AdministratorRepository;

@Service
public class AdministratorService {
	
	private AdministratorRepository administratorRepository;
	
	@Autowired
	public AdministratorService(AdministratorRepository administratorRepository) {
		this.administratorRepository = administratorRepository;
	}
	
	public List<Administrator> findAll(){
		return administratorRepository.findAll();
	}
	
	public Administrator findByName(String name) {
		if(name == null) {
			return null;	
			}
		return this.administratorRepository.findByName(name);
	}
	
	public boolean existId(Long id) {
		List<Administrator> administrators = this.administratorRepository.findAll();
		for(Administrator administrator:administrators) {
			if(administrator.getId() == id){
				return true;
			}
		}
		return false;
	}

	public Administrator getById(Long id) {
		return administratorRepository.findById(id).orElseThrow();

	}

	@Transactional
	public Administrator createAdministrator(String name, String password) {
		if (name == null){ 
			return null; 
			}
		if (this.findByName(name) != null) { 
			return this.findByName(name);
			}
		
		Administrator administrator = new Administrator(name, password);
		
		administrator = this.administratorRepository.save(administrator);
		return administrator;
	}
	
	@Transactional
	public void updateAdministrator(Long id, String name, String password) {
		if (name != null && this.existId(id)) {
			Administrator a = this.findByName(name);
			if (a == null || a.getId() == id) {
				Administrator administrator = this.getById(id);
				administrator.setName(name);
				administrator.setPassword(password);
				administrator = this.administratorRepository.save(administrator);	
			}		
		}
	}
	
	@Transactional
	public void deleteAdministrator(Long id) {
		Administrator administrator = this.getById(id);
		if (administrator != null) {
			this.administratorRepository.delete(administrator);
		}
	}
}
