package com.example.Jwt_Login.Singup.Api.Configuration;

import com.example.Jwt_Login.Singup.Api.JwtUtility.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests(authz -> authz
                 //    .requestMatchers(HttpMethod.POST,"/login","/signup").permitAll() // Use String paths directly
                    //    .requestMatchers(AntPathRequestMatcher.antMatcher("/login"), AntPathRequestMatcher.antMatcher("/signup")).permitAll()
                        .anyRequest().authenticated()

                );
                //.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}