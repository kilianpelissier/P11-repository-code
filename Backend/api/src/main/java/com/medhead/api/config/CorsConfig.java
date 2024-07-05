package com.medhead.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // Autoriser les requêtes CORS pour tous les chemins
                        .allowedOrigins("http://localhost:4200") // Autoriser les requêtes CORS depuis le domaine spécifié
                        .allowedMethods("GET", "POST", "PUT", "DELETE") // Méthodes HTTP autorisées
                        .allowedHeaders("*") // En-têtes autorisés
                        .allowCredentials(true); // Autoriser les cookies
            }
        };
    }
}