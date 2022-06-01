package fr.baidem.userList.service.web;

import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import fr.baidem.userList.model.Administrator;
import fr.baidem.userList.service.AdministratorService;
import fr.baidem.userList.service.dto.AdministratorDTO;

@RestController
@RequestMapping("administrators")
public class AdministratorController {
	
	Logger logger = LoggerFactory.getLogger(AdministratorController.class);
	private AdministratorService administratorService;

	@Autowired
	public AdministratorController(AdministratorService administratorService) {
		this.administratorService = administratorService;
	}
	
	@GetMapping()
	public ResponseEntity<?> getAdministrators(){
		try {
			List<Administrator> administretors = administratorService.findAll();
			List<AdministratorDTO> administratorDTOS=new LinkedList<AdministratorDTO>();
			AdministratorDTO administratorDTO=new AdministratorDTO();
			for(Administrator administrator:administretors){
				administratorDTOS.add(administratorDTO.copyAdministrator(administrator));
			}
			return new ResponseEntity<>(administratorDTOS, HttpStatus.OK);
		} catch (Exception e){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("{id}")
	public ResponseEntity<?> getAdministratorById(@PathVariable Long id){
		try {
			logger.info("Administrator : {}", id);
			Administrator findAdministrator =  administratorService.getById(id);
			if(findAdministrator == null){
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			AdministratorDTO administratorDTO=new AdministratorDTO();
			return new ResponseEntity<> (administratorDTO.copyAdministrator(findAdministrator), HttpStatus.OK);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
		}
	}
	
	@PostMapping()
	public ResponseEntity<?> addAdministrator(@RequestBody AdministratorDTO administratorDTO){

		try{
			Administrator createdAdministrator = administratorService.createAdministrator(administratorDTO.getName(), administratorDTO.getPassword());
			AdministratorDTO administratorDTO1=new AdministratorDTO();
			return new ResponseEntity<Object>(administratorDTO1.copyAdministrator(createdAdministrator), HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error to create administrator", e);
		}

	}

	
	
	
}
