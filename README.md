# 🏠 Rental OpenClassroom - API de Location Immobilière

Une API REST complète pour la gestion de locations immobilières, développée avec Spring Boot 3.4.5 et Java 17.

## 📋 Table des matières

- [Fonctionnalités](#-fonctionnalités)
- [Technologies utilisées](#-technologies-utilisées)
- [Prérequis](#-prérequis)
- [Configuration](#-configuration)
- [Structure du projet](#-structure-du-projet)
- [API Endpoints](#-api-endpoints)
- [Authentification](#-authentification)
- [Documentation API](#-documentation-api)

## ✨ Fonctionnalités

### 🔐 Authentification
- **Inscription** : Création de nouveaux comptes utilisateurs
- **Connexion** : Authentification avec JWT
- **Profil utilisateur** : Accès aux informations personnelles

### 🏘️ Gestion des Locations
- **Liste des locations** : Récupération de toutes les locations disponibles
- **Détails d'une location** : Informations complètes d'une location spécifique
- **Création de location** : Ajout de nouvelles locations avec upload d'images
- **Modification de location** : Mise à jour des informations (propriétaire uniquement)

### 💬 Système de Messagerie
- **Envoi de messages** : Communication entre utilisateurs et propriétaires
- **Gestion des conversations** : Suivi des échanges par location

### 📸 Gestion des Images
- **Upload d'images** : Stockage local des photos de locations
- **Génération d'UUID** : Sécurisation des noms de fichiers

## 🛠️ Technologies utilisées

- **Java 17** - Langage de programmation
- **Spring Boot 3.4.5** - Framework principal
- **Spring Security** - Sécurisation de l'API
- **Spring Data JPA** - Persistance des données
- **MySQL** - Base de données
- **JWT** - Authentification par tokens
- **Swagger/OpenAPI** - Documentation de l'API
- **Gradle** - Gestion des dépendances
- **Spring Boot DevTools** - Outils de développement

## 📋 Prérequis

- **Java 17** ou supérieur
- **MySQL 8.0** ou supérieur
- **Gradle 7.0** ou supérieur
- **IDE** (IntelliJ IDEA, Eclipse, VS Code)

L'application sera accessible sur : `http://localhost:8080`

### Configuration JWT
```properties
jwt.secret=NjFiOWYxMDQ4ZjE2MzNmZDhjMjk3NDZkNmIzYTc5NTQ0ZGQ2YzRlZWNhZjM5MzY4ZDY3ZmNiYmM0MjQ2OTM4Nw==
jwt.expiration=86400000
```

### Configuration Swagger
```properties
springdoc.swagger-ui.path=/swagger-ui.html
```

## 📁 Structure du projet

```
src/
├── main/
│   ├── java/com/rental/
│   │   ├── config/           # Configuration Spring
│   │   ├── controller/       # Contrôleurs REST
│   │   ├── dto/             # Objets de transfert de données
│   │   ├── model/           # Entités JPA
│   │   ├── repository/      # Interfaces de données
│   │   ├── service/         # Logique métier
│   │   └── utils/           # Utilitaires (JWT, etc.)
│   └── resources/
│       ├── application.properties
│       └── public/uploads/  # Stockage des images
└── test/                    # Tests unitaires
```

## 🔐 Authentification

L'API utilise l'authentification JWT (JSON Web Token).

### Format du token
```
Authorization: Bearer <jwt_token>
```

## 🗄️ Base de données

### Entités principales

#### User
- `id` : Identifiant unique
- `name` : Nom de l'utilisateur
- `email` : Adresse email (unique)
- `password` : Mot de passe hashé
- `created_at` : Date de création
- `updated_at` : Date de modification

#### Rental
- `id` : Identifiant unique
- `name` : Nom de la location
- `surface` : Surface en m²
- `price` : Prix de location
- `picture` : Nom du fichier image
- `description` : Description détaillée
- `owner_id` : ID du propriétaire
- `created_at` : Date de création
- `updated_at` : Date de modification

#### Message
- `id` : Identifiant unique
- `rental_id` : ID de la location concernée
- `user_id` : ID de l'utilisateur
- `message` : Contenu du message
- `created_at` : Date de création
- `updated_at` : Date de modification

## 📚 Documentation API

La documentation interactive est disponible via Swagger UI :

- **URL** : `http://localhost:8080/swagger-ui.html`

### Fonctionnalités Swagger
- Documentation interactive
- Tests d'endpoints directement depuis l'interface
- Exemples de requêtes et réponses
- Authentification JWT intégrée

### Standards de code
- Respect des conventions Java
- Documentation des méthodes publiques
- Tests unitaires pour les nouvelles fonctionnalités
- Messages de commit explicites
