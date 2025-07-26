# 📦 ms-avaliacao

Microserviço desenvolvido em Spring Boot com Java 21 responsável por operações relacionadas a **avaliações** dentro de um ecossistema de microsserviços. O projeto segue boas práticas de estruturação, versionamento e organização de pacotes com o namespace `com.lucavpa`.

---

## 🚀 Tecnologias utilizadas

- Java 21
- Spring Boot 3.2.5
  - Spring Web
  - Spring Data JPA
  - H2 Database
  - Lombok
- Maven

## 📁 Estrutura do projeto
```
ms-avaliacao/
├── src/
│ ├── main/
│ │ ├── java/com/lucavpa/msavaliacao/
│ │ │ ├── controller/
│ │ │ ├── service/
│ │ │ ├── repository/
│ │ │ ├── model/
│ │ │ └── MsAvaliacaoApplication.java
│ │ ├── resources/
│ │   └── application.properties
│ │ └── static/
│ └── test/
├── pom.xml
├── .github
└── README.md

```
---

## ⚙️ Pré-requisitos

- [JDK 21](https://adoptium.net/en-GB/temurin/releases/?version=21) instalado
- [Maven](https://maven.apache.org/) ou utilizar o Maven Wrapper (`./mvnw`)

---

## ▶️ Como executar a aplicação

### ✔️ Via terminal
```

```bash
# com Maven wrapper
./mvnw spring-boot:run

# ou, com Maven instalado globalmente
mvn spring-boot:run
'''