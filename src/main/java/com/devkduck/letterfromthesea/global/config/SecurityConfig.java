package com.devkduck.letterfromthesea.global.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests(authorizeHttpRequestsCustomizer -> authorizeHttpRequestsCustomizer
                        .requestMatchers("/login/page").permitAll()
                        .requestMatchers("/images/**").permitAll()
                        .requestMatchers("/callback").permitAll()
                        .requestMatchers("/index.html").permitAll()


//                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .anyRequest()
                        .authenticated()
                ).formLogin(formLoginCustomizer -> formLoginCustomizer
                        .loginPage("/login/page")
                        .defaultSuccessUrl("/")
//                        .usernameParameter("email")
//                        .failureUrl("/members/login/error")
//                        .failureHandler(new CustomAuthenticationFailureHandler())
                ).logout( logoutCustomizer -> logoutCustomizer
//                        .logoutRequestMatcher(new AntPathRequestMatcher("/members/logout"))
                        .logoutSuccessUrl("/")
                )
                .csrf(AbstractHttpConfigurer::disable)
                .build()
                ;
    }
//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder(); //해시 함수를 이용하여 비밀번호를 암호화 저장
//    }
}
