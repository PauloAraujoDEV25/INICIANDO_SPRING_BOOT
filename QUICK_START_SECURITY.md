# 🎯 Guia Rápido - Spring Security Implementado

## ✅ O que foi implementado?

### 1. Dependências Adicionadas
- ✅ `spring-boot-starter-security` no `build.gradle`

### 2. Entidades e Repositórios
- ✅ `User.java` - Entidade de usuário com roles
- ✅ `UserRepository.java` - Repository para gerenciar usuários

### 3. DTOs
- ✅ `RegisterRequest.java` - DTO para registro
- ✅ `LoginRequest.java` - DTO para login
- ✅ `AuthResponse.java` - DTO de resposta

### 4. Services
- ✅ `CustomUserDetailsService.java` - Service do Spring Security
- ✅ `AuthService.java` - Lógica de autenticação

### 5. Controllers
- ✅ `AuthController.java` - Endpoints de autenticação

### 6. Configurações
- ✅ `SecurityConfig.java` - Configuração de segurança
- ✅ `application.properties` - Propriedades atualizadas
- ✅ `data.sql` - Usuários padrão

---

## 🚀 Como Executar

### 1. Compilar o Projeto

```powershell
cd "c:\development\java\web_spring\iniciando_spring_boot\INICIANDO_SPRING_BOOT"
.\gradlew.bat clean build
```

### 2. Executar a Aplicação

```powershell
.\gradlew.bat bootRun
```

### 3. Aguardar a aplicação iniciar

Você verá no console:
```
Started PokemonCacheApiApplication in X.XXX seconds
```

---

## 🧪 Testes Rápidos

### Teste 1: Registrar Novo Usuário

```powershell
$registerBody = @{
    username = "testuser"
    email = "test@pokemon.com"
    password = "test123"
} | ConvertTo-Json

Invoke-RestMethod -Method POST -Uri "http://localhost:8080/api/auth/register" `
    -Body $registerBody -ContentType "application/json"
```

**Resposta esperada:**
```json
{
  "message": "Usuário registrado com sucesso",
  "username": "testuser",
  "role": "ROLE_USER"
}
```

---

### Teste 2: Login

```powershell
$loginBody = @{
    username = "user"
    password = "password123"
} | ConvertTo-Json

$session = New-Object Microsoft.PowerShell.Commands.WebRequestSession
$response = Invoke-RestMethod -Method POST -Uri "http://localhost:8080/api/auth/login" `
    -Body $loginBody -ContentType "application/json" -WebSession $session

Write-Host "Login realizado: $($response.username) - $($response.role)"
```

---

### Teste 3: Acessar URL Pública (sem login)

```powershell
# Deve funcionar sem autenticação
Invoke-RestMethod -Uri "http://localhost:8080/api/pokemon"
```

---

### Teste 4: Acessar URL Privada (sem login)

```powershell
# Deve retornar erro 401
try {
    Invoke-RestMethod -Method POST -Uri "http://localhost:8080/api/pokemon/cache/pikachu"
} catch {
    Write-Host "✅ Bloqueado corretamente: Autenticação necessária"
}
```

---

### Teste 5: Acessar URL Privada (com login)

```powershell
# Login primeiro
$loginBody = @{username = "user"; password = "password123"} | ConvertTo-Json
$session = New-Object Microsoft.PowerShell.Commands.WebRequestSession
Invoke-RestMethod -Method POST -Uri "http://localhost:8080/api/auth/login" `
    -Body $loginBody -ContentType "application/json" -WebSession $session

# Agora pode cachear
$result = Invoke-RestMethod -Method POST -Uri "http://localhost:8080/api/pokemon/cache/pikachu" `
    -WebSession $session

Write-Host "✅ Pokémon cacheado: $($result.name)"
```

---

### Teste 6: Ver Usuário Autenticado

```powershell
# Após fazer login (usando a mesma $session)
$me = Invoke-RestMethod -Uri "http://localhost:8080/api/auth/me" -WebSession $session
Write-Host "Usuário: $($me.username)"
Write-Host "Role: $($me.role)"
```

---

## 📊 URLs Configuradas

### URLs Públicas (✅ Acesso sem login)
- `POST /api/auth/register`
- `POST /api/auth/login`
- `GET /api/pokemon`
- `GET /api/pokemon/{id}`
- `GET /api/pokemon/search?type=fire`
- `GET /actuator/health`
- `GET /h2-console/**`

### URLs Privadas (🔒 Requer login)
- `POST /api/pokemon/cache/{nameOrId}` - Requer ROLE_USER ou ROLE_ADMIN
- `PATCH /api/pokemon/{id}/favorite` - Requer ROLE_USER ou ROLE_ADMIN
- `GET /api/auth/me` - Requer autenticação
- `DELETE /api/pokemon/{id}` - Requer ROLE_ADMIN

---

## 👥 Usuários Padrão

### Usuário Comum
```
Username: user
Password: password123
Role: ROLE_USER
```

### Administrador
```
Username: admin
Password: password123
Role: ROLE_ADMIN
```

---

## 🔍 Verificar Usuários no Banco H2

1. Acesse: http://localhost:8080/h2-console
2. JDBC URL: `jdbc:h2:mem:pokemondb`
3. User: `sa`
4. Password: (vazio)
5. Execute:

```sql
SELECT * FROM USERS;
```

---

## 📁 Arquivos Criados

```
src/main/java/br/com/pokemon/
├── config/
│   └── SecurityConfig.java (NOVO)
├── controller/
│   └── AuthController.java (NOVO)
├── dto/
│   ├── RegisterRequest.java (NOVO)
│   ├── LoginRequest.java (NOVO)
│   └── AuthResponse.java (NOVO)
├── model/
│   ├── entity/
│   │   └── User.java (NOVO)
│   └── repository/
│       └── UserRepository.java (NOVO)
└── service/
    ├── CustomUserDetailsService.java (NOVO)
    └── AuthService.java (NOVO)

src/main/resources/
├── application.properties (ATUALIZADO)
└── data.sql (NOVO)

docs/
└── SPRING_SECURITY.md (NOVO)
```

---

## 🎯 Fluxo Completo de Teste

```powershell
# 1. Registrar
$registerBody = @{username="ash"; email="ash@pokemon.com"; password="pikachu123"} | ConvertTo-Json
Invoke-RestMethod -Method POST -Uri "http://localhost:8080/api/auth/register" `
    -Body $registerBody -ContentType "application/json"

# 2. Login
$loginBody = @{username="ash"; password="pikachu123"} | ConvertTo-Json
$session = New-Object Microsoft.PowerShell.Commands.WebRequestSession
Invoke-RestMethod -Method POST -Uri "http://localhost:8080/api/auth/login" `
    -Body $loginBody -ContentType "application/json" -WebSession $session

# 3. Cachear Pokémon
Invoke-RestMethod -Method POST -Uri "http://localhost:8080/api/pokemon/cache/pikachu" `
    -WebSession $session

# 4. Listar Pokémon (público)
Invoke-RestMethod -Uri "http://localhost:8080/api/pokemon"

# 5. Favoritar
$favBody = @{favorite=$true; note="Meu starter!"} | ConvertTo-Json
Invoke-RestMethod -Method PATCH -Uri "http://localhost:8080/api/pokemon/1/favorite" `
    -Body $favBody -ContentType "application/json" -WebSession $session

# 6. Ver usuário
Invoke-RestMethod -Uri "http://localhost:8080/api/auth/me" -WebSession $session
```

---

## ⚠️ Possíveis Erros

### Erro: "Username já está em uso"
- **Causa:** Tentando registrar um username que já existe
- **Solução:** Use outro username

### Erro: "Bad credentials"
- **Causa:** Username ou senha incorretos no login
- **Solução:** Verifique as credenciais

### Erro: "401 Unauthorized"
- **Causa:** Tentando acessar URL privada sem autenticação
- **Solução:** Faça login primeiro e use a sessão

---

## 📚 Documentação Completa

Para mais detalhes, consulte:
- **[SPRING_SECURITY.md](SPRING_SECURITY.md)** - Documentação completa
- **[README.md](README.md)** - Documentação do projeto
- **[ROBUSTEZ.md](ROBUSTEZ.md)** - Tratamento de erros

---

<div align="center">

### ✅ Spring Security implementado e pronto para uso!

**Próximo passo:** Execute `.\gradlew.bat bootRun` e teste os endpoints!

</div>
