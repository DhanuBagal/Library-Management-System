package com.Library.Management.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService(PasswordEncoder passwordEncoder) {
        UserDetails admin = User.builder()
                .username("admin")
                // encrypted password
                .password(passwordEncoder.encode("password123")) 
                .roles("ADMIN").build();

        return new InMemoryUserDetailsManager(admin);
    }
    
    // 🔑 1. Define the CORS configuration source bean
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        
        // **Crucial Settings for your Angular App**
        config.setAllowCredentials(true);
        config.addAllowedOrigin("http://localhost:4200"); // 🔑 Your Angular Origin
        config.addAllowedHeader("*"); // Allow all headers
        config.addAllowedMethod("*"); // Allow all methods (GET, POST, OPTIONS, etc.)
        
        source.registerCorsConfiguration("/**", config); // Apply to all paths
        return source;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // 🔑 2. Enable CORS configuration in the filter chain
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))
            
            // Disable CSRF for simpler API development
            .csrf(csrf -> csrf.disable())
            
            .authorizeHttpRequests(auth -> auth
                // Crucial: Permit all OPTIONS requests (CORS preflight)
                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll() 
                
                // **UPDATED: Permit all methods (GET, POST, PUT, DELETE) under /books/** for easy testing.**
                .requestMatchers("/books/**").permitAll() 

                // Keep existing public endpoints
                .requestMatchers("/users/login", "/users/register").permitAll() 
                
                // Everything else requires login
                .anyRequest().authenticated() 
            )
            .httpBasic();

        return http.build();
    }
}