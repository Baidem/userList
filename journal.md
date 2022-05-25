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
    - Créer le projet   : ok
    - Rédiger le Readme : Besoins client
    - Màj gitignore     : ok
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
        - Télécharger .zip  : ok
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
    - Diagramme utilisateur : ok
    - Diagramme de class : ok
- Backlog
    - Users stories : ok
    - Priorités : ok
    - Sprint : priorité 0
- Git
    - Importation du projet sur Eclipse : ok
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
Débuter le projet
## TODO
- Code
	- Repository Administrator : ok
	
    - Repositories
    - DTO
    - Controllers
    - Tests
    - Exceptions
    - Loggers


