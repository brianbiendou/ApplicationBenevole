# ApplicationBenevole

Ensembles de µ-services permettant la modification d'une base de donnée en vue de gérée des demandes d'aides de personnes en difficulté.

Les requêtes possibles sont les suivantes :

Requêtes user
  
 - GET http://localhost:8091/api/users/get : Retourne la liste de tous les membres de la table "user"
 - GET http://localhost:8091/api/users/get/{id} : Print et retourne le user dont l'id correspond à l'attribut {id} rentré
  
 - POST http://localhost:8091/api/users : Appelle les fonction POST du µ-service correspondant au role rentré dans le JSON posté. Un volunteer créé ainsi aura un phone_Number NULL
 - POST http://localhost:8091/api/users/demand : Ajoute à la table un user ayant comme rôle "demandeur" et correspondant aux attributs présents dans le JSON
 - POST http://localhost:8091/api/users/admin : Ajoute à la table un user ayant comme rôle "admin" et correspondant aux attributs présents dans le JSON
 - POST http://localhost:8091/api/users/demand : Ajoute à la table un user ayant comme rôle "demandeur" et correspondant aux attributs présents dans le JSON
 - POST http://localhost:8091/api/users/volunteer : Ajoute à la table un user ayant comme rôle "volunteer" et correspondant aux attributs présents dans le JSON

 - PUT http://localhost:8091/api/users/update/{id} : Met à jour le user dont l'id correspond à {id} selon les valeurs rentrées dans le JSON
 - DELETE http://localhost:8091/api/users/delete/{id} : Supprime le user dont l'id correspond à {id}. Supprime également en cascade le demandeur/volunteer/admin correspondant


Requêtes demandeur
 - GET http://localhost:8095/api/demandeur/get : Retourne la liste de tous les membres de la table "demandeur"
 - GET http://localhost:8095/api/demandeur/get/{id} : Print et retourne le demandeur dont l'id correspond à l'attribut {id} rentré

 - POST http://localhost:8095/api/demandeur : Rentre le demandeur dans la table selon les attributs du JSON posté. POST également le demandeur à http://localhost:8091/api/users/demand

 - PUT http://localhost:8095/api/demandeur/update/{id} : Met à jour le demandeur dont l'id correspond à {id} selon les valeurs rentrées dans le JSON
 - DELETE http://localhost:8095/api/demandeur/delete/{id} : Supprime le demandeur dont l'id correspond à {id}. Nécessite aucune request liée. Ne supprime pas le user correspondant. Non recommandé


Requêtes admin
 - GET http://localhost:8094/api/admin/get : Retourne la liste de tous les membres de la table "admin"
 - GET http://localhost:8094/api/admin/get/{id} : Retourne le admin dont l'id correspond à l'attribut {id} rentré

 - POST http://localhost:8094/api/admin : Rentre le admin dans la table selon les attributs du JSON posté. POST également le admin à http://localhost:8091/api/users/admin

 - PUT http://localhost:8094/api/admin/update/{id} : Met à jour le admin dont l'id correspond à {id} selon les valeurs rentrées dans le JSON
 - DELETE http://localhost:8094/api/admin/delete/{id} : Supprime le admin dont l'id correspond à {id}. Ne supprime pas le user correspondant. Non recommandé


Requêtes volunteer
 - GET http://localhost:8093/api/volunteers/get : Retourne la liste de tous les membres de la table "volunteer"
 - GET http://localhost:8093/api/volunteers/{id} : Retourne le volunteer dont l'id correspond à l'attribut {id} rentré

 - POST http://localhost:8093/api/volunteers : Rentre le volunteer dans la table selon les attributs du JSON posté. POST également le volunteer à http://localhost:8091/api/users/volunteer

 - PUT http://localhost:8093/api/volunteers/{id} : Met à jour le volunteer dont l'id correspond à {id} selon les valeurs rentrées dans le JSON
 - DELETE http://localhost:8093/api/volunteers/delete/{id} : Supprime le volunteer dont l'id correspond à {id}. Nécessite de ne pas être lié à une request. Ne supprime pas le user correspondant. Non recommandé



Requêtes request
 - GET http://localhost:8092/api/requests/get : Retourne la liste de tous les membres de la table "request"
 - GET http://localhost:8092/api/requests/{id} : Retourne la request dont l'id correspond à l'attribut {id} rentré

 - POST http://localhost:8092/api/requests : Rentre la request dans la table selon les attributs du JSON posté

 - PUT http://localhost:8092/api/requests/update/{id} : Met à jour la request dont l'id correspond à {id} selon les valeurs rentrées dans le JSON
 - PUT http://localhost:8092/api/requests/update/{id}/{state} : Met à jour le state de la request dont l'id correspond à {id} selon la valeur {state}
 - PUT http://localhost:8092/api/requests/accept/{id}/{volID} : Change le state de la request correspondante en "accepted" et son volunteerID par la valeur de volID
 - PUT http://localhost:8092/api/requests/remove/{volunteerID} : Passe tous les volunteerID des request pour lesquelles volunteerID = {volID} à NULL
 - DELETE http://localhost:8092/api/requests/delete/{id} : Supprime la request dont l'id correspond à {id}
 - DELETE http://localhost:8092/api/requests/delete/demandeur/{demandeurID} : Supprime toutes les request pour lesquelles demandeurID = {demandeurID}
