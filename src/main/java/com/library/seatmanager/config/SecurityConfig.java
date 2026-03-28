package com.library.seatmanager.config;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
public class SecurityConfig {

//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//
//        http
//                .csrf(csrf -> csrf.disable())
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers(
//                                "/login.html",
//                                "/otp.html",
//
//                                // login & otp assets
//                                "/login.css",
//                                "/login.js",
//                                "/otp.css",
//                                "/otp.js",
//
//                                // shared assets
//                                "/header.html",
//                                "/header.js",
//
//                                // dashboard assets
//                                "/dashboard.css",
//                                "/dashboard.js",
//
//                                // profile assets (THIS WAS MISSING)
//                                "/profile.js",
//
//                                // misc
//                                "/favicon.ico",
//
//                                "/index.html",
//                                "/index.css",
//
//                                "/new-index.html",
//                                "/new-index.css",
//                                "/new-index.js",
//
//                                "/signup.html",
//                                "/signup.js",
//
//                                "/login.html",
//                                "/login.js",
//
//                                "/library.html",
//                                "/create-library.js",
//
//                                // auth APIs
//                                "/api/auth/**"
//                        ).permitAll()
//                        .anyRequest().authenticated()
//                )
//                .formLogin(form -> form.disable())
//                .logout(logout -> logout
//                        .logoutUrl("/api/auth/logout")
//                        .logoutSuccessUrl("/login.html")
//                );
//        return http.build();
//    }


// @Bean
// public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

//     http
//         .cors(cors -> {}) // ✅ ENABLE CORS
//         .csrf(csrf -> csrf.disable())
//         .authorizeHttpRequests(auth -> auth
//                 .requestMatchers("/api/auth/**").permitAll()
//                 .anyRequest().authenticated()
//         )
//         .formLogin(form -> form.disable());

//     return http.build();
//



//  @Bean
//     public SecurityFilterChain secuirtyFilterChain(HttpSecurity http) throws Exception {

// //		 because direct csrf is depricated that's why we are using (csrf-> csrf. type thing so these thing are saying as lamda)
//         http.csrf(csrf-> csrf.disable())
//                 .cors(cors-> cors.configurationSource(new CorsConfigurationSource() {

//                     @Override
//                     public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {

//                         CorsConfiguration cfg= new CorsConfiguration();
//                         cfg.setAllowedOrigins(Arrays.asList(
//                                 "https://seat-manger-frontend.vercel.app/",
//                                 "https://seattss.vercel.app/"

//                         ));
//                         cfg.setAllowedMethods(Collections.singletonList("*"));
//                         cfg.setAllowCredentials(true);
//                         cfg.setAllowedHeaders(Collections.singletonList("*"));
//                         cfg.setExposedHeaders(Arrays.asList("Authorization"));
//                         cfg.setMaxAge(3600L);
//                         return cfg;
//                     }
//                 }))
//                 .authorizeHttpRequests(auth->
//                          auth.requestMatchers("/api/auth/**").permitAll()
//                         .anyRequest()
//                         .authenticated())
//                 .sessionManagement(session-> session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED));
//         return http.build();
//     }




private final JwtFilter jwtFilter;

public SecurityConfig(JwtFilter jwtFilter) {
    this.jwtFilter = jwtFilter;
}

@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

    http
        .csrf(csrf -> csrf.disable())
        .cors(cors -> {})
        .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/api/auth/**").permitAll()
                    .anyRequest().authenticated()
        )
        .sessionManagement(session ->
                    session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        )
        .addFilterBefore(jwtFilter,
            UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }


@Bean
public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
}
}