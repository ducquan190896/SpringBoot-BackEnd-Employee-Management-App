package com.apimanagement.employeemanagement.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apimanagement.employeemanagement.Entity.Account;
import com.apimanagement.employeemanagement.Repository.AccountRepository;
import com.apimanagement.employeemanagement.Repository.RoleRepository;

@Service
public class AccountServiceImp implements AccountService, UserDetailsService {

    @Autowired 
    AccountRepository accountRepository;
    @Autowired
    RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Account> entity = accountRepository.findByUsername(username);
        Account account = checkAccount(entity, 404L);
        List<SimpleGrantedAuthority> authorities = account.getRoles().stream().map(auth -> new SimpleGrantedAuthority(auth.getName().getRoleType())).collect(Collectors.toList());
        User user  = new User(username, account.getPassword(), authorities); 
        return user;
       
    }

    @Override
    public void deleteAccount(String username) {
        Optional<Account> entity = accountRepository.findByUsername(username);
         Account account = checkAccount(entity, 404L);
         accountRepository.delete(account);
        
    }

    @Override
    public Account getAccount(String username) {
       Optional<Account> entity = accountRepository.findByUsername(username);
       return checkAccount(entity, 404L);
       
    }

    @Override
    public List<Account> getAccounts() {
       
        return accountRepository.findAll();
    }

    @Transactional
    @Override
    public void saveAccount(Account account) {
        Optional<Account> entity = accountRepository.findByUsername(account.getUsername());
        if(entity.isPresent()) {
            throw new EntityNotFoundException("the account with " + account.getUsername()  + " already exists");
        }
        account.setPassword(new BCryptPasswordEncoder().encode(account.getPassword()));
        account.getRoles().add(roleRepository.findByName("ROLE_USER").get());
        accountRepository.save(account);
        
    }

    @Override
    public Account updatePassword(Long id, String password) {
       
        Optional<Account> entity = accountRepository.findById(id);
        Account a = checkAccount(entity, id);
         a.setPassword(new BCryptPasswordEncoder().encode(password));
        return accountRepository.save(a);
       
    }

    private Account checkAccount(Optional<Account> entity, Long id) {
        if(entity.isPresent()) {
            return entity.get();
        } else {
        throw new EntityNotFoundException("the account with " + id + " not exists");
        }
    }
    
}
