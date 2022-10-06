package com.unosquare.sailingapp.configuration;

import static com.unosquare.sailingapp.constant.AppConstants.ACTIVE;
import static com.unosquare.sailingapp.constant.AppConstants.ADMIN;
import com.unosquare.sailingapp.security.JwtRequestFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration {

        private final JwtRequestFilter jwtRequestFilter;

        @Bean
        public SecurityFilterChain filterChain(final HttpSecurity httpSecurity)throws Exception{
                httpSecurity.csrf().disable().authorizeRequests()
                        .antMatchers(HttpMethod.POST, "/app-users/create").permitAll()
                        .antMatchers(HttpMethod.POST, "/v1/login").permitAll()
                        .antMatchers(HttpMethod.GET, "/actuator/**").hasAnyAuthority(ADMIN, ACTIVE)
                        .antMatchers(HttpMethod.GET, "/swagger-ui/**").permitAll()
                        .antMatchers(HttpMethod.GET, "/v3/api-docs**").permitAll()
                        .antMatchers(HttpMethod.GET, "/v3/api-docs/**").permitAll()
                        .anyRequest().authenticated()
                        .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

                return httpSecurity.build();
        }

        @Bean
        public AuthenticationManager authenticationManager(final AuthenticationConfiguration authenticationConfiguration)
                throws Exception {
                return authenticationConfiguration.getAuthenticationManager();
        }

        @Bean
        public BCryptPasswordEncoder bCryptPasswordEncoder() {
                return new BCryptPasswordEncoder();
        }
}
