package com.example.FP.config;

import com.example.FP.handler.CustomAuthenticationFailureHandler;
import com.example.FP.handler.CustomAuthenticationSuccessHandler;
import com.example.FP.service.CustomOAuth2Service;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig{

    private final CustomOAuth2Service customOAuth2Service;
    private final CustomAuthenticationSuccessHandler authenticationSuccessHandler;
    private final CustomAuthenticationFailureHandler authenticationFailureHandler;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()

                .requestMatchers("/", "/all/**","/listIngredient/**", "/join","/joinOk","/static/**","/id_check","/sendEmail","/nickname_check","/mailCheck","/error"
                , "/listRecipe/**", "/detailRecipe", "/detailIngredient", "/notice", "/oftenQuestions", "/oftenQuestionDetail/**", "/noticeDetail/**", "/eventDetail/**", "/eventList")
                .permitAll()
                .requestMatchers("/admin/**").hasAuthority("ADMIN")
                .anyRequest().authenticated()


                .and()
                .formLogin().loginPage("/login")
                .successHandler(authenticationSuccessHandler)   // 로그인 성공시
                .failureHandler(authenticationFailureHandler)   // 로그인 실패시
                .permitAll()
                
                .and()
                .oauth2Login()  // oauth2를 이용한 로그인
                .loginPage("/login")
                .successHandler(authenticationSuccessHandler)   // 로그인 성공시
                .failureHandler(authenticationFailureHandler)   // 로그인 실패시
                .userInfoEndpoint()
                .userService(customOAuth2Service);

        http.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .invalidateHttpSession(true)
                .logoutSuccessUrl("/login");

        http.httpBasic();


        return http.build();
    }
}