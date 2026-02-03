package com.example.notesapp.auth.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class ConfigurationHandler {

    private final PasswordEncoder passwordEncoder;
    private final JwtAuthFilter jwtAuthFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity){

//        httpSecurity.formLogin(Customizer.withDefaults());

        httpSecurity.csrf(csrfConfig->csrfConfig.disable())
                .sessionManagement(sessionConfig->
                        sessionConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                .authorizeHttpRequests(auth->auth

                .requestMatchers("/auth/**").permitAll()
                        .anyRequest().authenticated()
        )
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
//        .formLogin(Customizer.withDefaults());

        return httpSecurity.build();
    }



}
