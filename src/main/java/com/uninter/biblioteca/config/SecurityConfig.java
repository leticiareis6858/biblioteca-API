package com.uninter.biblioteca.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

// classe de configuração do Spring Security
@Configuration
public class SecurityConfig {

    // método que configura o filtro de segurança para que não precise de
    // autenticação para acessar as rotas
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests(authorizeRequests -> authorizeRequests.anyRequest().permitAll()).csrf(csrf -> csrf.disable());
        return http.build();
    }

    // método que configura o password encoder, que é utilizado para criptografar as senhas
    // ao salvá-las no banco de dados
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}