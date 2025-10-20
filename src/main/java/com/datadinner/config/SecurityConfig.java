package com.datadinner.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer; // Necesario para la sintaxis moderna de .cors()
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
// Importaciones necesarias para CORS
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import java.util.Arrays; // Necesario para Arrays.asList

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Deshabilitar CSRF
            // 1. Integrar CORS en la cadena de filtros de seguridad
            .cors(Customizer.withDefaults()) 
            .authorizeHttpRequests(auth -> auth
                // 2. Permitir cualquier petición por ahora (para que Angular pueda conectar)
                // Si quieres que solo el login sea público, cambia .anyRequest().permitAll()
                // o asegúrate de tener un endpoint /login
                .anyRequest().permitAll() // Permitimos todo para que Angular pueda probar la conexión
            )
            .httpBasic(Customizer.withDefaults()); // Usar autenticación básica
            
        return http.build();
    }
    
    // 3. Bean que define la configuración de CORS
    // Spring Security utilizará este Bean para responder al navegador de Angular
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200")); // <--- Origen de Angular
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true); 

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration); // Aplica a todas las rutas
        return source;
    }

    @Bean
    public UserDetailsService users() {
        UserDetails admin = User.builder()
                .username("admin")
                .password("{noop}1234") 
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(admin);
    }
}