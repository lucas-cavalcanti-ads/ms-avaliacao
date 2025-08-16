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
- Docker

## 📁 Estrutura do projeto
```
ms-avaliacao/
├── src/
│   ├── main/
│   │   ├── java/com/lucavpa/msavaliacao/
│   │   │   ├── restaurant/
│   │   │   │   ├── app/service/                 # Casos de uso / Serviços da feature Restaurant
│   │   │   │   ├── domain/                      # Entidades e regras de negócio da feature Restaurant
│   │   │   │   ├── infra/                        # Adapters de infraestrutura (ex.: repositórios JPA)
│   │   │   │   └── web/controller/              # Controllers REST da feature Restaurant
│   │   │   ├── review/
│   │   │   │   ├── app/service/                 # Casos de uso / Serviços da feature Review
│   │   │   │   ├── domain/
│   │   │   │   │   ├── exception/               # Exceções específicas da feature Review
│   │   │   │   │   └── model/                   # Entidades e DTOs da feature Review
│   │   │   │   ├── infra/repository/            # Repositórios JPA da feature Review
│   │   │   │   └── web/
│   │   │   │       ├── controller/              # Controllers REST da feature Review
│   │   │   │       └── handler/                 # Handlers específicos da feature Review
│   │   │   ├── shared/
│   │   │   │   ├── config/                      # Configurações globais (ex.: JPA, beans)
│   │   │   │   └── web/handler/                 # Handlers globais de exceções
│   │   │   └── MsAvaliacaoApplication.java      # Classe principal Spring Boot
│   │   ├── resources/
│   │   │   └── application.properties           # Configurações da aplicação
│   │   └── static/                              # Arquivos estáticos (se aplicável)
│   └── test/                                    # Testes unitários e de integração
├── pom.xml
├── .github/                                     # Configurações do GitHub (CI/CD, workflows)
└── README.md

```
---

## ⚙️ Pré-requisitos

- [JDK 21](https://adoptium.net/en-GB/temurin/releases/?version=21) instalado
- [Maven](https://maven.apache.org/) ou utilizar o Maven Wrapper (`./mvnw`)
- Docker instalado na máquina

---

## ▶️ Como executar a aplicação

### ✔️ Via terminal

#### Build

```bash
docker build -t ms-avaliacao:dev .
```

#### Executar container

```bash
docker run --rm -p 8081:8080 ms-avaliacao:dev
```

## Funcionalidades

- Incluir nova avaliação
  - Método: POST
  - URI: /reviews
  - Body
    - personId 
    - restaurantId 
    - orderNumber 
    - comment (opcional)
- Consultar todas as avaliações
  - Método: GET 
  - URI: /reviews
- Consultar avaliação por ID da avaliação
  - Método: GET
  - URI: /reviews/{id}
- Consultar avaliações por ID de um restaurante
  - Método: GET
  - URI: /restaurant/{id}
- Consultar a média das avaliações por ID de um restaurante
  - Método: GET
  - URI: /restaurant/{id}/average

## Backlog (em ordem de prioridade)
- Testes unitários
- Implementação de Actuator
- Configuração de esteira CI
- Configuração de esteira CD
- Publicação em provedor de Cloud Pública (AWS)
- Implementação do Prometheus para coleta de métricas
- Implementação do banco de dados real
- Implementação de funcionalidade assincrona