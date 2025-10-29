# 🔐 Spring Security - Guia de Autenticação

## 📋 Índice

- [Visão Geral](#visão-geral)
- [Configuração de Segurança](#configuração-de-segurança)
- [URLs Públicas vs Privadas](#urls-públicas-vs-privadas)
- [Usuários Padrão](#usuários-padrão)
- [Endpoints de Autenticação](#endpoints-de-autenticação)
- [Exemplos de Uso](#exemplos-de-uso)
- [Testando a Segurança](#testando-a-segurança)

---

## 🎯 Visão Geral

O projeto agora implementa **Spring Security** com autenticação baseada em usuário e senha. As principais funcionalidades incluem:

- ✅ Autenticação de usuário com username/password
- ✅ Criptografia de senha com BCrypt
- ✅ Controle de acesso baseado em roles (ROLE_USER, ROLE_ADMIN)
- ✅ URLs públicas e privadas configuradas
- ✅ Sessão HTTP para manter autenticação

---

## ⚙️ Configuração de Segurança

### Roles Disponíveis

- **ROLE_USER**: Usuário comum (pode cachear Pokémon e favoritar)
- **ROLE_ADMIN**: Administrador (pode fazer tudo, incluindo deletar)

### Criptografia

As senhas são criptografadas usando **BCrypt** com 10 rounds.

---

## 🔓🔒 URLs Públicas vs Privadas

### URLs Públicas (sem autenticação)

✅ **Autenticação:**
- `POST /api/auth/register` - Registrar novo usuário
- `POST /api/auth/login` - Fazer login

✅ **Consultas:**
- `GET /api/pokemon` - Listar Pokémon (paginado)
- `GET /api/pokemon/{id}` - Buscar por ID
- `GET /api/pokemon/search?type=fire` - Buscar por tipo

✅ **Utilitários:**
- `GET /actuator/health` - Health check
- `GET /h2-console/**` - Console H2

### URLs Privadas (requer autenticação)

🔒 **Requer ROLE_USER ou ROLE_ADMIN:**
- `POST /api/pokemon/cache/{nameOrId}` - Cachear Pokémon
- `PATCH /api/pokemon/{id}/favorite` - Favoritar Pokémon
- `GET /api/auth/me` - Ver usuário autenticado

🔒 **Requer ROLE_ADMIN:**
- `DELETE /api/pokemon/{id}` - Deletar Pokémon (futura implementação)

---

## 👥 Usuários Padrão

O sistema vem com 2 usuários pré-cadastrados:

### Usuário Comum

```
Username: user
Password: password123
Email: user@pokemon.com
Role: ROLE_USER
```

### Administrador

```
Username: admin
Password: password123
Email: admin@pokemon.com
Role: ROLE_ADMIN
```

---

## 🔌 Endpoints de Autenticação

### 1. Registrar Novo Usuário

**Request:**
```http
POST /api/auth/register
Content-Type: application/json
```

**Body:**
```json
{
  "username": "novousuario",
  "email": "novo@pokemon.com",
  "password": "senha123"
}
```

**Response (201):**
```json
{
  "message": "Usuário registrado com sucesso",
  "username": "novousuario",
  "role": "ROLE_USER"
}
```

---

### 2. Login

**Request:**
```http
POST /api/auth/login
Content-Type: application/json
```

**Body:**
```json
{
  "username": "user",
  "password": "password123"
}
```

**Response (200):**
```json
{
  "message": "Login realizado com sucesso",
  "username": "user",
  "role": "ROLE_USER"
}
```

---

### 3. Ver Usuário Autenticado

**Request:**
```http
GET /api/auth/me
```

**Response (200):**
```json
{
  "message": "Usuário autenticado",
  "username": "user",
  "role": "ROLE_USER"
}
```

---

## 💻 Exemplos de Uso

### Exemplo 1: Registrar e Fazer Login

```powershell
# 1. Registrar novo usuário
$registerBody = @{
    username = "pokemon_trainer"
    email = "trainer@pokemon.com"
    password = "pikachu123"
} | ConvertTo-Json

Invoke-RestMethod -Method POST -Uri "http://localhost:8080/api/auth/register" `
    -Body $registerBody -ContentType "application/json"

# 2. Fazer login (cria sessão)
$loginBody = @{
    username = "pokemon_trainer"
    password = "pikachu123"
} | ConvertTo-Json

$session = New-Object Microsoft.PowerShell.Commands.WebRequestSession
Invoke-RestMethod -Method POST -Uri "http://localhost:8080/api/auth/login" `
    -Body $loginBody -ContentType "application/json" -WebSession $session

# 3. Cachear Pokémon (usa a sessão autenticada)
Invoke-RestMethod -Method POST -Uri "http://localhost:8080/api/pokemon/cache/pikachu" `
    -WebSession $session
```

---

### Exemplo 2: Login com Usuário Padrão

```powershell
# Login com usuário padrão
$loginBody = @{
    username = "user"
    password = "password123"
} | ConvertTo-Json

$session = New-Object Microsoft.PowerShell.Commands.WebRequestSession
$loginResponse = Invoke-RestMethod -Method POST -Uri "http://localhost:8080/api/auth/login" `
    -Body $loginBody -ContentType "application/json" -WebSession $session

Write-Host "Login: $($loginResponse.message)"
Write-Host "Usuário: $($loginResponse.username)"
Write-Host "Role: $($loginResponse.role)"

# Verificar autenticação
$meResponse = Invoke-RestMethod -Uri "http://localhost:8080/api/auth/me" -WebSession $session
Write-Host "`nUsuário autenticado: $($meResponse.username)"
```

---

### Exemplo 3: Testar URL Pública vs Privada

```powershell
# URL Pública (funciona sem autenticação)
Invoke-RestMethod -Uri "http://localhost:8080/api/pokemon"

# URL Privada (retorna 401 sem autenticação)
try {
    Invoke-RestMethod -Method POST -Uri "http://localhost:8080/api/pokemon/cache/pikachu"
} catch {
    Write-Host "Erro: Autenticação necessária!"
}

# URL Privada (funciona com autenticação)
$loginBody = @{username = "user"; password = "password123"} | ConvertTo-Json
$session = New-Object Microsoft.PowerShell.Commands.WebRequestSession
Invoke-RestMethod -Method POST -Uri "http://localhost:8080/api/auth/login" `
    -Body $loginBody -ContentType "application/json" -WebSession $session

Invoke-RestMethod -Method POST -Uri "http://localhost:8080/api/pokemon/cache/pikachu" `
    -WebSession $session
```

---

### Exemplo 4: Usando cURL (Linux/Mac)

```bash
# Registrar
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "username": "ash",
    "email": "ash@pokemon.com",
    "password": "pikachu123"
  }'

# Login (salvar cookies)
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username": "ash", "password": "pikachu123"}' \
  -c cookies.txt

# Cachear Pokémon (usar cookies)
curl -X POST http://localhost:8080/api/pokemon/cache/charizard \
  -b cookies.txt

# Verificar usuário autenticado
curl http://localhost:8080/api/auth/me -b cookies.txt
```

---

## 🧪 Testando a Segurança

### Teste 1: Acesso Público

```powershell
# Deve funcionar sem login
Invoke-RestMethod -Uri "http://localhost:8080/api/pokemon"
Invoke-RestMethod -Uri "http://localhost:8080/api/pokemon/search?type=fire"
Invoke-RestMethod -Uri "http://localhost:8080/actuator/health"
```

### Teste 2: Acesso Privado sem Autenticação

```powershell
# Deve retornar 401 Unauthorized
try {
    Invoke-RestMethod -Method POST -Uri "http://localhost:8080/api/pokemon/cache/pikachu"
} catch {
    Write-Host "✅ Bloqueado corretamente: 401 Unauthorized"
}
```

### Teste 3: Acesso Privado com Autenticação

```powershell
# Login
$loginBody = @{username = "user"; password = "password123"} | ConvertTo-Json
$session = New-Object Microsoft.PowerShell.Commands.WebRequestSession
Invoke-RestMethod -Method POST -Uri "http://localhost:8080/api/auth/login" `
    -Body $loginBody -ContentType "application/json" -WebSession $session

# Deve funcionar
Invoke-RestMethod -Method POST -Uri "http://localhost:8080/api/pokemon/cache/pikachu" `
    -WebSession $session
Write-Host "✅ Acesso autorizado com sucesso"
```

### Teste 4: Logout

```powershell
# Fazer logout
Invoke-RestMethod -Method POST -Uri "http://localhost:8080/api/auth/logout" -WebSession $session

# Tentar acessar URL privada após logout (deve falhar)
try {
    Invoke-RestMethod -Method POST -Uri "http://localhost:8080/api/pokemon/cache/bulbasaur" `
        -WebSession $session
} catch {
    Write-Host "✅ Sessão encerrada corretamente"
}
```

---

## 🔍 Verificar Usuários no H2 Console

1. Acesse: **http://localhost:8080/h2-console**
2. Configure:
   - JDBC URL: `jdbc:h2:mem:pokemondb`
   - User: `sa`
   - Password: *(vazio)*
3. Execute:

```sql
-- Ver todos os usuários
SELECT * FROM USERS;

-- Ver apenas usuários ativos
SELECT username, email, role, enabled, created_at 
FROM USERS 
WHERE enabled = TRUE;

-- Contar usuários por role
SELECT role, COUNT(*) as total 
FROM USERS 
GROUP BY role;
```

---

## ⚠️ Tratamento de Erros de Autenticação

### 400 Bad Request
```json
{
  "timestamp": "2025-10-28T10:00:00",
  "status": 400,
  "error": "Bad Request",
  "message": "Username já está em uso",
  "path": "/api/auth/register"
}
```

### 401 Unauthorized
```json
{
  "timestamp": "2025-10-28T10:00:00",
  "status": 401,
  "error": "Unauthorized",
  "message": "Bad credentials",
  "path": "/api/auth/login"
}
```

### 403 Forbidden
```json
{
  "timestamp": "2025-10-28T10:00:00",
  "status": 403,
  "error": "Forbidden",
  "message": "Access Denied",
  "path": "/api/pokemon/1"
}
```

---

## 📝 Validações Implementadas

### RegisterRequest
- ✅ Username: 3-50 caracteres, obrigatório, único
- ✅ Email: formato válido, obrigatório, único
- ✅ Password: mínimo 6 caracteres, obrigatório

### LoginRequest
- ✅ Username: obrigatório
- ✅ Password: obrigatório

---

## 🔐 Segurança Implementada

1. **Criptografia de Senha**: BCrypt com 10 rounds
2. **Sessão HTTP**: SessionCreationPolicy.IF_REQUIRED
3. **CSRF Protection**: Desabilitado (para APIs REST)
4. **H2 Console**: Frame options desabilitadas
5. **Logout**: Limpa contexto de segurança
6. **Validação**: Bean Validation nos DTOs

---

## 🚀 Próximos Passos (Melhorias Futuras)

- [ ] Implementar JWT (JSON Web Tokens)
- [ ] Adicionar refresh tokens
- [ ] Rate limiting por usuário
- [ ] Sistema de permissões granulares
- [ ] Autenticação OAuth2 (Google, GitHub)
- [ ] 2FA (Two-Factor Authentication)
- [ ] Auditoria de acessos
- [ ] Password reset via email

---

## 🔗 Links Úteis

| Recurso | URL |
|---------|-----|
| **Registrar** | POST http://localhost:8080/api/auth/register |
| **Login** | POST http://localhost:8080/api/auth/login |
| **Usuário Atual** | GET http://localhost:8080/api/auth/me |
| **Logout** | POST http://localhost:8080/api/auth/logout |
| **H2 Console** | http://localhost:8080/h2-console |

---

<div align="center">

### 🔐 Spring Security implementado com sucesso!

**Documentação completa: [README.md](README.md)**

</div>
