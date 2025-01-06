 # Test Java Spring Boot

## Objectif du test

Le but de ce test est de créer un API Rest avec Java et Spring Boot pour ajouter et obtenir les détails d'un film.

Vous devez créer deux endpoints dans l'API :

- Un GET pour obtenir un film par ID dans la base de données en mémoire (H2).
- Un POST pour ajouter un film dans la base de données en mémoire (H2).

## Présomptions

- Ne vous souciez pas de valider l'entrée de l'utilisateur, vous pouvez présumer que la requête sera toujours valide.
- Il n'est pas nécessaire d'implémenter un mécanisme de sécurité.

## Critères

- 2 endpoints REST fonctionnels
- Structure des classes
- Qualité du code
- Utilisation des meilleurs pratiques
- Création de tests unitaires et de tests Cucumber (optionnel)

## Outils à utiliser

Le projet contient déjà les dépendances Gradle requises et la configuration nécessaire pour H2.  
Vous avez aussi, optionnellement, la possibilité d'utiliser les librairies Lombok et Mapstruct si vous le désirez.

- Java 21
- Gradle
- Spring Boot
- Spring Data JPA
- Base de données en mémoire H2
- Lombok (Optionnel)
- Mapstruct (Optionnel)
- JUnit (optionnel)
- Cucumber (optionnel)

## Modèle

#### Film

```
{
    "id": long,
    "titre": string,
    "description": string
    "acteurs": [
        {
            "id": long,
            "nom": string,
            "prenom": string
        }
    ]
}
```

## Endpoints

#### GET /api/film/{id}

- Requête : ID dans l'URI
- Réponse : Objet Film (Voir modèle)
- Statut : 200 OK

```
http://localhost:8080/api/film/1
{
   "id":1,
   "titre":"Star Wars: The Empire Strikes Back",
   "description":"Darth Vader is adamant about turning Luke Skywalker to the dark side.",
   "acteurs":[
      {
         "id":2,
         "nom":"Ford",
         "prenom":"Harrison"
      },
      {
         "id":3,
         "nom":"Hamill",
         "prenom":"Mark"
      }
   ]
}
```

#### POST /api/film

- Requête : Objet Film dans le body
- Réponse : Objet Film crée
- Statut : 201 CREATED

```
'{
   "titre":"Star Wars: The Empire Strikes Back",
   "description":"Darth Vader is adamant about turning Luke Skywalker to the dark side.",
   "acteurs":[
      {
         "nom":"Ford",
         "prenom":"Harrison"
      },
      {
         "nom":"Hamill",
         "prenom":"Mark"
      }
   ]
}'
http://localhost:8080/api/film --header "Content-Type:application/json"

{
   "id":4,
   "titre":"Star Wars: The Empire Strikes Back",
   "description":"Darth Vader is adamant about turning Luke Skywalker to the dark side.",
   "acteurs":[
      {
         "id":5,
         "nom":"Ford",
         "prenom":"Harrison"
      },
      {
         "id":6,
         "nom":"Hamill",
         "prenom":"Mark"
      }
   ]
}
```

## Validation des endpoints

Vous pouvez utiliser la collection postman incluse dans le projet si vous désirez valider votre API avec des assertions.  
Celle-ci se retrouve dans le dossier **postman** du projet.

## Soumettre le test

Une fois terminé, veuillez créer un nouveau dépot sur GitHub et l'envoyer par courriel.
Merci de ne pas faire un fork du dépôt de code existant.

Bonne chance !
