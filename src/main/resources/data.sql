-- Usuários de exemplo (as senhas são: 'password123')
-- Senha criptografada com BCrypt

-- Usuário comum
INSERT INTO users (username, password, email, role, enabled, created_at) VALUES 
('user', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'user@pokemon.com', 'ROLE_USER', true, CURRENT_TIMESTAMP);

-- Administrador
INSERT INTO users (username, password, email, role, enabled, created_at) VALUES 
('admin', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'admin@pokemon.com', 'ROLE_ADMIN', true, CURRENT_TIMESTAMP);
