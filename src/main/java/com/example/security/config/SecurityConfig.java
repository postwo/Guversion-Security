package com.example.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder encode() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.csrf().disable(); // 비활성화 postman에서 하든 어떤 서버에서 회원가입을 하든 상관을 안하겠다는 뜻이다
        http.authorizeRequests()
                .antMatchers( "/user/**").authenticated()//인증만 되면 들어갈수 있는 주소
                .antMatchers("/manager/**").access("hasRole('ROLE_ADMIN' or hasRole('ROLE_MANAGER'))")
                .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .loginPage("/loginForm")// login 주소가 호출이 되면 시큐리티가 낚아채서 대신 로그인
                .defaultSuccessUrl("/");

            
        return http.build();

    }
}