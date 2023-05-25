package com.ssafy.enjoytrip.config;

import com.ssafy.enjoytrip.util.jwt.JwtAccessDeniedHandler;
import com.ssafy.enjoytrip.util.jwt.JwtAuthenticationEntryPoint;
import com.ssafy.enjoytrip.util.jwt.JwtSecurityConfig;
import com.ssafy.enjoytrip.util.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final TokenProvider tokenProvider;
    //    401 에러를 처리하는 인터페이스
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    //    403 에러를 처리하는 인터페이스
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring()
                .antMatchers("/img/**")
                .antMatchers("/upload/**")
                .antMatchers("/profile/**")
                .antMatchers("/authenticate")
                .antMatchers("/user/api/join")
                .antMatchers("/place/api/sort");
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .cors()
                .and()

                .csrf().disable()
                //401, 403 Exception 핸들링
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler)

                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .authorizeRequests()
                .anyRequest().authenticated()

                .and()
                .apply(new JwtSecurityConfig(tokenProvider))

                .and().build();
    }

}
