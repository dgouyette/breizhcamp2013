### Liste des commandes maven :

Lancer l'application :

mvn jetty:run

L'application est disponible à l'URL :






### Liste des commandes CURL :

Récupérer une entité :


Si l'entité existe :

curl -X GET http://localhost:8080/breizhcamp/talk/7




Supprimer une entité :

curl -X DELETE http://localhost:8080/breizhcamp/talk/7 -v






