//package com.bibimbap.DailyPlanServer.security.config;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//
//@EnableWebSecurity
//@Configuration
//@RequiredArgsConstructor
//public class SecurityConfig {
//
//    private final CustomOAuth2UserService customOAuth2UserService;
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()
//                .headers().frameOptions().disable()
//                .and()
//                .authorizeHttpRequests()
//                .requestMatchers("/","/css/**","/images/**","/js/**","/h2-console/**").permitAll()
//                //.requestMatchers("/api/v1/**").hasRole(Role.USER.name())
//                .anyRequest().authenticated()
//                .and()
//                .logout()
//                .logoutSuccessUrl("/")
//                .and()
//                .oauth2Login(
////                        oauth2 -> oauth2
////                        .loginPage("/login/oauth2")
////                        .authorizationEndpoint(authorization -> authorization
////                                .baseUri("/login/oauth2/authorization"))
//                )
//                .defaultSuccessUrl("/")
//                .userInfoEndpoint()
//                .userService(customOAuth2UserService);
//        return http.build();
//    }
//}