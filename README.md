# 🎮 Pokémon Cache API

API REST para gerenciamento e cache de dados de Pokémon, integrada com a PokeAPI oficial.

---

## 📋 Índice

- [Sobre o Projeto](#-sobre-o-projeto)
- [Tecnologias](#-tecnologias)
- [Funcionalidades](#-funcionalidades)
- [Instalação](#-instalação)
- [Configuração](#️-configuração)
- [Uso da API](#-uso-da-api)
- [Endpoints](#-endpoints)
- [Exemplos de Requisições](#-exemplos-de-requisições)
- [Modelo de Dados](#-modelo-de-dados)
- [Tratamento de Erros](#️-tratamento-de-erros)
- [Testes](#-testes)
- [Deploy](#-deploy)
- [Contato](#-contato)
- [Contribuindo](#-contribuindo)

---

## 🎯 Sobre o Projeto

Esta API permite **cachear informações de Pokémon** da PokeAPI oficial em um banco de dados local (H2), possibilitando buscas mais rápidas, adição de notas pessoais e marcação de favoritos. É ideal para aplicações que necessitam de consultas frequentes a dados de Pokémon sem sobrecarregar a API oficial.

**Diferenciais:**
- ✅ Cache inteligente com persistência local
- ✅ Sistema de favoritos com anotações
- ✅ Busca rápida por tipo
- ✅ Tratamento robusto de erros
- ✅ Documentação completa de robustez

---

## 🚀 Tecnologias

- **Java 21** (LTS)
- **Spring Boot 3.2.0**
- **Spring Data JPA**
- **H2 Database** (em memória)
- **Spring WebFlux** (WebClient)
- **Lombok**
- **Gradle 8.5**
- **Spring Boot Actuator**

---

## ✨ Funcionalidades

✅ Cache de Pokémon da PokeAPI  
✅ Busca por ID local ou nome na API  
✅ Listagem paginada  
✅ Filtro por tipo (fire, water, electric, etc.)  
✅ Sistema de favoritos  
✅ Anotações personalizadas (até 1000 caracteres)  
✅ Validações de entrada robustas  
✅ Logs estruturados (INFO, WARN, ERROR, DEBUG)  
✅ Timeout configurado (5s) para API externa  
✅ Health checks com Spring Actuator  

---

## 📦 Instalação

### Pré-requisitos

- Java 21 ou superior
- Gradle (wrapper incluído no projeto)

### Passos

1. **Clone o repositório:**

```bash
git clone https://github.com/PauloAraujoDEV25/INICIANDO_SPRING_BOOT.git
cd INICIANDO_SPRING_BOOT
```

2. **Compile o projeto:**

```bash
# Windows
.\gradlew.bat build

# Linux/Mac
./gradlew build
```

3. **Execute a aplicação:**

```bash
# Windows
.\gradlew.bat bootRun

# Linux/Mac
./gradlew bootRun
```

4. **Acesse a aplicação:**

A API estará disponível em **http://localhost:8080**

---

## ⚙️ Configuração

### Arquivo `application.properties`

```properties
# Aplicação
spring.application.name=pokemon-cache-api
server.port=8080

# Banco de Dados H2
spring.datasource.url=jdbc:h2:mem:pokemondb
spring.datasource.username=sa
spring.datasource.password=

# JPA
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

# Console H2
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console

# PokeAPI
pokeapi.base-url=https://pokeapi.co/api/v2

# Logging
logging.level.br.com.pokemon=INFO
```

### Console H2

Para visualizar os dados no banco:

1. Acesse: **http://localhost:8080/h2-console**
2. Configure a conexão:
   - **JDBC URL:** `jdbc:h2:mem:pokemondb`
   - **User Name:** `sa`
   - **Password:** *(deixe vazio)*
3. Clique em **"Connect"**

**Queries úteis:**

```sql
-- Listar todos os Pokémon
SELECT * FROM POKEMON;

-- Buscar favoritos
SELECT * FROM POKEMON WHERE FAVORITE = TRUE;

-- Contar por tipo
SELECT TYPES, COUNT(*) FROM POKEMON GROUP BY TYPES;

-- Últimos cacheados
SELECT * FROM POKEMON ORDER BY CACHED_AT DESC LIMIT 10;
```

---

## 📖 Uso da API

### Documentação Swagger (Planejado)

Em breve a documentação interativa estará disponível em:
- Desenvolvimento: http://localhost:8080/swagger-ui.html

---

## 🔌 Endpoints

Base URL: **`http://localhost:8080`**

### 1. Cachear Pokémon

Busca um Pokémon na PokeAPI e salva no banco local.

**Request:**
```http
POST /api/pokemon/cache/{nameOrId}
```

**Parâmetros:**
- `nameOrId` (path): Nome ou ID do Pokémon (ex: `"pikachu"` ou `"25"`)

**Respostas:**
- `201 Created`: Pokémon cacheado com sucesso
- `404 Not Found`: Pokémon não encontrado na PokeAPI
- `502 Bad Gateway`: Erro na integração com PokeAPI

---

### 2. Listar Todos os Pokémon

Retorna lista paginada de Pokémon cacheados.

**Request:**
```http
GET /api/pokemon?page=0&size=20
```

**Parâmetros de Query:**
- `page` (opcional): Número da página (padrão: `0`)
- `size` (opcional): Tamanho da página (padrão: `10`)

**Resposta:**
- `200 OK`: Lista paginada com dados

---

### 3. Buscar Pokémon por ID Local

Retorna um Pokémon específico pelo ID do banco local.

**Request:**
```http
GET /api/pokemon/{id}
```

**Parâmetros:**
- `id` (path): ID local do Pokémon no banco

**Respostas:**
- `200 OK`: Pokémon encontrado
- `404 Not Found`: Pokémon não encontrado

---

### 4. Buscar por Tipo

Busca Pokémon por tipo com paginação.

**Request:**
```http
GET /api/pokemon/search?type=fire&page=0&size=20
```

**Parâmetros de Query:**
- `type` (obrigatório): Tipo do Pokémon (ex: `"fire"`, `"water"`, `"electric"`)
- `page` (opcional): Número da página (padrão: `0`)
- `size` (opcional): Tamanho da página (padrão: `10`)

**Respostas:**
- `200 OK`: Busca realizada com sucesso
- `400 Bad Request`: Tipo inválido ou vazio

---

### 5. Favoritar/Desfavoritar Pokémon

Marca ou desmarca um Pokémon como favorito e adiciona nota.

**Request:**
```http
PATCH /api/pokemon/{id}/favorite
Content-Type: application/json
```

**Parâmetros:**
- `id` (path): ID local do Pokémon

**Body:**
```json
{
  "favorite": true,
  "note": "Meu Pokémon favorito!"
}
```

**Respostas:**
- `200 OK`: Pokémon atualizado com sucesso
- `404 Not Found`: Pokémon não encontrado
- `400 Bad Request`: Nota excede 1000 caracteres

---

### 6. Health Check

Verifica o status da aplicação.

**Request:**
```http
GET /actuator/health
```

**Resposta:**
- `200 OK`: Aplicação funcionando corretamente

---

## 💻 Exemplos de Requisições

### Cachear Pikachu

```bash
# Windows PowerShell
Invoke-RestMethod -Method POST -Uri http://localhost:8080/api/pokemon/cache/pikachu

# Linux/Mac
curl -X POST http://localhost:8080/api/pokemon/cache/pikachu
```

---

### Listar Pokémon (primeira página)

```bash
# Windows PowerShell
Invoke-RestMethod -Uri "http://localhost:8080/api/pokemon?page=0&size=10"

# Linux/Mac
curl -X GET "http://localhost:8080/api/pokemon?page=0&size=10"
```

---

### Buscar Pokémon do tipo fogo

```bash
# Windows PowerShell
Invoke-RestMethod -Uri "http://localhost:8080/api/pokemon/search?type=fire&page=0&size=20"

# Linux/Mac
curl -X GET "http://localhost:8080/api/pokemon/search?type=fire&page=0&size=20"
```

---

### Marcar como favorito

```bash
# Windows PowerShell
$body = @{favorite=$true; note="Muito forte em batalhas!"} | ConvertTo-Json
Invoke-RestMethod -Method PATCH -Uri http://localhost:8080/api/pokemon/1/favorite -Body $body -ContentType "application/json"

# Linux/Mac
curl -X PATCH http://localhost:8080/api/pokemon/1/favorite \
  -H "Content-Type: application/json" \
  -d '{
    "favorite": true,
    "note": "Muito forte em batalhas!"
  }'
```

---

### Teste Completo

Execute em sequência para validar todas as funcionalidades:

```bash
# 1. Cachear Pokémon
Invoke-RestMethod -Method POST -Uri http://localhost:8080/api/pokemon/cache/pikachu
Invoke-RestMethod -Method POST -Uri http://localhost:8080/api/pokemon/cache/charizard
Invoke-RestMethod -Method POST -Uri http://localhost:8080/api/pokemon/cache/bulbasaur
Invoke-RestMethod -Method POST -Uri http://localhost:8080/api/pokemon/cache/squirtle

# 2. Listar todos
Invoke-RestMethod -Uri http://localhost:8080/api/pokemon

# 3. Buscar por tipo
Invoke-RestMethod -Uri "http://localhost:8080/api/pokemon/search?type=fire"

# 4. Ver detalhes
Invoke-RestMethod -Uri http://localhost:8080/api/pokemon/1

# 5. Marcar como favorito
$body = @{favorite=$true; note="Primeiro capturado!"} | ConvertTo-Json
Invoke-RestMethod -Method PATCH -Uri http://localhost:8080/api/pokemon/1/favorite -Body $body -ContentType "application/json"

# 6. Verificar no H2 Console
# Acesse: http://localhost:8080/h2-console
# Execute: SELECT * FROM POKEMON WHERE FAVORITE = TRUE;
```

---

## 📊 Modelo de Dados

### PokemonResponse

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
  "note": "Meu Pokémon favorito!"
}
```

### PagePokemonSummary

```json
{
  "content": [
    {
      "id": 1,
      "idPokeApi": 25,
      "name": "pikachu",
      "types": "electric",
      "cachedAt": "2025-10-27T15:30:00"
    }
  ],
  "pageable": {
    "pageNumber": 0,
    "pageSize": 10
  },
  "totalPages": 5,
  "totalElements": 50,
  "first": true,
  "last": false
}
```

### Estrutura da Entidade `Pokemon`

| Campo | Tipo | Descrição |
|-------|------|-----------|
| `id` | BIGINT | ID local (PK, auto-incremento) |
| `idPokeApi` | INTEGER | ID do Pokémon na PokeAPI (único) |
| `name` | VARCHAR(255) | Nome do Pokémon |
| `height` | INTEGER | Altura (decímetros) |
| `weight` | INTEGER | Peso (hectogramas) |
| `ability` | VARCHAR(255) | Primeira habilidade |
| `types` | VARCHAR(500) | Tipos (CSV: "fire, flying") |
| `cachedAt` | TIMESTAMP | Data/hora do cache |
| `favorite` | BOOLEAN | Marcado como favorito? |
| `note` | VARCHAR(1000) | Anotação pessoal |

---

## ⚠️ Tratamento de Erros

A API utiliza códigos HTTP padrão e retorna respostas JSON padronizadas:

### Códigos HTTP

| Código | Significado | Quando ocorre |
|--------|-------------|---------------|
| `200 OK` | Sucesso | GET bem-sucedido |
| `201 Created` | Criado | POST bem-sucedido |
| `400 Bad Request` | Requisição inválida | Parâmetros inválidos, validação falhou |
| `404 Not Found` | Não encontrado | Pokémon não existe |
| `502 Bad Gateway` | Erro na API externa | PokeAPI indisponível ou erro |
| `500 Internal Server Error` | Erro interno | Erro inesperado no servidor |

### Exemplo de Resposta de Erro

```json
{
  "timestamp": "2025-10-27T15:30:00",
  "status": 404,
  "error": "Not Found",
  "message": "Pokémon 'pokemoninexistente' não encontrado na PokeAPI",
  "path": "/api/pokemon/cache/pokemoninexistente"
}
```

### Validações Implementadas

- ✅ IDs devem ser >= 1
- ✅ Paginação: `page` >= 0, `size` >= 1
- ✅ Tipo não pode ser vazio
- ✅ Nota máximo 1000 caracteres
- ✅ Timeout de 5 segundos na API externa

### Documentação Completa de Robustez

📄 Para informações técnicas detalhadas sobre tratamento de erros, exceções customizadas, logs estruturados e testes de validação, consulte: **[ROBUSTEZ.md](ROBUSTEZ.md)**

---

## 📝 Boas Práticas

- ✅ Cache Pokémon antes de realizar operações repetidas
- ✅ Use paginação para listas grandes
- ✅ Valide dados antes de enviar requisições
- ✅ Trate erros de conexão com a PokeAPI
- ✅ Monitore logs para debugging
- ✅ Use o Console H2 para inspeção de dados

---

## 🧪 Testes

Execute os testes unitários com:

```bash
# Windows
.\gradlew.bat test

# Linux/Mac
./gradlew test
```

Para relatório de cobertura (futura implementação com JaCoCo):

```bash
.\gradlew.bat test jacocoTestReport
```

---

## 🚀 Deploy

### Docker (Planejado)

```dockerfile
FROM openjdk:21-jdk-slim
COPY build/libs/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
```

**Build e execute:**

```bash
docker build -t pokemon-cache-api .
docker run -p 8080:8080 pokemon-cache-api
```

### Executar JAR diretamente

```bash
# Build
.\gradlew.bat build

# Execute
java -jar build/libs/pokemon-cache-api-1.0.0.jar
```

---

## 📄 Licença

Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.

---

## 👤 Contato

**Paulo Henrique Araujo**

- GitHub: [@PauloAraujoDEV25](https://github.com/PauloAraujoDEV25)
- Email: (adicione seu email)

---

## 🤝 Contribuindo

Contribuições são bem-vindas! Sinta-se à vontade para:

1. Fazer fork do projeto
2. Criar uma branch para sua feature (`git checkout -b feature/MinhaFeature`)
3. Commit suas mudanças (`git commit -m 'Adiciona MinhaFeature'`)
4. Push para a branch (`git push origin feature/MinhaFeature`)
5. Abrir um Pull Request

---

## 📌 Roadmap

- [ ] Autenticação JWT
- [ ] Swagger/OpenAPI documentação
- [ ] Cache Redis
- [ ] Busca avançada (nome, habilidades, stats)
- [ ] Export de dados (CSV, JSON)
- [ ] Sistema de tags personalizadas
- [ ] Comparação entre Pokémon
- [ ] Testes de integração
- [ ] CI/CD com GitHub Actions
- [ ] Deploy em cloud (Azure/AWS)

---

## 🔗 Links Úteis

| Recurso | URL |
|---------|-----|
| **API Base** | http://localhost:8080 |
| **Console H2** | http://localhost:8080/h2-console |
| **Health Check** | http://localhost:8080/actuator/health |
| **PokeAPI Docs** | https://pokeapi.co/docs/v2 |

---

<div align="center">

### ⭐ Desenvolvido com ☕ Java 21 e 💚 Spring Boot por Paulo Henrique Araujo

**Se este projeto foi útil, deixe uma ⭐ no repositório!**

[⬆ Voltar ao topo](#-pokémon-cache-api)

</div>
