package com.apimanagement.employeemanagement.Service;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import com.apimanagement.employeemanagement.Entity.Account;

public interface AccountService {
    //assume that username is unique, dont need to look for id of username
    public Account getAccount(String username);
    public List<Account> getAccounts();
    @Transactional
    public void saveAccount(Account account);
    public Account updatePassword(Long id, String password);

    @Modifying
    @Transactional
    public void deleteAccount(String username);
}
