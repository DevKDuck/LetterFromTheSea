package com.devkduck.letterfromthesea.global.config;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomOAuth2UserService customOAuth2UserService;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests(authorizeHttpRequestsCustomizer -> authorizeHttpRequestsCustomizer
                        .requestMatchers("/login/page").permitAll()
                        .requestMatchers("/images/**").permitAll()
                        .requestMatchers("/callback").permitAll()
                        .requestMatchers("/index.html").permitAll()
                        .requestMatchers("/js/**").permitAll()
                        .anyRequest()
                        .authenticated()
                )
//                .formLogin(
//                        formLoginCustomizer -> formLoginCustomizer
//                        .loginPage("/login/page")
//                        .defaultSuccessUrl("/")
//                )
                .oauth2Login(oauth2Login -> oauth2Login
                        .loginPage("/login/page")
                        .userInfoEndpoint(userInfo -> userInfo.userService(customOAuth2UserService)
                        )
                        .defaultSuccessUrl("/")
                )
                .logout( logoutCustomizer -> logoutCustomizer
                        .logoutSuccessUrl("/")
                )
                .csrf(AbstractHttpConfigurer::disable)
                .build()
                ;
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(); //해시 함수를 이용하여 비밀번호를 암호화 저장
    }
}


//    private final CustomOAuth2UserService customOAuth2UserService;
//    private final OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;
//    private final OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler;
//    private final HttpCookieOAuth2AuthorizationRequestRepository httpCookieOAuth2AuthorizationRequestRepository;

//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//
//        http.csrf(AbstractHttpConfigurer::disable)
//                .headers(headersConfigurer -> headersConfigurer.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable)) // For H2 DB
//                .formLogin(AbstractHttpConfigurer::disable)
//                .httpBasic(AbstractHttpConfigurer::disable)
//                .authorizeHttpRequests((requests) -> requests
//                        .requestMatchers(antMatcher("/api/admin/**")).hasRole("ADMIN")
//                        .requestMatchers(antMatcher("/api/user/**")).hasRole("USER")
//                        .requestMatchers(antMatcher("/h2-console/**")).permitAll()
//                        .anyRequest().authenticated()
//                )
//                .sessionManagement(sessions -> sessions.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .oauth2Login(configure ->
//                        configure.authorizationEndpoint(config -> config.authorizationRequestRepository(httpCookieOAuth2AuthorizationRequestRepository))
//                                .userInfoEndpoint(config -> config.userService(customOAuth2UserService))
//                                .successHandler(oAuth2AuthenticationSuccessHandler)
//                                .failureHandler(oAuth2AuthenticationFailureHandler)
//                );
//
//        return http.build();
//    }
