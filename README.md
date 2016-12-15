<center>
## Helios <br> Micro plateforme pédagogique en ligne
</center>



### Architecture 

Le livrable est un Entreprise Application Project nommé  `heliosEA`. 
Il comporte un projet web dynamique `heliosClient` et un projet EJB `heliosEJB`. Nous détaillons ici la mise en oeuvre architecturale de ces deux derniers composants.

#### heliosWeb
    
La partie web englobe 4 servlets, chacun dédié à certains types d'actions. 

En particulier, nous avons :

* GlobalController : 

Contrôleur par défaut en charge de l'authentification et de la déconnexion des utilisateurs. Lorsqu'un utilisateur est authentifié, ce contrôleur permet l'affichage d'informations le concernant au sein de son espace personnel. Une fois authentifié, il redirige l'utilisateur dans son servlet correspondant à son groupe: admin, student ou teacher.

* AdminController :  

Contrôleur permettant à l'administrateur d'effectuer l'ensemble de ces actions à savoir créer un compte d'utilisateur et lister ceux existant. 

* StudentController : 

Imlplémente les tâches dévolues aux utilisateurs étudiants : inscription à un module, lecture des chapitres, réalisation des QCMs, consultation de l'état d'avancement. 

* Teacher Controller : 

Permet la création de modules, chapitres et QCMs par un utilisateur authentifié comme étant un professeur. Ce controlleur implémente aussi la possibilité de consulter les statistiques d'un chapitre (nombre d'inscrits, date d'inscription, niveau d'avancement et date de validation d'un QCM avec le nombre d'essais nécessaires). 

L'application comporte additionellement un package nommé *tools* comportant différentes méthodes de vérification des champs textuels.

#### heliosEJB

La partie "application métier" se trouve dans le projet heliosEJB. Elle comporte des *Session Beans* (package *beans*) et des *Entity Beans* (package *entities*) permettant d'interagir avec la base de données. La totalité des données contenues dans chaque entité est utilisée dans les servlets, il a donc été décidé de ne pas utiliser de DTO.

##### Gestion des utilisateurs

La gestion des utilisateurs s'effectue au travers du session bean *UsersManager* qui manipule des entity beans *User* et couvre donc des opérations telles que créer un utilisateur, s'authentifier et lire des informations le concernant.  
L'entité *User* comporte des données génériques d'un utilisateur (nom, prénom, mail, nom d'utilisateur et mot de passe) ainsi qu'un nom de groupe correspondant au type d'utilisateur, pouvant prendre comme valeurs *student*, *teacher* ou *admin*.

##### Gestion des modules

La gestion des modules s'effectue au travers du session bean *ModulesManager* et des entity beans *Modules*, *Chapter* et *Subscription*.  
L'entité *Subscription* reflète une table de correspondance dans la base de données permettant de lier les étudiants aux modules tout en conservant des informations telles que la date d'inscription d'un étudiant à un module et sa progression.  
*ModuleManager* sert donc à effectuer la création de modules, la rédaction de chapitres et l'inscription d'un élève à un module au travers de *Subscription*.

##### Gestion des QCMs  

La gestion des QCMs s'effectue au travers du session bean *QCMsManager* et des entités *QCM*, *QCMInstance*, *Question* et *Answer*.  
*QCMsManager* permet la création de QCMs par les professeurs et la réponse du côté étudiant. 
Un *QCM* est composé d'un ensemble de questions, d'une date d'expiration, d'une note totale et d'une note minimale permettant de déterminer si un étudiant a validé le QCM. La validation d'un QCM entraine la possibilité d'un étudiant à poursuivre un cours (lire le chapitre suivant et effectuer le QCM correspondant).  
La complétion d'un QCM par un étudiant est représentée par *QCMInstance*, qui renseigne la date de complétion, la note obtenue, le nombre d'essais et sa validité par rapport à la note minimale d'obtention.

### Répartition des tâches

Les tâches de conception, rédaction du rapport, de la définition du modèle de données et de l'architecture ont été réalisées en commun.  
La phase d'implémentation a été répartie par servlet, avec d'une part StudentController et GlobalController (Alban) et d'autre part AdminController et teacherController (Lisa).
Le dépot de source en ligne peut être retrouvé à l'adresse suivante: [https://github.com/achazot/helios](https://github.com/achazot/helios)

### Utilisation 

Une fois déployé, vous pouvez accéder à la page d'accueil du projet à l'url suivante : <a ref="http://localhost:8080/helios">http://localhost:8080/helios</a>. 

La base de données est d'ores et déjà peuplée de valeurs rencensées dans le tableau ci-dessous.
<center>
<table style="text-align:center;">
    <tr>
        <td> Identifiant </td>
        <td> Mot de Passe </td>
        <td> Groupe d'appartenance </td>
    </tr>
    <tr>
        <td> albus </td>
        <td> azerty </td>
        <td style="background-color: #FFD573;"> teacher </td>
    </tr> 
    <tr>
        <td> minerva </td>
        <td> azerty </td>
        <td style="background-color: #FFD573;"> teacher </td>
    </tr>
    <tr>
        <td> harry </td>
        <td> azerty </td>
        <td style="background-color: #FF773D;"> student </td>
    </tr>
    <tr>
        <td> hermione </td>
        <td> azerty </td>
        <td style="background-color: #FF773D;"> student </td>
    </tr>
    <tr>
        <td> ron </td>
        <td> azerty </td>
        <td style="background-color: #FF773D;"> student </td>
    </tr>
        <tr>
        <td> admin </td>
        <td> azerty </td>
        <td style="background-color: #F73E3E;"> admin </td>
    </tr>
</table>
</center>
