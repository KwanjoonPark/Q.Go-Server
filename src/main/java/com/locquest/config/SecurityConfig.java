package com.locquest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        .requestMatchers("/", "/health").permitAll()          // 헬스체크 허용
                        .requestMatchers("/auth/**").permitAll()             // 로그인/회원가입 허용
                        .requestMatchers(HttpMethod.GET, "/game/**").permitAll()   // 게임 GET API 허용
                        .requestMatchers(HttpMethod.POST, "/game/**").permitAll()  // 게임 POST API 허용
                        .requestMatchers("/location/**").permitAll()         // 위치 API 허용
                        .requestMatchers("/ranking/**").permitAll()          // 랭킹 API 허용
                        .requestMatchers("/h2-console/**").permitAll()       // H2 콘솔 허용
                        .anyRequest().authenticated()                        // 나머지는 인증 필요
                )
                .headers(headers -> headers.frameOptions().disable()); // H2 콘솔용

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOriginPatterns(List.of("*"));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);
        
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
