# 📦 Estrutura de Pacotes - Projeto Pokemon Cache API

## 📂 Estrutura Organizada

```
src/main/java/br/com/pokemon/
│
├── 📱 PokemonCacheApiApplication.java   # Classe principal
│
├── 🔧 config/                            # Configurações gerais
│   └── WebClientConfig.java             # Configuração do WebClient
│
├── 🎮 controller/                        # Controllers REST
│   ├── PokemonController.java           # Endpoints de Pokémon
│   └── AuthController.java              # Endpoints de Autenticação
│
├── 📦 dto/                               # Data Transfer Objects
│   ├── PokeApiResponse.java             # DTO da PokeAPI
│   ├── PokemonResponse.java             # DTO resposta completa
│   ├── PokemonSummary.java              # DTO resumo
│   ├── FavoriteRequest.java             # DTO favorito
│   ├── ErrorResponse.java               # DTO erro
│   ├── RegisterRequest.java             # DTO registro
│   ├── LoginRequest.java                # DTO login
│   └── AuthResponse.java                # DTO resposta auth
│
├── ❌ exception/                         # Exceções customizadas
│   ├── GlobalExceptionHandler.java      # Handler global
│   ├── PokemonNotFoundException.java    # Exceção Pokémon
│   └── PokeApiException.java            # Exceção API
│
├── 📊 model/                             # Modelos de domínio
│   ├── entity/                          # Entidades JPA
│   │   ├── Pokemon.java                 # Entidade Pokémon
│   │   └── User.java                    # Entidade Usuário
│   └── repository/                      # Repositórios JPA
│       ├── PokemonRepository.java       # Repository Pokémon
│       └── UserRepository.java          # Repository Usuário
│
├── 🔐 security/                          # ⭐ NOVO PACOTE DE SEGURANÇA
│   ├── SecurityConfig.java              # Configuração Spring Security
│   └── CustomUserDetailsService.java   # Service de autenticação
│
└── 💼 service/                           # Serviços de negócio
    ├── PokemonService.java              # Lógica Pokémon
    ├── PokeApiService.java              # Integração PokeAPI
    └── AuthService.java                 # Lógica de autenticação
```

---

## 🎯 Pacote `security` - Detalhamento

### 📁 Por que criar um pacote `security` separado?

✅ **Organização:** Separa concerns de segurança do resto da aplicação  
✅ **Manutenibilidade:** Facilita encontrar e modificar configurações de segurança  
✅ **Clareza:** Deixa explícito o que é relacionado à segurança  
✅ **Padrão:** Segue as boas práticas do Spring Boot  
✅ **Escalabilidade:** Facilita adicionar novas features de segurança

### 📄 Arquivos no pacote `security`:

#### 1. `SecurityConfig.java`
**Responsabilidade:** Configuração principal do Spring Security

```java
package br.com.pokemon.security;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    // Configurações de:
    // - URLs públicas vs privadas
    // - PasswordEncoder (BCrypt)
    // - AuthenticationManager
    // - SessionManagement
    // - CSRF, CORS, Headers
}
```

**O que configura:**
- ✅ Regras de autorização (quem pode acessar o quê)
- ✅ Criptografia de senhas
- ✅ Gerenciamento de sessão
- ✅ Configurações de segurança HTTP

---

#### 2. `CustomUserDetailsService.java`
**Responsabilidade:** Carregar detalhes do usuário para autenticação

```java
package br.com.pokemon.security;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    // Busca usuário no banco
    // Converte para UserDetails do Spring Security
    // Usado pelo AuthenticationManager
}
```

**O que faz:**
- ✅ Implementa `UserDetailsService` do Spring Security
- ✅ Busca usuário no banco via `UserRepository`
- ✅ Converte entidade `User` para `UserDetails`
- ✅ Define roles e permissões

---

## 🔄 Diferença entre `service` e `security`

### 💼 Pacote `service/` (Lógica de Negócio)

```
service/
├── PokemonService.java      → Lógica de Pokémon
├── PokeApiService.java       → Integração PokeAPI
└── AuthService.java          → Lógica de registro/login
```

**Responsabilidades:**
- ✅ Regras de negócio da aplicação
- ✅ Validações de dados
- ✅ Operações CRUD
- ✅ Integrações externas
- ✅ Transações

---

### 🔐 Pacote `security/` (Infraestrutura de Segurança)

```
security/
├── SecurityConfig.java              → Configuração Spring Security
└── CustomUserDetailsService.java   → Autenticação Spring Security
```

**Responsabilidades:**
- ✅ Configuração de segurança
- ✅ Autenticação/Autorização (framework)
- ✅ Filtros de segurança
- ✅ Encoders de senha
- ✅ Gerenciamento de sessão

---

## 📊 Fluxo de Autenticação

```
┌─────────────────┐
│   Controller    │
│  AuthController │
└────────┬────────┘
         │ chama
         ▼
┌─────────────────┐
│    Service      │
│  AuthService    │ (Lógica de negócio)
└────────┬────────┘
         │ usa
         ▼
┌─────────────────────────────────────┐
│          Security Package           │
│                                     │
│  ┌──────────────────────────────┐  │
│  │  AuthenticationManager       │  │
│  └───────────┬──────────────────┘  │
│              │                      │
│              ▼                      │
│  ┌──────────────────────────────┐  │
│  │ CustomUserDetailsService     │  │
│  └───────────┬──────────────────┘  │
│              │                      │
│              ▼                      │
│  ┌──────────────────────────────┐  │
│  │      UserRepository          │  │
│  └──────────────────────────────┘  │
│                                     │
└─────────────────────────────────────┘
```

---

## ✅ Vantagens da Nova Estrutura

### 1. **Separação de Concerns**
```
❌ Antes: Tudo misturado em config/ e service/
✅ Agora: Segurança isolada em security/
```

### 2. **Facilita Manutenção**
```
❌ Antes: Procurar configs de segurança em vários lugares
✅ Agora: Tudo em br.com.pokemon.security
```

### 3. **Padrão da Comunidade**
```
Estrutura comum em projetos Spring:
- security/      → Configs de segurança
- config/        → Outras configurações
- service/       → Lógica de negócio
```

### 4. **Facilita Testes**
```
✅ Testar segurança isoladamente
✅ Mockar componentes de segurança
✅ Testes unitários mais claros
```

---

## 🎯 Quando usar cada pacote?

### Use `security/` quando for:
- ✅ Configuração do Spring Security
- ✅ Filtros de segurança
- ✅ Autenticação (framework level)
- ✅ Autorização (framework level)
- ✅ Encoders, Providers, Managers

### Use `service/` quando for:
- ✅ Lógica de negócio (registro, login)
- ✅ Validações de dados
- ✅ Regras de negócio
- ✅ Operações no banco
- ✅ Integrações externas

---

## 📚 Comparação com Outras Frameworks

### Spring Boot (Java)
```
✅ security/     → Configurações e autenticação
✅ service/      → Lógica de negócio
```

### .NET Core (C#)
```
✅ Security/     → Configurações e autenticação
✅ Services/     → Lógica de negócio
```

### NestJS (TypeScript)
```
✅ auth/         → Módulo de autenticação
✅ guards/       → Guards de autorização
✅ services/     → Lógica de negócio
```

---

## 🔗 Arquivos Relacionados

| Arquivo | Pacote | Responsabilidade |
|---------|--------|------------------|
| `SecurityConfig.java` | `security/` | Configuração Spring Security |
| `CustomUserDetailsService.java` | `security/` | Carregar usuários |
| `AuthService.java` | `service/` | Lógica de registro/login |
| `AuthController.java` | `controller/` | Endpoints REST |
| `User.java` | `model/entity/` | Entidade de usuário |
| `UserRepository.java` | `model/repository/` | Acesso ao banco |

---

## 🚀 Estrutura Final

```
br.com.pokemon/
├── config/          → WebClient, outras configs
├── controller/      → REST endpoints
├── dto/             → Data Transfer Objects
├── exception/       → Exceções e handlers
├── model/
│   ├── entity/      → Entidades JPA
│   └── repository/  → Repositories
├── security/        → ⭐ Spring Security configs
└── service/         → Lógica de negócio
```

---

<div align="center">

### ✅ Estrutura organizada seguindo padrões Spring Boot!

**Próxima etapa:** Compile o projeto com `.\gradlew.bat build`

</div>
