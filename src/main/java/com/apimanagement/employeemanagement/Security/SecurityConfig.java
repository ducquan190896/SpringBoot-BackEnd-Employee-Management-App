package com.apimanagement.employeemanagement.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import com.apimanagement.employeemanagement.Entity.RoleType;
import com.apimanagement.employeemanagement.Security.Filter.AuthenticationFilter;
import com.apimanagement.employeemanagement.Security.Filter.ExceptionFilter;
import com.apimanagement.employeemanagement.Security.Filter.JwtAuthorizationFilter;

import lombok.AllArgsConstructor;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

  @Autowired
  CustomAuthenticationManager customAuthenticationManager;

  @Autowired
  ExceptionFilter exceptionFilter;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        AuthenticationFilter authenticationFilter = new AuthenticationFilter(customAuthenticationManager);
        authenticationFilter.setFilterProcessesUrl("/login");
        
        http
        .csrf().disable()
        .authorizeRequests()
        .antMatchers("/api/v1/employees/**").hasRole("ROLE")
        .anyRequest().authenticated()
        .and()
        .addFilterBefore(exceptionFilter, AuthenticationFilter.class)
        .addFilter(authenticationFilter)
        .addFilterAfter(new JwtAuthorizationFilter(), authenticationFilter.getClass())
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        return http.build();
    }
}
