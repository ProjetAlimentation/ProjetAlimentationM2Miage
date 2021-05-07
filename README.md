[![Build Status](http://52.232.47.118:8080/buildStatus/icon?job=DietiCourseCIBackend)](http://52.232.47.118:8080/job/DietiCourseCIBackend/)
[![Codacy Analysis](https://github.com/ProjetAlimentation/ProjetAlimentationM2Miage/actions/workflows/codacy-analysis.yml/badge.svg)](https://github.com/ProjetAlimentation/ProjetAlimentationM2Miage/actions/workflows/codacy-analysis.yml)
[![Codacy Badge](https://app.codacy.com/project/badge/Grade/14b2e1acd95546409dd6fc9b55dcb60c)](https://www.codacy.com?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=ProjetAlimentation/ProjetAlimentationM2Miage&amp;utm_campaign=Badge_Grade) 
[![codecov](https://codecov.io/gh/ProjetAlimentation/ProjetAlimentationM2Miage/branch/main/graph/badge.svg?token=JDV5VZEHDV)](https://codecov.io/gh/ProjetAlimentation/ProjetAlimentationM2Miage)
[![Website shields.io](https://img.shields.io/website-up-down-green-red/http/shields.io.svg)](https://dieticourse-frontend.azurewebsites.net/)
[![Documentation Status](https://readthedocs.org/projects/ansicolortags/badge/?version=latest)](https://github.com/ProjetAlimentation/ProjetAlimentationM2Miage/blob/main/backendSpring/src/docs/asciidoc/main.adoc)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://github.com/ProjetAlimentation/ProjetAlimentationM2Miage/blob/main/LICENCE)
[![official JetBrains project](http://jb.gg/badges/official.svg)](https://confluence.jetbrains.com/display/ALL/JetBrains+on+GitHub)

# Plateforme DietiCourse

### Technologies
<ol>
<li>Backend :Java Spring et Maven</li>
<li>Frontend : Angular</li>
<li>Base de données: H2 et Json</li>  
<li>Tests unitaires: JUnit et Mockito</li>
<li>Versionning et partgage de code: GitHub</li>
<li>Intégration continue: Jenkins </li> 
<li>Déploiement : Microsoft Azure </li>
<li>Suivi des issues : GitHub Boards </li> 
<li>Analyse du code: Codacy</li>
<li>Test Coverage : JaCoCo Code Coverage (21% covered ratio, 88% on DietService)</li>
</ol>


### Comment utiliser DietiCourse
Lien disponible pour accéder à la plateforme DietiCourse : https://dieticourse-frontend.azurewebsites.net/

Lien pour tester les fonctionnalités backend: https://dieticourse-backend.azurewebsites.net/swagger-ui/

Lien pour accéder à Jenkins : http://52.232.47.118:8080/ (connexion avec admin/password) 

#### Fonctionnalités disponibles dans le frontend

  - Connexion : Formulaire de connexion standard (à tester avec admin/password)
  - Menus: La page contenant la diète de l'utilisateur avec 2 repas/jour sur une semaine.
     <ul> Afficher le détail d'une recette en cliquant dessus </ul>
     <ul> Afficher les produits OpenFoodFacts correspondant (au mieux) à chacune des recettes </ul>
     <ul> Regénérer un nouveau repas en cliquant sur l'icône regénérer en haut à gauche de chaque recette </ul>
     <ul> Ajouter un ou plusieurs produits d'une recette dans le panier de course en cliquant sur le bouton "cadie" du produit </ul>
  - Panier : Contient les produits ajoutés au panier de course : possibilité de supprimer un ou plusieurs produits
  - Suivi : Permet de saisir des informations sur le poids, le niveau de bien-être et la qualité de la diète proposé. Il est possible de consulter l'évolution de ces métriques à travers des courbes de suivi.
  - Profil : Permet la saisie des informations concernant le profil alimentaire de l'utilisateur. Cette page va conditionner le filtrage des recettes en fonction des restrictions saisies (Allergène et Régime Végétarien / Végétalien)

#### Fonctionnalités seulement en backend 
  - Fonctionnalité d'ajout de repas au json dans "DietController /addDish" : ajout d'un repas au format json. Fonctionnalité front abandonné (#1) car ne repondant à aucun besoin métier. Permet de peupler le json de repas externe à la liste DishNutriwi.

