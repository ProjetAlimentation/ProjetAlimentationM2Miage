# Plateforme DietiCourse

### Technologies
<ol>
<li>Backend Java : Spring et Maven</li>
<li>Frontend : Angular</li>
<li>Base de données: MySQL et Json</li>  
<li>Tests: JUnit et Mockito</li>
<li>Versionning et partgage de code: GitHub</li>
<li>Intégration continue: Jenkins : accessible au lien http://52.232.47.118:8080/ (connexion avec login/password) </li> 
<li>Suivi des issues : GitHub Boards </li> 
<li>Analyse du code: Codacy</li>
<li>Test Coverage : JaCoCo Code Coverage (13% de coverage)</li>
<li>Déploiement : Microsoft Azure </li>
</ol>


### Comment utiliser DietiCourse
Lien disponible pour accéder à la plateforme DietiCourse : https://dieticourse-frontend.azurewebsites.net/

Lien pour tester les fonctionnalités backend: https://dieticourse-backend.azurewebsites.net/swagger-ui/

#### Fonctionnalités disponibles dans le frontend

  - Connexion : formulaire de connexion standard (à tester avec admin/password)
  - Menus: La page contenant la diète de l'utilisateur avec 2 repas/jour sur une semaine.
     <ul> Filtrer les recettes en fonction du profil alimentaire (Végétarien - Végétalien - Sans Gluten - Sans lactose) </ul>
     <ul> Afficher le détail d'une recette en cliquant dessus </ul>
     <ul> Afficher les produits OpenFoodFacts correspondant (au mieux) à chacune des recettes </ul>
     <ul> Regénérer un nouveau repas en cliquant sur l'icône regénérer en haut à gauche de chaque recette </ul>
     <ul> Ajouter un ou plusieurs produits d'une recette dans le panier de course en cliquant sur le bouton "cadie" du produit </ul>
  - Panier: une page contenant les produits ajoutés à notre panier : possibilité de supprimer un ou plusieurs produits

#### Fonctionnalités seulement en backend
  - Suivi "Monitoring-controller /addMonitoring" : ajout du poids - note de la diet - note de l'état mental les trois paramètre s'ajoutent à la base de données associé à la date d'ajout afin de pouvoir ensuite mettre en place une courbe de suivi. Le frontEnd de cette fonctionnalité contient encore des bugs (voir ticket #23). 
  - Fonctionnalité d'ajout de repas au json dans "DietController /saveDiet" : ajout d'un repas au format json 

