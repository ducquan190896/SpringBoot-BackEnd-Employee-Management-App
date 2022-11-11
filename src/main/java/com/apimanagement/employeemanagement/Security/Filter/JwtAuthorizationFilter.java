package com.apimanagement.employeemanagement.Security.Filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.apimanagement.employeemanagement.Security.SecurityConstant;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter{
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
       String header = request.getHeader("Authorization");
       if(header == null || !header.startsWith(SecurityConstant.Authorization)) {
        filterChain.doFilter(request, response);
       } else {
        String token = header.replace(SecurityConstant.Authorization, "");
        DecodedJWT decodedJwt = JWT.require(Algorithm.HMAC512(SecurityConstant.private_key)).build().verify(token);
        String username = decodedJwt.getSubject();
        String[] claims = decodedJwt.getClaim("authorities").asArray(String.class);
      
        System.out.println(username);
        System.out.println(Arrays.toString(claims));

          List<SimpleGrantedAuthority> authorities = Arrays.stream(claims).map(auth -> new SimpleGrantedAuthority(auth)).collect(Collectors.toList());
          Authentication authentication = new UsernamePasswordAuthenticationToken(username, null, authorities);

          SecurityContextHolder.getContext().setAuthentication(authentication);
          filterChain.doFilter(request, response);
       }
    }
}
