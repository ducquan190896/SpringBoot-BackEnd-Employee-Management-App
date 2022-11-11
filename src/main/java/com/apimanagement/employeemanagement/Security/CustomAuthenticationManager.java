package com.apimanagement.employeemanagement.Security;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.apimanagement.employeemanagement.Entity.Account;
import com.apimanagement.employeemanagement.Repository.AccountRepository;

@Component
public class CustomAuthenticationManager implements AuthenticationManager {

    @Autowired
    AccountRepository accountRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Optional<Account> entity = accountRepository.findByUsername(authentication.getName());
        if(!entity.isPresent()) {
            throw new EntityNotFoundException();
        }
        Account account = entity.get();
      boolean checkpassword =  bCryptPasswordEncoder.matches(authentication.getCredentials().toString(), account.getPassword());
      if(checkpassword == true) {
        List<SimpleGrantedAuthority> authorities = account.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName().getRoleType())).collect(Collectors.toList());
        return new UsernamePasswordAuthenticationToken(account.getUsername(), account.getPassword(), authorities);

      } else {
        throw new BadCredentialsException("you provided a wrong password");
      } 
      
    }
    
}
