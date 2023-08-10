package com.platzi.pizza.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .csrf().disable()
                .cors().and()                   //Conector
                .authorizeHttpRequests()        //Autoriza las peticiones http
                .requestMatchers(HttpMethod.GET,"/api/**").permitAll()
                .requestMatchers(HttpMethod.PUT).denyAll()
                .anyRequest()                   //Para cualquier peticion
                .authenticated().and()          // Conector
                .httpBasic();                   // Deben tener autenticacion http Basic
        return http.build();
    }
}
