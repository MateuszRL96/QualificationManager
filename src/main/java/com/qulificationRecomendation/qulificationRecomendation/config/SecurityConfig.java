package com.qulificationRecomendation.qulificationRecomendation.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private AuthenticationSuccessHandler customAuthenticationSuccessHandler;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .cors().and().csrf().disable()
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/", "/login", "/css/**").permitAll();
                    auth.requestMatchers("/favicon.ico").permitAll();
                    auth.requestMatchers("/error").permitAll();
                    auth.requestMatchers("/api/user-qualifications/**").permitAll();
                    auth.requestMatchers("/api/user-qualifications/delete/**").permitAll();
                    auth.requestMatchers("/api/user-details/**").permitAll(); // Allow access to /api/user-details endpoints
                    auth.requestMatchers("/api/users/create-profile").authenticated();
                    auth.requestMatchers("/api/auth/login-count").permitAll();
                    auth.requestMatchers("/api/employer-details/all").permitAll();
                    auth.requestMatchers("/recommendations/**").permitAll();
                    auth.requestMatchers("/data-preparation/**").permitAll();
                    auth.requestMatchers("/qualifications/search").permitAll();
                    auth.requestMatchers("/qualifications/**").permitAll();
                    auth.requestMatchers("/qualifications/profession/**").permitAll();
                    auth.requestMatchers("/api/udemy-category/**").permitAll();
                    auth.requestMatchers("/api/udemy-category-data/**").permitAll();
                    auth.anyRequest().authenticated();
                })
                .formLogin(form -> form
                        .loginPage("/login")
                        .permitAll()
                )
                .oauth2Login(oauth2 -> oauth2
                        .successHandler(customAuthenticationSuccessHandler)
                )
                .build();
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("http://localhost:4200");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}