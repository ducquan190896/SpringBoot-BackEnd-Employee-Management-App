package com.apimanagement.employeemanagement.Controller;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apimanagement.employeemanagement.Entity.Account;
import com.apimanagement.employeemanagement.Service.AccountService;


@RestController
@RequestMapping("api/v1/accounts/")
public class AccountController {

    @Autowired
    AccountService accountService;


    @GetMapping("/")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER')")
    public ResponseEntity<List<Account>> getAccounts() {
        return new ResponseEntity<>(accountService.getAccounts(), HttpStatus.OK);

    }
    @GetMapping("/{username}")
    public ResponseEntity<Account> getAccount(@PathVariable String username) {
        return new ResponseEntity<Account>(accountService.getAccount(username), HttpStatus.OK);
    }

   
    @PostMapping("/")
    @PermitAll
    public ResponseEntity<HttpStatus> saveAccount(@Valid @RequestBody Account account) {
        System.out.println(account);
        accountService.saveAccount(account);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Account> updatepassword(@PathVariable Long id, @RequestBody String password) {
        return new ResponseEntity<Account>(accountService.updatePassword(id, password), HttpStatus.OK);
    }
    @DeleteMapping("/{username}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER')")
    public ResponseEntity<Account> deleteAccount(@PathVariable String username) {
        accountService.deleteAccount(username);
        return new ResponseEntity<Account>( HttpStatus.NO_CONTENT);
    }
    
}
