# Projet Alimentation M2Miage

### Technologies
<ol>
<li>Backend Java : </li>
    - Spring
    - Maven
<li>Frontend : Angular</li>
<li>Base de données: MySQL et Json</li>  
<li>Tests: JUnit et Mockito</li>
<li>Versionning et partgage de code: GitHub</li>
<li>Intégration continue: Jenkins</li> 
    - Lien: http://52.232.47.118:8080/
    - Connexion: login/password
<li>Suivi des issues : GitHub Boards </li> 
<li>Analyse du code: Codacy</li>
<li>Test Coverage : JaCoCo Code Coverage </li>
<li>Déploiement : Microsoft Azure </li>
</ol>


### Comment utiliser DietiCourse
Lien disponible pour accéder à la plateforme DietiCourse : https://dieticourse-frontend.azurewebsites.net/

Lien pour tester les fonctionnalités backend: https://dieticourse-backend.azurewebsites.net/swagger-ui/

#### Fonctionnalités disponibles dans le frontend

  - Connexion : codes de connexion! login: admin password: password
  - Menus: Une page avec 2 menus/jour pendant une semaine (7 jours).
     <ul> Regénérer une nouvelle recette en cliquant sur l'ic$one regénérer en haut à droite de la photo </ul>
     <ul> Filtrer les recettes en fonction des restrictions alimentaire (Végétarien - Végétalien - Sans Gluten - Sans lactose </ul>
     <ul> Afficher le détail d'une recette en cliquant dessus une popup s'afffiche </ul>
     <ul> Ajouter un ou plusieurs produits d'une recette dans le panier de course </ul>
  - Panier: une page contenant les produits OpenFoodFacts correspondant (au mieux) à nos recettes.

#### Fonctionnalités seulement en backend
  - Suivi "Monitoring-controller /addMonitoring" : ajout du poids - note de la diet - note de l'état mental les trois paramètre s'ajoutent à la base de données associé à la date d'ajout afin de pouvoir ensuite mettre en place une courbe de suivi. Le frontEnd de cette fonctionnalité contient encore des bugs (voir ticket #23). 
  - Fonctionnalité d'ajout de repas au json dans "DietController /saveDiet" : ajout d'un repas au format json 

