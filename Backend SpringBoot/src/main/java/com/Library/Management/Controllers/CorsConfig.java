package com.Library.Management.Controllers;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Apply this configuration to ALL endpoints
                .allowedOrigins("http://localhost:4200") // 🔑 Crucial: Your Angular application's origin
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Allows the necessary HTTP methods, including OPTIONS for preflight
                .allowedHeaders("*") // Allows all headers from the client
                .allowCredentials(true); // Optional: if you are using cookies or sessions
    }
}