# Projet CoursEpfBack

## Présentation

Ce projet est le backend d'un jeu de type "Plants vs Zombies".
Il est structuré selon une architecture en couches, avec une séparation stricte des responsabilités pour garantir la maintenabilité, la robustesse et la clarté du code.
Ce choix d'architecture, bien que parfois redondant pour un petit projet, a été fait pour s'entraîner à une organisation professionnelle typique des applications Spring.

---

## Architecture en couches et formats échangés

Le projet suit une architecture en **quatres couches principales** :

```
Controller <-> Service <-> Repository <-> DAO 
```

À chaque frontière de couche, les formats de données échangés sont différents :

- **Entre le Controller et le Service** :
  - **Entrée** : DTO (Data Transfer Object) 
  - **Sortie** : Model (objet métier)
- **Entre le Service et le Repository** :
  - **Entrée/Sortie** : Model
- **Entre le Repository et le DAO** :
  - **Entrée/Sortie** : Entity (représentation base de données)
- **Le DAO** est responsable de l'accès physique à la base de données.

Chaque couche effectue la conversion nécessaire :
- Le **Controller** convertit les DTO reçus en Model pour le Service, et inversement.
- Le **Service** travaille uniquement avec des Models et utilise le Repository pour la persistance.
- Le **Repository** convertit les Models en Entities pour les passer au DAO, et inversement.

**Schéma :**

```
[API/Client]
    |
   [DTO] <-> Controller <-> [Model] <-> Service <-> [Model] <-> Repository <-> [Entity] <-> DAO <-> Base de données
```

Ce découpage permet :
- D'isoler les responsabilités de chaque couche
- De sécuriser les échanges (on ne renvoie jamais d'Entity brute à l'extérieur)
- De faciliter l'évolution du code (modification d'une couche sans casser les autres)
- De séparer clairement la logique métier, la logique de persistance, et l'accès aux données

---

## Utilisation Model, Entity et DTO ?

Même si dans ce projet les trois formats sont parfois proches et peuvent sembler inutiles, leur séparation permet :
- **D'anticiper la complexité** : dans un projet réel, chaque couche évolue différemment.
- **De garantir la maintenabilité** : chaque couche peut évoluer indépendamment.
- **De sécuriser les échanges** : on contrôle précisément ce qui transite entre les couches et ce qui est exposé à l'extérieur.
- **De s'entraîner à une architecture professionnelle** : c'est un choix personnel pour acquérir de bonnes pratiques Spring.

---

## Vérifications réalisées dans le code

Les vérifications de validité des données sont rélisés dans la logique métier **Service**. 
### Zombie
- Nom non vide et non nul
- Points de vie strictement positifs et bornés
- Attaque par seconde strictement positive et bornée
- Dégâts d'attaque strictement positifs et bornés
- Vitesse de déplacement strictement positive et bornée
- Chemin d'image non vide
- L'id de la map associée doit exister
- **Rôle** : garantir la cohérence métier, éviter les données corrompues ou incohérentes, et prévenir les erreurs d'exécution

### Plante
- Nom non vide et non nul
- Points de vie strictement positifs et bornés
- Attaque par seconde positive et bornée
- Dégâts d'attaque non négatifs et bornés
- Coût strictement positif
- Soleil par seconde positif
- Effet non vide
- Chemin d'image non vide 
- **Rôle** : assurer la validité des plantes créées ou modifiées, éviter les abus ou incohérences dans le gameplay

### Map
- Nombre de lignes et de colonnes strictement positifs et bornés
- Chemin d'image non vide
- **Rôle** : garantir que les maps sont jouables et correctement définies

Toutes ces vérifications sont centralisées dans la couche service, ce qui garantit la cohérence métier indépendamment de la source des données (API, tests, etc.).

### Attention : Lien entre les zombies et les maps
Les vérifications des liens entre les maps et les plantes entrainent les comportements suivants:
- Un zombie ne peut pas être créé ou mis à jour sans être lié à une map existante.
- Une map suprimmée entraine la supression des zombies qui lui sont associé. Ce comportement permet d'assurer que les zombies soient toujours associés à une map.


---

## Gestion centralisée des exceptions

Le gestionnaire global d'exceptions (`GlobalExceptionHandler`) :
- Intercepte toutes les exceptions métier (`NotFoundException`, `ValidationException`) et les exceptions inattendues
- Retourne des réponses HTTP claires et adaptées (404, 400, 500) avec un **message explicite sur l'erreur en question**
- Centralise la gestion des erreurs pour éviter la duplication de code dans les contrôleurs
- Facilite le débogage grâce à des logs détaillés

**Rôle** : améliorer la robustesse de l'API, fournir des retours clairs aux clients, et simplifier la maintenance.

**Gestion des updates partielles** :
Les updates contenant des termes vides ne renvoient pas d'erreurs et conservent pour les arguments vides les arguments initaux. Cette méthode permet donc de gérer les updates partielles.


---

## Tests unitaires

Des fichiers de tests unitaires sont présents pour chaque couches, types d'objet et mapper. Ils vérifient :
- Les cas d'utilisation standards (création, modification, suppression, récupération...)
- Les cas d'erreur et de validation (ex : données invalides, identifiants inexistants)
- La robustesse des vérifications métier

**Rôle** : garantir la fiabilité du code, prévenir les régressions, et documenter les comportements attendus.

---

## Endpoint `/validation`

Un endpoint spécifique à été ajouté inspiré de celui du front `/maps/validation`. Il est disponible dans le contrôleur des maps. Il permet de :
- Vérifier en temps réel la validité de toutes les maps stockées dans la base
- Retourner un rapport détaillé sur les erreurs détectées (lignes/colonnes invalides, chemin d'image manquant, etc.)
- Faciliter le diagnostic et la correction des données côté back, sans passer par le front

**Rôle** : offrir un outil de contrôle qualité pour l'administrateur ou le développeur, et garantir l'intégrité des données.

---
## Commentaires liés au front
### Chemin des images
Le chemin des images utilisé dans le front pour créer de nouveaux plante/map/zombie finie par un -s contrairement à la base données et au chemin des images dans webapp. Le choix des chemin d'images à conservé à été fait en faveur de la base de données. Ainsi, lors de la création de nouveau plante/zombie/map les images n'apparaissent pas puisque le front attribut un chemin de avec un -s à la fin (expl: `/webapp/images/plantes/default.png` au lieu de `/webapp/images/plante/default.png`).

### Gestion des maps du front
Le front ne permet pas le choix des maps. Toutefois, des choix ont été fait pour la gestion du lien entre les zombies et les maps. Ces décisions sont issues de l'hypothèse que l'id_map d'un zombie permet au front de géréer le déplacement des zombies selon le nombre de lignes et de colones de la map en question. Ainsi, un zombie ne peux exister sans être associé à une map existantes. Pour plus de détails, consulter `Vérifications réalisées dans le code/Attention : Lien entre les zombies et les maps`