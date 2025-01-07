package com.project.agendaWeb.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) //Desabilitar CSRF
                .headers(headers -> headers
                        .frameOptions(frame -> frame.sameOrigin())
                ) // Permitir iframes para o console H2
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/h2-console/**").permitAll() // Permite acesso ao console H2 sem autenticação
                        .anyRequest().permitAll() //// Permite todas as requisições sem autenticação
                )
                .logout(logout -> logout.disable()); //Desativa a funcionalidade de logout
        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
