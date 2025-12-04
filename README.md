# 📊 Mon Projet Spring - Gestion de Projets et Équipes

Application REST API Spring Boot pour la gestion complète de projets, d'équipes et de détails de projets avec gestion avancée des relations JPA.

## 📋 Description

Cette application fournit une API REST robuste pour gérer l'ensemble du cycle de vie des projets, incluant :
- Création et gestion de projets avec leurs détails techniques
- Affectation et désaffectation d'équipes aux projets
- Gestion des relations bidirectionnelles entre entités
- Support de domaines métier spécifiques (ERPBI, SIM, NIDS)

## 🛠️ Technologies Utilisées

- **Framework**: Spring Boot 3.x
- **Langage**: Java 17+
- **ORM**: JPA/Hibernate (Jakarta Persistence)
- **Build Tool**: Maven
- **Base de données**: Compatible avec bases relationnelles (MySQL, PostgreSQL, H2)
- **Librairies**:
  - Lombok - Réduction du code boilerplate
  - Spring Data JPA - Gestion des repositories
  - Spring Web - APIs REST
  - Spring Transactions - Gestion transactionnelle

## 📁 Structure du Projet

```
src/
├── main/
│   └── java/
│       └── tn.fst.tp5eyaammarigroupe3/
│           ├── controllers/              # Contrôleurs REST
│           │   └── ProjetRestController.java
│           ├── services/                 # Couche service
│           │   ├── interfaces/
│           │   │   └── IProjetService.java
│           │   └── ProjetServiceImpl.java
│           ├── repositories/             # Repositories JPA
│           │   ├── ProjetRepository.java
│           │   ├── ProjetDetailRepository.java
│           │   └── EquipeRepository.java
│           ├── entities/                 # Entités JPA
│           │   ├── Projet.java
│           │   ├── ProjetDetail.java
│           │   ├── Equipe.java
│           │   └── Domaine.java
│           └── Tp5EyaAmmariGroupe3Application.java
└── test/                                 # Tests unitaires
```

## 🗂️ Modèle de Données

### Entités Principales

#### 📁 Projet
- `id` (Long) - Identifiant unique
- `sujet` (String) - Sujet du projet
- **Relations**:
  - OneToOne avec `ProjetDetail`
  - ManyToMany avec `Equipe`

**Fonctionnalités**:
- Ajout d'équipes au projet
- Association avec détails techniques

#### 📝 ProjetDetail
- `id` (Long) - Identifiant unique
- `description` (String) - Description détaillée
- `technologie` (String) - Technologies utilisées
- `cout` (Long) - Coût estimé
- `dateDebut` (Date) - Date de début
- **Relations**:
  - OneToOne bidirectionnel avec `Projet`

**Fonctionnalités**:
- Détails techniques et financiers du projet
- Relation bidirectionnelle avec le projet

#### 👥 Equipe
- `id` (Long) - Identifiant unique
- `nom` (String) - Nom de l'équipe
- `domaine` (Domaine) - Domaine de spécialisation
- **Relations**:
  - ManyToMany avec `Projet`

**Domaines disponibles**:
- `ERPBI` - Enterprise Resource Planning & Business Intelligence
- `SIM` - Systèmes d'Information Métier
- `NIDS` - Network Intrusion Detection Systems

## 🔌 API Endpoints

### Projet Controller (`/projet`)

| Méthode | Endpoint | Description |
|---------|----------|-------------|
| POST | `/ajouter-projet-et-projet-detail` | Ajouter un projet avec ses détails |
| PUT | `/affecter-projet-a-projet-details/{projet-id}/{projet-details-id}` | Affecter un ProjetDetail existant à un Projet |
| PUT | `/affecter-projet-equipe/{projet-id}/{equipe-id}` | Affecter un projet à une équipe |
| POST | `/ajouter-et-affecter-projet-detail/{projet-detail-id}` | Créer un projet et l'affecter à un ProjetDetail existant |
| DELETE | `/desaffecter-projet-detail/{projet-id}` | Désaffecter le ProjetDetail d'un Projet |
| DELETE | `/desaffecter-projet-de-equipe/{projet-id}/{equipe-id}` | Désaffecter un projet d'une équipe |

## 📖 Cas d'Usage et Exemples

### Cas 1 : Créer un Projet avec ses Détails

**Endpoint**: `POST /projet/ajouter-projet-et-projet-detail`

```json
{
  "sujet": "Application de Gestion RH",
  "projetDetail": {
    "description": "Système de gestion des ressources humaines",
    "technologie": "Spring Boot, Angular, PostgreSQL",
    "cout": 150000,
    "dateDebut": "2025-01-15"
  }
}
```

**Description**: Crée un nouveau projet avec tous ses détails techniques en une seule opération.

---

### Cas 2 : Affecter un ProjetDetail Existant à un Projet

**Endpoint**: `PUT /projet/affecter-projet-a-projet-details/{projet-id}/{projet-details-id}`

```bash
PUT http://localhost:8080/projet/affecter-projet-a-projet-details/1/5
```

**Description**: Associe un ProjetDetail déjà existant à un projet existant. Utile pour réutiliser des spécifications techniques.

---

### Cas 3 : Affecter un Projet à une Équipe

**Endpoint**: `PUT /projet/affecter-projet-equipe/{projet-id}/{equipe-id}`

```bash
PUT http://localhost:8080/projet/affecter-projet-equipe/1/3
```

**Description**: Assigne une équipe à un projet. La relation Many-to-Many permet à plusieurs équipes de travailler sur le même projet.

---

### Cas 4 : Créer un Projet et l'Affecter à un ProjetDetail Existant

**Endpoint**: `POST /projet/ajouter-et-affecter-projet-detail/{projet-detail-id}`

```bash
POST http://localhost:8080/projet/ajouter-et-affecter-projet-detail/5
Content-Type: application/json

{
  "sujet": "Migration Cloud"
}
```

**Description**: Crée un nouveau projet et l'associe immédiatement à un ProjetDetail qui existe déjà.

---

### Cas 5 : Désaffecter un ProjetDetail d'un Projet

**Endpoint**: `DELETE /projet/desaffecter-projet-detail/{projet-id}`

```bash
DELETE http://localhost:8080/projet/desaffecter-projet-detail/1
```

**Description**: Supprime le lien entre un Projet et son ProjetDetail sans supprimer les entités elles-mêmes.

---

### Cas 6 : Désaffecter un Projet d'une Équipe

**Endpoint**: `DELETE /projet/desaffecter-projet-de-equipe/{projet-id}/{equipe-id}`

```bash
DELETE http://localhost:8080/projet/desaffecter-projet-de-equipe/1/3
```

**Description**: Retire l'équipe du projet. Gère la relation bidirectionnelle Many-to-Many correctement.

## 🚀 Installation et Démarrage

### Prérequis

- Java 17 ou supérieur
- Maven 3.8+
- Base de données (MySQL, PostgreSQL, ou H2 pour développement)

### Étapes d'installation

1. **Cloner le repository**
```bash
git clone https://github.com/AmariEyaa/MonProjetSpring.git
cd MonProjetSpring
```

2. **Configurer la base de données**

Créer/modifier le fichier `src/main/resources/application.properties`:

```properties
# Configuration de la base de données
spring.datasource.url=jdbc:mysql://localhost:3306/projet_db
spring.datasource.username=votre_username
spring.datasource.password=votre_password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# Configuration JPA/Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
spring.jpa.properties.hibernate.format_sql=true

# Configuration du serveur
server.port=8080
```

**Pour H2 (développement)**:
```properties
spring.datasource.url=jdbc:h2:mem:projetdb
spring.datasource.driverClassName=org.h2.Driver
spring.h2.console.enabled=true
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
```

3. **Compiler le projet**
```bash
mvn clean install
```

4. **Lancer l'application**
```bash
mvn spring-boot:run
```

L'application sera accessible sur `http://localhost:8080`

## 🧪 Tests

Pour exécuter les tests:
```bash
mvn test
```

Pour exécuter les tests avec rapport de couverture:
```bash
mvn test jacoco:report
```

## 📦 Build et Déploiement

### Créer un fichier JAR exécutable

```bash
mvn clean package
```

Le fichier JAR sera généré dans `target/`.

### Exécuter le JAR

```bash
java -jar target/tp5eyaammarigroupe3-0.0.1-SNAPSHOT.jar
```

### Build avec profil de production

```bash
mvn clean package -Pprod
```

## 🏗️ Architecture

### Architecture en Couches

```
┌─────────────────────────┐
│   Controller Layer      │  ← Gestion des requêtes HTTP
├─────────────────────────┤
│   Service Layer         │  ← Logique métier et transactions
├─────────────────────────┤
│   Repository Layer      │  ← Accès aux données (JPA)
├─────────────────────────┤
│   Entity Layer          │  ← Modèles de données
└─────────────────────────┘
```

### Principes de Conception

- **Injection de Dépendances**: Utilisation de `@AllArgsConstructor` avec Lombok
- **Transactions**: Gestion automatique avec `@Transactional`
- **Relations Bidirectionnelles**: Gestion cohérente des deux côtés de la relation
- **Repository Pattern**: Abstraction de l'accès aux données
- **Service Layer**: Isolation de la logique métier

## 🔍 Fonctionnalités Avancées

### Gestion des Relations Bidirectionnelles

Le projet implémente une gestion robuste des relations Many-to-Many bidirectionnelles entre `Projet` et `Equipe`:

```java
// Lors de l'affectation
projet.addEquipe(equipe);
equipe.getProjets().add(projet);

// Lors de la désaffectation
projet.getEquipes().remove(equipe);
equipe.getProjets().remove(projet);
```

### Gestion Transactionnelle

Toutes les opérations de service sont transactionnelles (`@Transactional`), garantissant la cohérence des données.

### Initialisation des Collections

Les collections sont initialisées directement dans les entités pour éviter les `NullPointerException`:

```java
private Set<Equipe> equipes = new HashSet<>();
private Set<Projet> projets = new HashSet<>();
```

## 📊 Diagramme de Relations

```
┌──────────────┐           ┌──────────────────┐
│   Projet     │ 1     1   │  ProjetDetail    │
│              │◄─────────►│                  │
│  - id        │           │  - id            │
│  - sujet     │           │  - description   │
└──────┬───────┘           │  - technologie   │
       │                   │  - cout          │
       │ *              *  │  - dateDebut     │
       │                   └──────────────────┘
       │
       │
       │
       │         ┌──────────────┐
       └────────►│    Equipe    │
                 │              │
                 │  - id        │
                 │  - nom       │
                 │  - domaine   │
                 └──────────────┘
```

## 🎯 Cas d'Utilisation Métier

### Scénario 1: Nouveau Projet Complet
1. Créer un projet avec tous ses détails techniques
2. Affecter une ou plusieurs équipes spécialisées
3. Suivre l'évolution et les coûts

### Scénario 2: Réorganisation d'Équipes
1. Désaffecter une équipe d'un projet
2. Affecter une nouvelle équipe avec le domaine approprié
3. Maintenir l'intégrité des données

### Scénario 3: Réutilisation de Spécifications
1. Créer un ProjetDetail réutilisable
2. L'affecter à plusieurs projets similaires
3. Centraliser les informations techniques

## 🛠️ Outils de Développement Recommandés

- **IDE**: IntelliJ IDEA ou Eclipse avec Spring Tools
- **API Testing**: Postman ou Insomnia
- **Base de données**: MySQL Workbench ou DBeaver
- **Version Control**: Git avec GitHub

## 📝 Bonnes Pratiques Implémentées

✅ Architecture en couches claire et séparée  
✅ Gestion appropriée des relations JPA bidirectionnelles  
✅ Utilisation de transactions pour la cohérence des données  
✅ Gestion des erreurs avec exceptions personnalisées  
✅ Utilisation de Lombok pour réduire le code boilerplate  
✅ Initialisation defensive des collections  
✅ Séparation des interfaces et implémentations  

## 🐛 Troubleshooting

### Erreur: "Projet non trouvé"
- Vérifier que l'ID du projet existe dans la base de données
- Utiliser un client REST pour vérifier les données

### Erreur: NullPointerException sur les collections
- Les collections sont initialisées par défaut, mais vérifier l'état de la base de données

### Problème de connexion à la base de données
- Vérifier les credentials dans `application.properties`
- S'assurer que le serveur de base de données est démarré

## 🤝 Contribution

Les contributions sont les bienvenues ! Pour contribuer:

1. Fork le projet
2. Créer une branche (`git checkout -b feature/NouvelleFonctionnalite`)
3. Commit les changements (`git commit -m 'Ajout NouvelleFonctionnalite'`)
4. Push vers la branche (`git push origin feature/NouvelleFonctionnalite`)
5. Ouvrir une Pull Request

### Guidelines de Contribution

- Respecter l'architecture en couches existante
- Ajouter des tests pour les nouvelles fonctionnalités
- Documenter les nouvelles API endpoints
- Suivre les conventions de nommage Java

## 📚 Documentation Complémentaire

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [Jakarta Persistence](https://jakarta.ee/specifications/persistence/)
- [Lombok Documentation](https://projectlombok.org/)

## 👤 Auteur

**Amari Eyaa**
- GitHub: [@AmariEyaa](https://github.com/AmariEyaa)

## 📄 Licence

Ce projet est développé dans un cadre éducatif.

---

⭐ **Note**: Ce projet illustre les concepts avancés de JPA incluant les relations bidirectionnelles, la gestion transactionnelle et l'architecture en couches avec Spring Boot.

💡 **Tip**: Utilisez Postman pour tester facilement tous les endpoints de l'API !
