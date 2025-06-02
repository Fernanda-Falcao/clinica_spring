package com.br.spring_security.clinica.config;

import com.br.spring_security.clinica.service.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // Informa que essa classe contém configurações do Spring
@EnableWebSecurity // Ativa as configurações de segurança da aplicação
public class SecurityConfig {

    // Injeta o serviço que lida com autenticação e usuários
    private final UsuarioService service;

    public SecurityConfig(UsuarioService service) {
        this.service = service;
    }

    // Define a cadeia de filtros de segurança da aplicação
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors() // Habilita suporte a CORS (cross-origin)
                .and()
                .csrf().disable() // Desabilita proteção CSRF (útil para APIs REST)
                .authorizeHttpRequests(authz -> authz
                        // Rotas públicas que não precisam de autenticação
                        .requestMatchers("/", "/home", "/login", "/login-error", "/image/**", "/css/**", "/js/**").permitAll()
                        // Qualquer outra rota requer autenticação
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginProcessingUrl("/login") // Endpoint para envio do login
                        .successHandler((request, response, authentication) -> response.setStatus(200)) // Login bem-sucedido
                        .failureHandler((request, response, exception) -> response.sendError(401, "Credenciais inválidas")) // Login falhou
                )
                .logout(logout -> logout
                        .logoutUrl("/logout") // Endpoint para logout
                        .logoutSuccessHandler((request, response, authentication) -> response.setStatus(200)) // Logout com sucesso
                )
                .authenticationProvider(authenticationProvider()); // Define como o Spring valida os usuários

        return http.build();
    }

    // Configura o provedor de autenticação usando um serviço de usuários e um encoder
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(service); // Usa nosso service personalizado
        authProvider.setPasswordEncoder(passwordEncoder()); // Usa o BCrypt para codificar senhas
        return authProvider;
    }

    // Encoder de senhas padrão com hash seguro (BCrypt)
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Gerencia o processo de autenticação no Spring Security
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }
}