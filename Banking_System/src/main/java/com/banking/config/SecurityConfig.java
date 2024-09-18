package com.banking.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.banking.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
	
	@Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .authorizeHttpRequests(authorizeRequests ->
                authorizeRequests
                    .requestMatchers("/api/auth/**").permitAll() // Public access to auth endpoints
                    .requestMatchers("/api/bank/**").authenticated() // Requires authentication for bank-related endpoints
                    .requestMatchers("/api/customer/**").authenticated() // Requires authentication for customer-related endpoints
                    .requestMatchers("/api/account/**").authenticated() // Requires authentication for account-related endpoints
                    .requestMatchers("/api/transaction/**").authenticated() // Requires authentication for transaction-related endpoints
                    .anyRequest().authenticated() // Require authentication for any other requests
            )
            .exceptionHandling(exceptionHandling ->
                exceptionHandling
                    .authenticationEntryPoint(unauthorizedHandler) // Handle unauthorized access
            )
            .sessionManagement(sessionManagement ->
                sessionManagement
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Stateless sessions
            )
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class); // Add JWT filter before UsernamePasswordAuthenticationFilter
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager  authenticationManager(HttpSecurity http) throws Exception {
    	AuthenticationConfiguration authenticationConfiguration = http.getSharedObject(AuthenticationConfiguration.class);
        return authenticationConfiguration.getAuthenticationManager();
    }
}