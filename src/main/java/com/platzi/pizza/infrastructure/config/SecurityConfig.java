package com.platzi.pizza.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .csrf().disable()
                .cors().and()                   //Conector
                .authorizeHttpRequests()        //Autoriza las peticiones http
                .requestMatchers(HttpMethod.GET,"/api/pizza/**").permitAll()
                .requestMatchers(HttpMethod.PUT).denyAll()
                .anyRequest()                   //Para cualquier peticion
                .authenticated().and()          // Conector
                .httpBasic();                   // Deben tener autenticacion http Basic
        return http.build();
    }

    @Bean
    public UserDetailsService memoryUsers(){
        UserDetails admin = User
                .builder()
                .username("admin")
                .password(encoder().encode("admin"))
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(admin);
    }

    @Bean
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }
}
