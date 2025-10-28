 # 🐉 Pokemon Cache API

 API REST em Spring Boot 3.2.0 + Java 21 que consome a PokeAPI e mantém um cache local em H2.

 ## Sobre

 Aplicação simples para buscar Pokémon na PokeAPI, persistir no banco H2 e expor endpoints para
 listagem, busca por tipo e marcação como favorito.

 ## Tecnologias

 - Java 21
 - Spring Boot 3.2.0
 - Spring Data JPA
 - H2 (in-memory)
 - Spring WebFlux (WebClient)
 - Lombok
 - Gradle 8.5

 ## Como executar

 No Windows (PowerShell):

 ```powershell
 .\gradlew.bat bootRun
 # Acesse: http://localhost:8080
 ```

 ## Console H2

 - URL: http://localhost:8080/h2-console
 - JDBC URL: `jdbc:h2:mem:pokemondb`
 - User: `sa`
 - Password: (vazio)

 ## Endpoints principais

 - POST /api/pokemon/cache/{nameOrId}  — cacheia um Pokémon da PokeAPI (201)
 - GET  /api/pokemon?page=0&size=10   — lista paginada (200)
 - GET  /api/pokemon/{id}             — detalhes por id local (200)
 - GET  /api/pokemon/search?type=fire — busca por tipo (200)
 - PATCH /api/pokemon/{id}/favorite   — marcar/atualizar favorito (200)
 - GET /actuator/health               — health check

 ## Robustez

 O projeto implementa tratamento global de exceções, validações de entrada e logs estruturados.
 Para a documentação técnica completa sobre erros, códigos HTTP, validações e testes de robustez,
 consulte: [ROBUSTEZ.md](ROBUSTEZ.md)

 ## Testes rápidos (PowerShell)

 ```powershell
 # Cachear
 Invoke-RestMethod -Method POST -Uri http://localhost:8080/api/pokemon/cache/pikachu

 # Listar
 Invoke-RestMethod -Uri http://localhost:8080/api/pokemon

 # Favoritar
 $body = @{favorite=$true; note="Meu favorito"} | ConvertTo-Json
 Invoke-RestMethod -Method PATCH -Uri http://localhost:8080/api/pokemon/1/favorite -Body $body -ContentType "application/json"
 ```

 ## Links úteis

 - Aplicação: http://localhost:8080
 - Console H2: http://localhost:8080/h2-console
 - PokeAPI: https://pokeapi.co/docs/v2

 ---

 Desenvolvido por Paulo Henrique Araujo
# 🐉 Pokemon Cache API# 🐉 Pokemon Cache API# 🐉 Pokemon Cache API# 🐉 Pokemon Cache API# Pokemon Cache API



API REST em **Spring Boot 3.2.0** + **Java 21** que consome a [PokeAPI](https://pokeapi.co/) e persiste dados localmente em banco H2.



## 📋 SobreAPI REST em **Spring Boot 3.2.0** + **Java 21** que consome a [PokeAPI](https://pokeapi.co/) e persiste dados localmente em banco H2.



Aplicação que busca Pokémon na PokeAPI externa, salva no banco local e disponibiliza endpoints para consulta e gerenciamento com sistema de favoritos.



**Funcionalidades:**## 📋 SobreAPI REST em **Spring Boot 3.2.0** + **Java 21** que consome a [PokeAPI](https://pokeapi.co/) e persiste dados localmente em banco H2 para cache.

- ✅ Cache de Pokémon da PokeAPI

- ✅ Listagem paginada

- ✅ Busca por tipo

- ✅ Sistema de favoritosAplicação que busca Pokémon na PokeAPI externa, salva no banco local e disponibiliza endpoints para consulta e gerenciamento com sistema de favoritos.

- ✅ Tratamento de erros robusto

- ✅ Validações de entrada

- ✅ Logs estruturados

**Funcionalidades:**## 📋 Sobre o Projeto<div align="center">API REST em Spring Boot que consome a PokeAPI v2 e persiste/cacheia dados no H2, seguindo arquitetura MVC.

---

- ✅ Cache de Pokémon da PokeAPI

## 🚀 Tecnologias

- ✅ Listagem paginada

Java 21 | Spring Boot 3.2.0 | Spring Data JPA | H2 Database | Spring WebFlux | Lombok | Gradle 8.5

- ✅ Busca por tipo

---

- ✅ Sistema de favoritosAplicação que busca informações de Pokémon na PokeAPI externa, salva no banco local (H2) e disponibiliza endpoints para consulta, busca e gerenciamento com sistema de favoritos.

## ⚙️ Como Executar

- ✅ Tratamento de erros robusto

```bash

# Executar- ✅ Validações de entrada

.\gradlew.bat bootRun

- ✅ Logs estruturados

# Acesse: http://localhost:8080

```**Funcionalidades:**![Java](https://img.shields.io/badge/Java-21-orange?style=for-the-badge&logo=java)## 📋 Descrição



------



## 💾 Console H2- ✅ Cache de Pokémon da PokeAPI no banco H2



1. Acesse: **http://localhost:8080/h2-console**## 🚀 Tecnologias

2. Preencha:

   - **JDBC URL:** `jdbc:h2:mem:pokemondb`- ✅ Listagem paginada de Pokémon![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.0-brightgreen?style=for-the-badge&logo=spring)

   - **User:** `sa`

   - **Password:** *(vazio)*- Java 21 | Spring Boot 3.2.0 | Spring Data JPA | H2 Database | Spring WebFlux | Lombok | Gradle 8.5



**Queries úteis:**- ✅ Busca por tipo (fire, water, electric, etc.)

```sql

SELECT * FROM POKEMON;---

SELECT * FROM POKEMON WHERE FAVORITE = TRUE;

SELECT TYPES, COUNT(*) FROM POKEMON GROUP BY TYPES;- ✅ Sistema de favoritos com anotações personalizadas![Gradle](https://img.shields.io/badge/Gradle-8.5-blue?style=for-the-badge&logo=gradle)Esta aplicação implementa um microserviço que:

```

## ⚙️ Como Executar

---

- ✅ Tratamento robusto de erros

## 📚 Endpoints

```bash

### 1. Cachear Pokémon

```http# Executar- ✅ Validações de entrada![H2](https://img.shields.io/badge/H2-Database-blue?style=for-the-badge)- Busca informações de Pokémon na PokeAPI v2

POST /api/pokemon/cache/{nameOrId}

```.\gradlew.bat bootRun

```bash

Invoke-RestMethod -Method POST -Uri http://localhost:8080/api/pokemon/cache/pikachu- ✅ Logs estruturados

```

# Acesse: http://localhost:8080

### 2. Listar (Paginado)

```http```![License](https://img.shields.io/badge/License-MIT-yellow?style=for-the-badge)- Persiste dados essenciais no banco H2 (cache local)

GET /api/pokemon?page=0&size=10

```



### 3. Buscar por ID------

```http

GET /api/pokemon/{id}

```

## 💾 Console H2- Expõe endpoints REST para consulta e gerenciamento dos dados

### 4. Buscar por Tipo

```http

GET /api/pokemon/search?type=fire

```1. Acesse: **http://localhost:8080/h2-console**## 🚀 Tecnologias



### 5. Favoritar2. Preencha:

```http

PATCH /api/pokemon/{id}/favorite   - **JDBC URL:** `jdbc:h2:mem:pokemondb`**API REST robusta para cache e gerenciamento de Pokémon**- Segue arquitetura MVC: controller (REST), service (regra/integração), repository (JPA), model (entidades/DTOs)

```

```bash   - **User:** `sa`

$body = @{favorite=$true; note="Meu favorito!"} | ConvertTo-Json

Invoke-RestMethod -Method PATCH -Uri http://localhost:8080/api/pokemon/1/favorite -Body $body -ContentType "application/json"   - **Password:** *(vazio)*- **Java 21** (LTS)

```



### 6. Health Check

```http**Queries úteis:**- **Spring Boot 3.2.0**

GET /actuator/health

``````sql



---SELECT * FROM POKEMON;- **Spring Data JPA**



## 🧪 Teste CompletoSELECT * FROM POKEMON WHERE FAVORITE = TRUE;



```bashSELECT TYPES, COUNT(*) FROM POKEMON GROUP BY TYPES;- **H2 Database** (em memória)[🚀 Começar](#-como-executar) • [📚 Documentação](#-endpoints-da-api) • [🧪 Testes](#-testes) • [🛡️ Robustez](#️-robustez-e-tratamento-de-erros)## 🚀 Tecnologias

# Cachear

Invoke-RestMethod -Method POST -Uri http://localhost:8080/api/pokemon/cache/pikachu```

Invoke-RestMethod -Method POST -Uri http://localhost:8080/api/pokemon/cache/charizard

- **Spring WebFlux** (WebClient)

# Listar

Invoke-RestMethod -Uri http://localhost:8080/api/pokemon---



# Buscar por tipo- **Lombok**

Invoke-RestMethod -Uri "http://localhost:8080/api/pokemon/search?type=fire"

## 📚 Endpoints

# Favoritar

$body = @{favorite=$true; note="Primeiro!"} | ConvertTo-Json- **Gradle 8.5**

Invoke-RestMethod -Method PATCH -Uri http://localhost:8080/api/pokemon/1/favorite -Body $body -ContentType "application/json"

```### 1. Cachear Pokémon



---```http</div>- **Spring Boot 3.2.0**



## 🛡️ RobustezPOST /api/pokemon/cache/{nameOrId}



### Tratamento de Erros```---



Todas exceções retornam JSON padronizado:```bash

```json

{Invoke-RestMethod -Method POST -Uri http://localhost:8080/api/pokemon/cache/pikachu- **Java 17**

  "timestamp": "2025-10-27T15:30:00",

  "status": 404,```

  "error": "Not Found",

  "message": "Pokémon 'invalido' não encontrado na PokeAPI",## ⚙️ Como Executar

  "path": "/api/pokemon/cache/invalido"

}### 2. Listar (Paginado)

```

```http---- **Spring Data JPA**

### Validações

GET /api/pokemon?page=0&size=10

- ✅ IDs >= 1

- ✅ Paginação: page >= 0, size >= 1```### 1. Pré-requisitos

- ✅ Tipo não vazio

- ✅ Nota máximo 1000 caracteres

- ✅ Timeout 5s na API externa

### 3. Buscar por ID- JDK 21 instalado- **H2 Database** (em memória)

### Códigos HTTP

```http

| Código | Quando |

|--------|--------|GET /api/pokemon/{id}- Gradle (wrapper incluído)

| 200 | Consulta OK |

| 201 | Criado com sucesso |```

| 400 | Parâmetro inválido |

| 404 | Não encontrado |## 📋 Sobre o Projeto- **Spring WebFlux** (WebClient para consumir PokeAPI)

| 502 | Erro API externa |

| 500 | Erro interno |### 4. Buscar por Tipo



### Logs```http### 2. Executar a aplicação



```GET /api/pokemon/search?type=fire

2025-10-27 15:30:00 - Requisição para cachear Pokémon: pikachu

2025-10-27 15:30:01 - Pokémon 'pikachu' (ID: 1) salvo com sucesso```- **Lombok**

```



**Níveis:** INFO, WARN, ERROR, DEBUG

### 5. Favoritar```bash

📄 **Documentação completa:** Para informações detalhadas sobre tratamento de erros, validações e testes de robustez, consulte **[ROBUSTEZ.md](ROBUSTEZ.md)**

```http

---

PATCH /api/pokemon/{id}/favorite# WindowsAPI REST desenvolvida em **Spring Boot** que consome a [PokeAPI v2](https://pokeapi.co/) e persiste dados localmente em banco H2 para cache. A aplicação segue boas práticas de desenvolvimento, com arquitetura em camadas, tratamento robusto de erros e logging estruturado.- **Spring Boot Actuator**

## 🔗 Links

```

| Recurso | URL |

|---------|-----|```bash.\gradlew.bat bootRun

| Aplicação | http://localhost:8080 |

| Console H2 | http://localhost:8080/h2-console |$body = @{favorite=$true; note="Meu favorito!"} | ConvertTo-Json

| Health | http://localhost:8080/actuator/health |

| PokeAPI | https://pokeapi.co/docs/v2 |Invoke-RestMethod -Method PATCH -Uri http://localhost:8080/api/pokemon/1/favorite -Body $body -ContentType "application/json"- **Gradle**



---```



**Desenvolvido com ☕ Java 21 e 💚 Spring Boot**# Linux/Mac


### 6. Health Check

```http./gradlew bootRun### ✨ Funcionalidades

GET /actuator/health

``````



---## 📁 Estrutura do Projeto



## 🧪 Teste CompletoA aplicação estará disponível em: **http://localhost:8080**



```bash- ✅ **Cache inteligente** de Pokémon da PokeAPI

# Cachear

Invoke-RestMethod -Method POST -Uri http://localhost:8080/api/pokemon/cache/pikachu---

Invoke-RestMethod -Method POST -Uri http://localhost:8080/api/pokemon/cache/charizard

- ✅ **CRUD completo** com paginação```

# Listar

Invoke-RestMethod -Uri http://localhost:8080/api/pokemon## 💾 Banco de Dados H2



# Buscar por tipo- ✅ **Busca por tipo** (fire, water, electric, etc.)src/

Invoke-RestMethod -Uri "http://localhost:8080/api/pokemon/search?type=fire"

### Acessar o Console Web

# Favoritar

$body = @{favorite=$true; note="Primeiro!"} | ConvertTo-Json- ✅ **Sistema de favoritos** com anotações├── main/

Invoke-RestMethod -Method PATCH -Uri http://localhost:8080/api/pokemon/1/favorite -Body $body -ContentType "application/json"

```1. Certifique-se que a aplicação está rodando



---2. Acesse: **http://localhost:8080/h2-console**- ✅ **Tratamento de erros** global e padronizado│   ├── java/br/com/pokemon/



## 🛡️ Robustez3. Preencha os campos:



### Tratamento de Erros   - **JDBC URL:** `jdbc:h2:mem:pokemondb`- ✅ **Validações de entrada** robustas│   │   ├── PokemonCacheApiApplication.java  # Classe principal



Todas exceções retornam JSON padronizado:   - **User Name:** `sa`



```json   - **Password:** *(deixe vazio)*- ✅ **Logs estruturados** para debugging│   │   ├── controller/

{

  "timestamp": "2025-10-27T15:30:00",4. Clique em **"Connect"**

  "status": 404,

  "error": "Not Found",- ✅ **Health checks** com Spring Actuator│   │   │   └── PokemonController.java        # Endpoints REST

  "message": "Pokémon 'invalido' não encontrado na PokeAPI",

  "path": "/api/pokemon/cache/invalido"### Queries úteis

}

```- ✅ **Console H2** para visualizar dados│   │   ├── service/



### Validações```sql



- ✅ IDs >= 1-- Listar todos os Pokémon│   │   │   ├── PokemonService.java           # Regras de negócio

- ✅ Paginação: page >= 0, size >= 1

- ✅ Tipo não vazioSELECT * FROM POKEMON;

- ✅ Nota máximo 1000 caracteres

- ✅ Timeout 5s na API externa---│   │   │   └── PokeApiService.java           # Integração com PokeAPI



### Códigos HTTP-- Buscar favoritos



| Código | Quando |SELECT * FROM POKEMON WHERE FAVORITE = TRUE;│   │   ├── model/

|--------|--------|

| 200 | Consulta OK |

| 201 | Criado com sucesso |

| 400 | Parâmetro inválido |-- Contar por tipo## 🚀 Tecnologias│   │   │   ├── entity/

| 404 | Não encontrado |

| 502 | Erro API externa |SELECT TYPES, COUNT(*) FROM POKEMON GROUP BY TYPES;

| 500 | Erro interno |

```│   │   │   │   └── Pokemon.java              # Entidade JPA

### Logs



```

2025-10-27 15:30:00 - Requisição para cachear Pokémon: pikachu---| Tecnologia | Versão | Descrição |│   │   │   └── repository/

2025-10-27 15:30:01 - Pokémon 'pikachu' (ID: 1) salvo com sucesso

```



**Níveis:** INFO, WARN, ERROR, DEBUG## 📚 Endpoints da API|------------|--------|-----------|│   │   │       └── PokemonRepository.java    # Repository JPA



---



## 🔗 LinksBase URL: `http://localhost:8080`| **Java** | 21 (LTS) | Linguagem de programação |│   │   ├── dto/



| Recurso | URL |

|---------|-----|

| Aplicação | http://localhost:8080 |### 1. Cachear Pokémon| **Spring Boot** | 3.2.0 | Framework principal |│   │   │   ├── PokeApiResponse.java          # DTO resposta PokeAPI

| Console H2 | http://localhost:8080/h2-console |

| Health | http://localhost:8080/actuator/health |```http

| PokeAPI | https://pokeapi.co/docs/v2 |

POST /api/pokemon/cache/{nameOrId}| **Spring Data JPA** | - | Persistência de dados |│   │   │   ├── PokemonResponse.java          # DTO resposta completa

---

```

**Desenvolvido com ☕ Java 21 e 💚 Spring Boot**

Busca um Pokémon na PokeAPI e salva no banco H2.| **H2 Database** | - | Banco em memória |│   │   │   ├── PokemonSummary.java           # DTO resumo



**Exemplo:**| **Spring WebFlux** | - | Cliente HTTP reativo (WebClient) |│   │   │   └── FavoriteRequest.java          # DTO request favorito

```bash

Invoke-RestMethod -Method POST -Uri http://localhost:8080/api/pokemon/cache/pikachu| **Lombok** | - | Redução de boilerplate |│   │   └── config/

```

| **Spring Actuator** | - | Monitoramento e health checks |│   │       └── WebClientConfig.java          # Configuração WebClient

**Resposta (201):**

```json| **Gradle** | 8.5 | Build automation |│   └── resources/

{

  "id": 1,│       └── application.properties            # Configurações

  "idPokeApi": 25,

  "name": "pikachu",---```

  "height": 4,

  "weight": 60,

  "ability": "static",

  "types": "electric",## 📁 Estrutura do Projeto## ⚙️ Configuração

  "cachedAt": "2025-10-27T15:30:00",

  "favorite": false,

  "note": null

}```### application.properties

```

src/

---

├── main/```properties

### 2. Listar Pokémon (Paginado)

```http│   ├── java/br/com/pokemon/# Application

GET /api/pokemon?page=0&size=10

```│   │   ├── PokemonCacheApiApplication.java    # 🎯 Classe principalspring.application.name=pokemon-cache-api



**Exemplo:**│   │   ├── controller/server.port=8080

```bash

Invoke-RestMethod -Uri "http://localhost:8080/api/pokemon?page=0&size=10"│   │   │   └── PokemonController.java          # 🌐 Endpoints REST

```

│   │   ├── service/# H2 Database

---

│   │   │   ├── PokemonService.java             # 💼 Lógica de negóciospring.datasource.url=jdbc:h2:mem:pokemondb

### 3. Buscar por ID

```http│   │   │   └── PokeApiService.java             # 🔌 Integração API externaspring.datasource.username=sa

GET /api/pokemon/{id}

```│   │   ├── model/spring.datasource.password=



**Exemplo:**│   │   │   ├── entity/

```bash

Invoke-RestMethod -Uri http://localhost:8080/api/pokemon/1│   │   │   │   └── Pokemon.java                # 📦 Entidade JPA# JPA

```

│   │   │   └── repository/spring.jpa.hibernate.ddl-auto=update

---

│   │   │       └── PokemonRepository.java      # 💾 Acesso ao bancospring.jpa.show-sql=true

### 4. Buscar por Tipo

```http│   │   ├── dto/

GET /api/pokemon/search?type={typeName}

```│   │   │   ├── PokeApiResponse.java            # 📥 DTO da PokeAPI# H2 Console



**Exemplo:**│   │   │   ├── PokemonResponse.java            # 📤 DTO resposta completaspring.h2.console.enabled=true

```bash

Invoke-RestMethod -Uri "http://localhost:8080/api/pokemon/search?type=fire"│   │   │   ├── PokemonSummary.java             # 📋 DTO resumospring.h2.console.path=/h2-console

```

│   │   │   ├── FavoriteRequest.java            # ⭐ DTO favorito

---

│   │   │   └── ErrorResponse.java              # ❌ DTO de erro# PokeAPI

### 5. Atualizar Favorito

```http│   │   ├── exception/pokeapi.base-url=https://pokeapi.co/api/v2

PATCH /api/pokemon/{id}/favorite

Content-Type: application/json│   │   │   ├── GlobalExceptionHandler.java     # 🛡️ Tratamento global```



{│   │   │   ├── PokemonNotFoundException.java   # 🔍 Exceção customizada

  "favorite": true,

  "note": "Meu Pokémon favorito!"│   │   │   └── PokeApiException.java           # 🌐 Exceção da API## 🔧 Como Executar

}

```│   │   └── config/



**Exemplo:**│   │       └── WebClientConfig.java            # ⚙️ Configuração WebClient### Pré-requisitos

```bash

$body = @{favorite=$true; note="Starter do jogo!"} | ConvertTo-Json│   └── resources/- Java 17 ou superior

Invoke-RestMethod -Method PATCH -Uri http://localhost:8080/api/pokemon/1/favorite -Body $body -ContentType "application/json"

```│       └── application.properties              # 🔧 Configurações- Gradle (ou usar o wrapper incluído)



---```



### 6. Health Check### Executar com Gradle

```http

GET /actuator/health### 🏗️ Arquitetura

```

```bash

---

```# Windows

## 🧪 Teste Completo

┌─────────────┐.\gradlew bootRun

Execute em sequência para testar todas as funcionalidades:

│   Cliente   │ (Browser, Postman, etc.)

```bash

# 1. Cachear Pokémon└──────┬──────┘# Linux/Mac

Invoke-RestMethod -Method POST -Uri http://localhost:8080/api/pokemon/cache/pikachu

Invoke-RestMethod -Method POST -Uri http://localhost:8080/api/pokemon/cache/charizard       │ HTTP Request./gradlew bootRun

Invoke-RestMethod -Method POST -Uri http://localhost:8080/api/pokemon/cache/bulbasaur

       ▼```

# 2. Listar todos

Invoke-RestMethod -Uri http://localhost:8080/api/pokemon┌─────────────────────┐



# 3. Buscar por tipo│   🌐 Controller     │ (Recebe requisições)### Compilar o projeto

Invoke-RestMethod -Uri "http://localhost:8080/api/pokemon/search?type=fire"

└──────────┬──────────┘

# 4. Ver detalhes

Invoke-RestMethod -Uri http://localhost:8080/api/pokemon/1           │```bash



# 5. Marcar como favorito           ▼# Windows

$body = @{favorite=$true; note="Primeiro capturado!"} | ConvertTo-Json

Invoke-RestMethod -Method PATCH -Uri http://localhost:8080/api/pokemon/1/favorite -Body $body -ContentType "application/json"┌─────────────────────┐.\gradlew build



# 6. Ver no H2 Console│   💼 Service        │ (Lógica de negócio)

# Acesse: http://localhost:8080/h2-console

# Execute: SELECT * FROM POKEMON WHERE FAVORITE = TRUE;└──────────┬──────────┘# Linux/Mac

```

           │./gradlew build

---

     ┌─────┴─────┐```

## 🛡️ Robustez e Segurança

     ▼           ▼

### 1. Tratamento Global de Erros

┌─────────┐  ┌──────────────┐### Executar o JAR gerado

A aplicação possui `@ControllerAdvice` que captura e trata todas as exceções de forma padronizada.

│ 💾 Repo │  │ 🔌 PokeAPI   │

**Exemplo - Pokémon não encontrado:**

```bash│   H2    │  │   Service    │```bash

# Requisição

Invoke-RestMethod -Method POST -Uri http://localhost:8080/api/pokemon/cache/pokemoninexistente└─────────┘  └──────────────┘java -jar build/libs/pokemon-cache-api-1.0.0.jar

```

``````

**Resposta (404):**

```json

{

  "timestamp": "2025-10-27T15:30:00",---A aplicação estará disponível em: `http://localhost:8080`

  "status": 404,

  "error": "Not Found",

  "message": "Pokémon 'pokemoninexistente' não encontrado na PokeAPI",

  "path": "/api/pokemon/cache/pokemoninexistente"## ⚙️ Configuração## 📊 Console H2

}

```



### 2. Validações Implementadas### Pré-requisitosAcesse o console H2 em: `http://localhost:8080/h2-console`



- ✅ **Parâmetros de entrada** - `@Valid`, `@Min`, `@Size`

- ✅ **IDs positivos** - IDs devem ser >= 1

- ✅ **Paginação** - page >= 0, size >= 1- ✅ **JDK 21** ou superior ([Download](https://www.oracle.com/java/technologies/downloads/#java21))**Configurações de conexão:**

- ✅ **Tipo não vazio** - Validação de busca por tipo

- ✅ **Tamanho da nota** - Máximo 1000 caracteres- ✅ **Gradle** (incluído via wrapper)- JDBC URL: `jdbc:h2:mem:pokemondb`



**Exemplo - Parâmetro inválido:**- ✅ **Git** (opcional)- User Name: `sa`

```bash

# Requisição com ID inválido- Password: (vazio)

Invoke-RestMethod -Uri http://localhost:8080/api/pokemon/abc

```### Instalação



**Resposta (400):**## 🔌 Endpoints da API

```json

{```bash

  "timestamp": "2025-10-27T15:30:00",

  "status": 400,# 1. Clone o repositório### 1. Cache de Pokémon

  "error": "Bad Request",

  "message": "Parâmetro 'idLocal' deve ser do tipo Long, mas recebeu: abc",git clone https://github.com/seu-usuario/pokemon-cache-api.git

  "path": "/api/pokemon/abc"

}cd pokemon-cache-apiConsulta a PokeAPI e persiste/atualiza no H2.

```



### 3. Exceções Customizadas

# 2. Build o projeto```bash

- `PokemonNotFoundException` - Pokémon não encontrado (404)

- `PokeApiException` - Erro na API externa (502).\gradlew.bat build          # WindowsPOST /api/pokemon/cache/{nameOrId}

- `IllegalArgumentException` - Argumentos inválidos (400)

./gradlew build              # Linux/Mac```

### 4. Timeout na API Externa



- **Timeout configurado:** 5 segundos

- **Evita:** Requisições travadas indefinidamente# 3. Execute a aplicação**Exemplo:**

- **Resposta:** Erro claro quando timeout ocorre

.\gradlew.bat bootRun        # Windows```bash

### 5. Logs Estruturados

./gradlew bootRun            # Linux/Maccurl -X POST http://localhost:8080/api/pokemon/cache/pikachu

Logs em todos os pontos críticos da aplicação:

```

```

2025-10-27 15:30:00 - Requisição para cachear Pokémon: pikachucurl -X POST http://localhost:8080/api/pokemon/cache/25

2025-10-27 15:30:00 - Buscando Pokémon 'pikachu' na PokeAPI

2025-10-27 15:30:01 - Pokémon 'pikachu' encontrado com sucesso (ID: 25)A aplicação estará disponível em: **`http://localhost:8080`**```

2025-10-27 15:30:01 - Pokémon 'pikachu' (ID: 1) salvo com sucesso no banco local

```



**Níveis:**---**Resposta:**

- `INFO` - Operações importantes

- `WARN` - Situações anormais```json

- `ERROR` - Erros graves

- `DEBUG` - Detalhes de execução## 🔧 Como Executar{



### 6. Códigos HTTP Semânticos  "id": 1,



| Código | Quando retorna |### Opção 1: Com Gradle (Recomendado)  "idPokeApi": 25,

|--------|----------------|

| `200 OK` | Consulta bem-sucedida |  "name": "pikachu",

| `201 Created` | Pokémon cacheado com sucesso |

| `400 Bad Request` | Parâmetros inválidos ou validação falhou |```bash  "height": 4,

| `404 Not Found` | Pokémon não encontrado |

| `502 Bad Gateway` | Erro na PokeAPI (externa) |.\gradlew.bat bootRun  "weight": 60,

| `500 Internal Server Error` | Erro interno não previsto |

```  "ability": "static",

### 7. Transações

  "types": "electric",

- Operações de escrita protegidas com `@Transactional`

- Rollback automático em caso de erro### Opção 2: Com JAR  "cachedAt": "2025-10-22T20:30:00",

- Garantia de consistência dos dados

  "favorite": false,

---

```bash  "note": null

## 📁 Estrutura do Projeto

# Build}

```

src/main/java/br/com/pokemon/.\gradlew.bat build```

├── controller/          # Endpoints REST

├── service/             # Lógica de negócio

├── model/

│   ├── entity/         # Entidades JPA# Execute### 2. Listar Pokémon (Paginado)

│   └── repository/     # Acesso ao banco

├── dto/                # Objetos de transferênciajava -jar build/libs/pokemon-cache-api-1.0.0.jar

├── exception/          # Tratamento de erros

└── config/             # Configurações```Retorna lista paginada de Pokémon.

```



---

### Opção 3: Via IDE (VS Code, IntelliJ)```bash

## 🔗 Links Úteis

GET /api/pokemon?page={page}&size={size}

| Recurso | URL |

|---------|-----|1. Abra o projeto na IDE```

| **Aplicação** | http://localhost:8080 |

| **Console H2** | http://localhost:8080/h2-console |2. Execute a classe `PokemonCacheApiApplication.java`

| **Health Check** | http://localhost:8080/actuator/health |

| **PokeAPI** | https://pokeapi.co/docs/v2 |3. Aguarde o servidor iniciar**Exemplo:**



---```bash



## 📖 Documentação Adicional**Saída esperada:**curl http://localhost:8080/api/pokemon?page=0&size=10



- **ROBUSTEZ.md** - Detalhes completos sobre tratamento de erros e testes de validação``````


✅ Aplicação rodando em **`http://localhost:8080`**    }

  ],

---  "pageable": {...},

  "totalElements": 1,

## 💾 Banco de Dados H2  "totalPages": 1

}

### 🌐 Acessar Console Web```



1. **Certifique-se que a aplicação está rodando**### 3. Buscar Pokémon por ID

2. Acesse: **`http://localhost:8080/h2-console`**

3. Preencha as configurações:Retorna o registro completo do H2.



| Campo | Valor |```bash

|-------|-------|GET /api/pokemon/{idLocal}

| **Driver Class** | `org.h2.Driver` |```

| **JDBC URL** | `jdbc:h2:mem:pokemondb` |

| **User Name** | `sa` |**Exemplo:**

| **Password** | *(deixe em branco)* |```bash

curl http://localhost:8080/api/pokemon/1

4. Clique em **"Connect"**```



### 📊 Visualizar Dados**Resposta:**

```json

Após conectar, execute queries SQL:{

  "id": 1,

```sql  "idPokeApi": 25,

-- Listar todos os Pokémon  "name": "pikachu",

SELECT * FROM POKEMON;  "height": 4,

  "weight": 60,

-- Buscar por nome  "ability": "static",

SELECT * FROM POKEMON WHERE NAME = 'pikachu';  "types": "electric",

  "cachedAt": "2025-10-22T20:30:00",

-- Pokémon favoritos  "favorite": false,

SELECT * FROM POKEMON WHERE FAVORITE = TRUE;  "note": null

}

-- Contar por tipo```

SELECT TYPES, COUNT(*) FROM POKEMON GROUP BY TYPES;

### 4. Buscar por Tipo

-- Últimos cacheados

SELECT * FROM POKEMON ORDER BY CACHED_AT DESC LIMIT 10;Filtra Pokémon por tipo (case-insensitive).

```

```bash

### 🗄️ Estrutura da Tabela POKEMONGET /api/pokemon/search?type={typeName}&page={page}&size={size}

```

| Coluna | Tipo | Descrição |

|--------|------|-----------|**Exemplo:**

| `ID` | BIGINT | ID local (PK, auto-incremento) |```bash

| `ID_POKE_API` | INTEGER | ID do Pokémon na PokeAPI (único) |curl "http://localhost:8080/api/pokemon/search?type=electric&page=0&size=10"

| `NAME` | VARCHAR(255) | Nome do Pokémon |```

| `HEIGHT` | INTEGER | Altura (decímetros) |

| `WEIGHT` | INTEGER | Peso (hectogramas) |**Resposta:**

| `ABILITY` | VARCHAR(255) | Primeira habilidade |```json

| `TYPES` | VARCHAR(500) | Tipos (CSV: "fire, flying") |{

| `CACHED_AT` | TIMESTAMP | Data/hora do cache |  "content": [

| `FAVORITE` | BOOLEAN | Marcado como favorito? |    {

| `NOTE` | VARCHAR(1000) | Anotação pessoal |      "id": 1,

      "idPokeApi": 25,

---      "name": "pikachu",

      "types": "electric",

## 📚 Endpoints da API      "cachedAt": "2025-10-22T20:30:00"

    }

Base URL: **`http://localhost:8080`**  ],

  "totalElements": 1

### 1️⃣ Cachear Pokémon da PokeAPI}

```

Busca um Pokémon na PokeAPI e salva no banco local.

### 5. Favoritar/Adicionar Nota

```http

POST /api/pokemon/cache/{nameOrId}Atualiza campos favorite e note.

```

```bash

**Parâmetros:**PATCH /api/pokemon/{idLocal}/favorite

- `nameOrId` (path) - Nome ou ID do Pokémon (ex: `pikachu` ou `25`)Content-Type: application/json



**Exemplo:**{

```bash  "favorite": true,

# Windows PowerShell  "note": "Meu Pokémon favorito!"

Invoke-RestMethod -Method POST -Uri http://localhost:8080/api/pokemon/cache/pikachu}

```

# Linux/Mac

curl -X POST http://localhost:8080/api/pokemon/cache/pikachu**Exemplo:**

``````bash

curl -X PATCH http://localhost:8080/api/pokemon/1/favorite \

**Resposta (201 Created):**  -H "Content-Type: application/json" \

```json  -d "{\"favorite\": true, \"note\": \"Meu Pokémon favorito!\"}"

{```

  "id": 1,

  "idPokeApi": 25,**Resposta:**

  "name": "pikachu",```json

  "height": 4,{

  "weight": 60,  "id": 1,

  "ability": "static",  "idPokeApi": 25,

  "types": "electric",  "name": "pikachu",

  "cachedAt": "2025-10-27T15:30:00",  "height": 4,

  "favorite": false,  "weight": 60,

  "note": null  "ability": "static",

}  "types": "electric",

```  "cachedAt": "2025-10-22T20:30:00",

  "favorite": true,

---  "note": "Meu Pokémon favorito!"

}

### 2️⃣ Listar Pokémon (Paginado)```



Lista todos os Pokémon do banco local com paginação.### 6. Health Check (Actuator)



```httpVerifica a saúde da aplicação.

GET /api/pokemon?page={page}&size={size}

``````bash

GET /actuator/health

**Parâmetros:**```

- `page` (query, opcional) - Número da página (default: 0)

- `size` (query, opcional) - Tamanho da página (default: 10)**Exemplo:**

```bash

**Exemplo:**curl http://localhost:8080/actuator/health

```bash```

# Windows PowerShell

Invoke-RestMethod -Uri "http://localhost:8080/api/pokemon?page=0&size=5"**Resposta:**

```json

# Linux/Mac{

curl http://localhost:8080/api/pokemon?page=0&size=5  "status": "UP"

```}

```

**Resposta (200 OK):**

```json## 📝 Fluxo de Uso Completo

{

  "content": [```bash

    {# 1. Cachear alguns Pokémon

      "id": 1,curl -X POST http://localhost:8080/api/pokemon/cache/pikachu

      "idPokeApi": 25,curl -X POST http://localhost:8080/api/pokemon/cache/charizard

      "name": "pikachu",curl -X POST http://localhost:8080/api/pokemon/cache/bulbasaur

      "types": "electric",curl -X POST http://localhost:8080/api/pokemon/cache/squirtle

      "cachedAt": "2025-10-27T15:30:00"

    },```

  "totalPages": 1

}## ⚠️ Tratamento de Erros

```

### Pokémon não encontrado na PokeAPI

---```bash

curl -X POST http://localhost:8080/api/pokemon/cache/pokemonInexistente

### 3️⃣ Buscar Pokémon por ID Local# Retorna: HTTP 404 Not Found

```

Retorna um Pokémon específico do banco.

### ID local inexistente

```http```bash

GET /api/pokemon/{id}curl http://localhost:8080/api/pokemon/999

```# Retorna: HTTP 404 Not Found

```

**Parâmetros:**

- `id` (path) - ID local do banco## 🎯 Campos Persistidos



**Exemplo:**A entidade `Pokemon` armazena:

```bash- **id**: ID local (auto-incremento)

# Windows PowerShell- **idPokeApi**: ID do Pokémon na PokeAPI

Invoke-RestMethod -Uri http://localhost:8080/api/pokemon/1- **name**: Nome do Pokémon

- **height**: Altura

# Linux/Mac- **weight**: Peso

curl http://localhost:8080/api/pokemon/1- **ability**: Primeira habilidade

```- **types**: Lista de tipos (CSV)

- **cachedAt**: Timestamp do cache

**Resposta (200 OK):**- **favorite**: Flag de favorito (padrão: false)

```json- **note**: Nota/observação (opcional)

{

  "id": 1,## 📚 Referências

  "idPokeApi": 25,

  "name": "pikachu",- [PokeAPI v2](https://pokeapi.co/)

  "height": 4,- [Spring Boot Documentation](https://spring.io/projects/spring-boot)

  "weight": 60,- [H2 Database](https://www.h2database.com/)

  "ability": "static",

  "types": "electric",## 👨‍💻 Desenvolvimento

  "cachedAt": "2025-10-27T15:30:00",

  "favorite": false,### Estrutura MVC

  "note": null

}- **Model**: `Pokemon.java` (entidade JPA)

```- **View**: API REST (JSON responses)

- **Controller**: `PokemonController.java` (endpoints REST)

---- **Service**: 

  - `PokemonService.java` (regras de negócio)

### 4️⃣ Buscar por Tipo  - `PokeApiService.java` (integração externa)

- **Repository**: `PokemonRepository.java` (acesso a dados)

Filtra Pokémon por tipo (case-insensitive).

### Padrões Utilizados

```http

GET /api/pokemon/search?type={typeName}&page={page}&size={size}- Injeção de dependência por construtor

```- DTOs para transferência de dados

- Transações com `@Transactional`

**Parâmetros:**- Queries customizadas com `@Query`

- `type` (query) - Nome do tipo (ex: `fire`, `water`, `electric`)- Validações e tratamento de erros

- `page` (query, opcional) - Número da página (default: 0)

- `size` (query, opcional) - Tamanho da página (default: 10)---





```bash
# Windows PowerShell
Invoke-RestMethod -Uri "http://localhost:8080/api/pokemon/search?type=fire"

# Linux/Mac
curl "http://localhost:8080/api/pokemon/search?type=fire"
```

**Resposta (200 OK):**
```json
{
  "content": [
    {
      "id": 2,
      "idPokeApi": 6,
      "name": "charizard",
      "types": "fire, flying",
      "cachedAt": "2025-10-27T15:31:00"
    }
  ],
  "totalElements": 1
}
```

---

### 5️⃣ Atualizar Favorito/Nota

Marca um Pokémon como favorito e/ou adiciona uma nota.

```http
PATCH /api/pokemon/{id}/favorite
Content-Type: application/json
```

**Parâmetros:**
- `id` (path) - ID local do Pokémon
- Body (JSON):
```json
{
  "favorite": true,
  "note": "Meu Pokémon favorito!"
}
```

**Exemplo:**
```bash
# Windows PowerShell
$body = @{favorite=$true; note="Starter do jogo!"} | ConvertTo-Json
Invoke-RestMethod -Method PATCH -Uri http://localhost:8080/api/pokemon/1/favorite -Body $body -ContentType "application/json"

# Linux/Mac
curl -X PATCH http://localhost:8080/api/pokemon/1/favorite \
  -H "Content-Type: application/json" \
  -d '{"favorite": true, "note": "Starter do jogo!"}'
```

**Resposta (200 OK):**
```json
{
  "id": 1,
  "idPokeApi": 25,
  "name": "pikachu",
  "height": 4,
  "weight": 60,
  "ability": "static",
  "types": "electric",
  "cachedAt": "2025-10-27T15:30:00",
  "favorite": true,
  "note": "Starter do jogo!"
}
```

---

### 6️⃣ Health Check

Verifica o status da aplicação.

```http
GET /actuator/health
```

**Exemplo:**
```bash
# Windows PowerShell
Invoke-RestMethod -Uri http://localhost:8080/actuator/health

# Linux/Mac
curl http://localhost:8080/actuator/health
```

**Resposta (200 OK):**
```json
{
  "status": "UP",
  "components": {
    "db": {
      "status": "UP",
      "details": {
        "database": "H2",
        "validationQuery": "isValid()"
      }
    },
    "diskSpace": {
      "status": "UP"
    },
    "ping": {
      "status": "UP"
    }
  }
}
```

---

## 🧪 Testes

### Fluxo Completo de Uso

```bash
# 1. Cachear alguns Pokémon
Invoke-RestMethod -Method POST -Uri http://localhost:8080/api/pokemon/cache/pikachu
Invoke-RestMethod -Method POST -Uri http://localhost:8080/api/pokemon/cache/charizard
Invoke-RestMethod -Method POST -Uri http://localhost:8080/api/pokemon/cache/bulbasaur
Invoke-RestMethod -Method POST -Uri http://localhost:8080/api/pokemon/cache/squirtle
Invoke-RestMethod -Method POST -Uri http://localhost:8080/api/pokemon/cache/mewtwo

# 2. Listar todos (5 Pokémon por página)
Invoke-RestMethod -Uri "http://localhost:8080/api/pokemon?page=0&size=5"

# 3. Buscar por tipo
Invoke-RestMethod -Uri "http://localhost:8080/api/pokemon/search?type=fire"
Invoke-RestMethod -Uri "http://localhost:8080/api/pokemon/search?type=water"

# 4. Ver detalhes de um específico
Invoke-RestMethod -Uri http://localhost:8080/api/pokemon/1

# 5. Marcar como favorito
$body = @{favorite=$true; note="Primeiro Pokémon capturado!"} | ConvertTo-Json
Invoke-RestMethod -Method PATCH -Uri http://localhost:8080/api/pokemon/1/favorite -Body $body -ContentType "application/json"

# 6. Ver no H2 Console
# Acesse: http://localhost:8080/h2-console
# Execute: SELECT * FROM POKEMON WHERE FAVORITE = TRUE;
```

---

## 🛡️ Robustez e Tratamento de Erros

A aplicação possui tratamento global de exceções com respostas padronizadas.

### Exemplo: Pokémon não encontrado

**Requisição:**
```bash
Invoke-RestMethod -Method POST -Uri http://localhost:8080/api/pokemon/cache/pokemoninexistente
```

**Resposta (404 Not Found):**
```json
{
  "timestamp": "2025-10-27T15:30:00",
  "status": 404,
  "error": "Not Found",
  "message": "Pokémon 'pokemoninexistente' não encontrado na PokeAPI",
  "path": "/api/pokemon/cache/pokemoninexistente"
}
```

### Exemplo: Parâmetro inválido

**Requisição:**
```bash
Invoke-RestMethod -Uri http://localhost:8080/api/pokemon/abc
```

**Resposta (400 Bad Request):**
```json
{
  "timestamp": "2025-10-27T15:30:00",
  "status": 400,
  "error": "Bad Request",
  "message": "Parâmetro 'idLocal' deve ser do tipo Long, mas recebeu: abc",
  "path": "/api/pokemon/abc"
}
```

### Códigos HTTP Retornados

| Código | Significado | Quando ocorre |
|--------|-------------|---------------|
| `200 OK` | Sucesso | GET bem-sucedido |
| `201 Created` | Criado | POST bem-sucedido |
| `400 Bad Request` | Requisição inválida | Parâmetros inválidos, validação falhou |
| `404 Not Found` | Não encontrado | Pokémon não existe |
| `502 Bad Gateway` | Erro na API externa | PokeAPI indisponível ou erro |
| `500 Internal Server Error` | Erro interno | Erro inesperado no servidor |

📄 **Documentação completa de erros:** Consulte [ROBUSTEZ.md](ROBUSTEZ.md)



## 🔗 Links Úteis

| Recurso | URL |
|---------|-----|
| **API Base** | http://localhost:8080 |
| **Console H2** | http://localhost:8080/h2-console |
| **Health Check** | http://localhost:8080/actuator/health |
| **Info** | http://localhost:8080/actuator/info |
| **PokeAPI Docs** | https://pokeapi.co/docs/v2 |

## 👨‍💻 Autor

Desenvolvido por Paulo Henrique Araujo



## ⭐ Gostou do Projeto?

Se este projeto foi útil, deixe uma ⭐ no repositório!


<div align="center">

**[⬆ Voltar ao topo](#-pokemon-cache-api)**

</div>
