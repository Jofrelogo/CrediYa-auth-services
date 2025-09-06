package com.crediya.auth.config;

import com.crediya.auth.r2dbc.security.JwtAuthenticationManager;
import com.crediya.auth.r2dbc.security.JwtSecurityContextRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;


@Configuration
@EnableWebFluxSecurity
public class SecurityWebFluxConfig {

    private final JwtAuthenticationManager jwtAuthenticationManager;
    private final JwtSecurityContextRepository securityContextRepository;

    public SecurityWebFluxConfig(JwtAuthenticationManager jwtAuthenticationManager,
                                 JwtSecurityContextRepository securityContextRepository) {
        this.jwtAuthenticationManager = jwtAuthenticationManager;
        this.securityContextRepository = securityContextRepository;
    }

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        return http
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .httpBasic(ServerHttpSecurity.HttpBasicSpec::disable)
                .formLogin(ServerHttpSecurity.FormLoginSpec::disable)
                .authenticationManager( jwtAuthenticationManager)
                .securityContextRepository( securityContextRepository)
                .authorizeExchange(exchange -> exchange
                        .pathMatchers("/api/v1/login",
                                "/swagger-ui.html",
                                "/swagger-ui/**",
                                "/v3/api-docs/**",
                                "/webjars/**").permitAll()
                        .pathMatchers("/api/v1/users/**").hasAnyRole("ADMIN", "ASESOR")
                        .pathMatchers("/api/v1/apply/**").hasRole("CLIENT")
                        .anyExchange().authenticated()
                )
                .build();
    }
}

