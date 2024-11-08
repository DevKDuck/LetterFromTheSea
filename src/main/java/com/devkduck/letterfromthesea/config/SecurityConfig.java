package com.devkduck.letterfromthesea.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    BCryptPasswordEncoder encodedPwd() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(
                        authorizeRequestCustomizer -> authorizeRequestCustomizer
                                .requestMatchers("/user/**").authenticated()
                                .anyRequest().permitAll()
                )
                .formLogin(
                        formLoginCustomizer ->
                                formLoginCustomizer
                                        .loginPage("/loginForm")
                                        .loginProcessingUrl("/login") // login 호출시 시큐리티가 낚아채서 대신 로그인
                                        .defaultSuccessUrl("/")
                )
                .csrf(AbstractHttpConfigurer::disable)
                .build();
    }
}
