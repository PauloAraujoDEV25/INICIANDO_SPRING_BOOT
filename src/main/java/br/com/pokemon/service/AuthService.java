package br.com.pokemon.service;

import br.com.pokemon.dto.AuthResponse;
import br.com.pokemon.dto.LoginRequest;
import br.com.pokemon.dto.RegisterRequest;
import br.com.pokemon.model.entity.User;
import br.com.pokemon.model.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    
    @Transactional
    public AuthResponse register(RegisterRequest request) {
        log.info("Tentativa de registro para usuário: {}", request.getUsername());
        
        // Validar se username já existe
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new IllegalArgumentException("Username já está em uso");
        }
        
        // Validar se email já existe
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Email já está em uso");
        }
        
        // Criar novo usuário
        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role("ROLE_USER")
                .enabled(true)
                .build();
        
        userRepository.save(user);
        log.info("Usuário registrado com sucesso: {}", user.getUsername());
        
        return AuthResponse.builder()
                .message("Usuário registrado com sucesso")
                .username(user.getUsername())
                .role(user.getRole())
                .build();
    }
    
    public AuthResponse login(LoginRequest request) {
        log.info("Tentativa de login para usuário: {}", request.getUsername());
        
        // Autenticar usuário
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        
        SecurityContextHolder.getContext().setAuthentication(authentication);
        
        // Buscar detalhes do usuário
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
        
        log.info("Login realizado com sucesso: {}", user.getUsername());
        
        return AuthResponse.builder()
                .message("Login realizado com sucesso")
                .username(user.getUsername())
                .role(user.getRole())
                .build();
    }
}
