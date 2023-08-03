package com.platzi.pizza.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .csrf().disable()
                .authorizeHttpRequests() //Autoriza las peticiones http
                .anyRequest()            //Para cualquier peticion
                //.permitAll();          //Permitir todas las peticiones
                .authenticated()        // Todas las peticiones autenticadas
                .and()                  // Conector
                .httpBasic();           // Deben tener autenticacion http Basic
        return http.build();
    }
}
