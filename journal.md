# 
### 23/05/2022
Premier sujet : faire une base d'utilisateur
## TODO
- Créer un projet github
    - Repository name   : userList
    - Description       : User list manager
    - Public ou Private : Public
    - Add a README file : yes
    - Licence           : MIT
    - Créer le projet   : OK
    - Rédiger le Readme : Besoins client
    - Màj gitignore     : OK
    - importer projet   : git clone 
- Créer un projet maven
    - Spring initializr (https://start.spring.io/)
        - Project           : Maven Project
        - Language          : Java
        - Spring Boot       : 2.7.0
        - Project Metadata  
            - Group         : fr.baidem
            - Artifact      : userList
            - Name          : userlist
            - Description   : User list manager
            - Package name  : fr.baidem.userList
            - Packaging     : Jar
            - Java          : 17
        - Dependencies      :
            - Spring Web
            - Spring Data JPA
            - H2 Database
        - Generate          : CTRL + entrée
        - Télécharger .zip  : OK
    - Extraction de userList.zip
        : Fichiers déposés dans le répertoire local du projet, je garde le fichier gitignore fait précédemment qui est plus complet.
- Roadmap.md                : Les objectifs pédagogiques        
- journal.md                : Rapport journalier, TODO list...
- Git push                  : commit = Init project
- Faire un post linkedin    : épisode 1 "Premier Client"

# 

### 24/05/2022
Débuter le projet
## TODO
- UML
    - Diagramme utilisateur : OK
    - Diagramme de class : OK
- Backlog
    - Users stories : OK
    - Priorités : OK
    - Sprint : priorité 0
- Git
    - Importation du projet sur Eclipse : OK
    - Créer branches dev et model
- Code
    - pom.xml : ajout dépendance de mysql-connector-java, javax.validation
    - application.properties : ajout config mysql et hibernate
    - Model class Administrator : création, table , constructeur, getter setter
    
_NOTE : On utilise pour cela l'attribut fetch, défini sur les annotations @OneToOne, @OneToMany, @ManyToOne et @ManyToMany. Cet attribut peut prendre deux valeurs :_
- _FetchType.LAZY : indique que la relation doit être chargée à la demande ;_
- _FetchType.EAGER : indique que la relation doit être chargée en même temps que l'entité qui la porte._

#  

### 25/05/2022
Administrator
## TODO
- Code
	- AdministratorRepository : OK
	- AdministratorDTO : OK

# 

### 30/05/2022
User
## TODO
- Code
	- Model : User.java : OK

# 

### 31/05/2022
User
## TODO
- Code
    - Repository : UserRepository.java : OK
    - DTO : UserDTO.java AdministratorDTO.java : OK
    - Repository : fix bug : UserRepository.java : OK
       
# 

### 01/06/2022
User + Controller
## TODO
- Code
	- Model : User.java : fix bug : constructor parameters : OK
	- Repository : UserRepository.java	 : add : @Query methods : OK
	- Service : UserService.java : CRUD methods : OK
	- Controller : UserController.java : OK
	- Controller : AdministratorController.java : OK
 	
 
# 

### 02/06/2022
Test
## TODO
- Code
	- Test : UserServiceTest.java : in progress
	- Test : AdministratorServiceTest.java : in progress
	
	
# 

### 04/06/2022
Test
## TODO
- Code
	- Test : AdministratorServiceTest.java : createAdministrator() : OK
	- Test : AdministratorServiceTest.java : updateAdministrator() : OK
	- Test : AdministratorServiceTest.java : deleteAdministrator() : OK
	-
    
#   
  
### 05/06 -> 14/06/2022
Test
## TODO
- Code
	- Test : AdministratorRepositoryTest.java : testSave() : OK
	- Test : AdministratorRepositoryTest.java : testFindAll() : OK
	- Test : AdministratorRepositoryTest.java : testFindByName() : OK
	- Test : AdministratorRepositoryTest.java : testDelete() : OK

	- Test : AdministratorServiceTest.java : testFindByName() : OK
	- Test : AdministratorServiceTest.java : testExistId() : OK
	- Test : AdministratorServiceTest.java : testFindAll() : OK
	- Test : AdministratorServiceTest.java : testGetById() : OK
	- Test : AdministratorServiceTest.java : testCreateAdministrator() : OK
	- Test : AdministratorServiceTest.java : testUpdateUser() : OK
	- Test : AdministratorServiceTest.java : testDeleteAdministrator() : OK
	
	- Service : AdminitratorService.java : update : OK
	- Service : AdministratorRepository.java : update : OK
	
    
#   
    
    
    
    
    
    
    - DTO
    - Controllers
    - Tests
    - Exceptions
    - Loggers


