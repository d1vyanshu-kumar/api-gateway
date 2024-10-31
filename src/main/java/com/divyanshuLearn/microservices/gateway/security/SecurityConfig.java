package com.divyanshuLearn.microservices.gateway.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

// this is the list of urls that are free to access without authentication
private final String[] freeResourceUrls = {"/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**",
        "/swagger-resources/**", "/api-docs/**", "/aggregate/**", "/actuator/prometheus"};

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//       return httpSecurity.authorizeHttpRequests(authorize ->
//                authorize
//                        .requestMatchers(freeResourceUrls)
//                        .permitAll()
//                        .anyRequest().authenticated()
//        ).oauth2ResourceServer(oauth2 ->
//                oauth2.jwt(Customizer.withDefaults())
//        ).build();
//    }
@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
    return httpSecurity
            .authorizeHttpRequests(authorize -> authorize
                    .requestMatchers(freeResourceUrls).permitAll() // Allow public access to these URLs
                    .anyRequest().authenticated()                 // Require authentication for all other requests
            )
            .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()))
            .build();
}

}
