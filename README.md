# 🌟 FRESHTREE "Système de gestion agricole" 🌱

## Description
**FRESHFREE** est un système complet de gestion agricole conçu pour aider les agriculteurs à gérer leurs fermes, champs, arbres, récoltes et ventes. La plateforme simplifie les processus, optimise le suivi de la productivité et améliore la prise de décision pour une gestion agricole efficace.

## 🛠️ Technologies Utilisées

### Backend
- **Spring Boot** : Utilisé pour créer l'API REST et gérer la logique métier.
- **Spring Data JPA** : Simplifie les interactions avec la base de données relationnelle grâce à des abstractions puissantes.

### Base de Données
- **MYSQL** : Base de données relationnelle utilisée pour la persistance.

###TEST-API 
- **Postman** : Utile pour tester vos API REST de manière simple et efficace. 

### Conteneurisation
- **Docker** : Permet de conteneuriser l'application pour un déploiement simplifié et reproductible.

### Mapping & Simplification du Code
- **MapStruct** : Permet de mapper efficacement les entités et les DTOs.
- **Lombok** : Réduit le code répétitif en générant automatiquement des méthodes comme `getters`, `setters`, et bien plus.

### Tests
- **Mockito** : Outil principal pour réaliser des tests unitaires en simulant des dépendances.

## 🚀 Fonctionnalités

### 🌾 Gestion des Fermes
- 🛠️ **CRUD** : Créer, lire, mettre à jour et supprimer des fermes.
- 📊 **Validation des données** : Vérification que la somme des surfaces des champs ne dépasse pas la surface totale de la ferme.
- 🔍 **Recherche multicritères** : Filtrer les fermes par différents critères (nom, localisation, etc...).

---

### 🌱 Gestion des Champs
- 🛠️ **Ajout de champs** : Associer des champs à une ferme tout en respectant des contraintes sur les surfaces.
- 🔢 **Limitation** : Chaque ferme peut contenir un maximum de **10 champs**.

---

### 🌳 Gestion des Arbres
- 🌿 **Suivi des arbres** : Enregistrer des informations telles que la date de plantation, l'âge, et le champ assigné.
- 📈 **Calcul de la productivité des arbres** :
  - **Jeunes arbres** (< 3 ans) : 2,5 kg/saison.
  - **Arbres matures** (3-10 ans) : 12 kg/saison.
  - **Vieux arbres** (> 10 ans) : 20 kg/saison.
- 📅 **Validation des dates de plantation** : Seules les plantations effectuées entre **mars** et **mai** sont acceptées. (**3**--**5**)

---

### 🌦️ Gestion des Récoltes
- 🛠️ **Enregistrement des récoltes** : Ajouter des détails comme la quantité récoltée et la saison.
- 🕒 **Restriction** : Une seule récolte par saison et par champ est autorisée.

---

### 💰 Gestion des Ventes
- 🛒 **Suivi des ventes** : Enregistrer les détails des clients, la quantité vendue, et le prix unitaire.
- 💵 **Calcul automatique du revenu total** : Multiplier la quantité par le prix unitaire pour obtenir le revenu.




