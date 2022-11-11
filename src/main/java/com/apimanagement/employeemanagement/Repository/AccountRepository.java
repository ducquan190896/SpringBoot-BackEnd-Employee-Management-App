package com.apimanagement.employeemanagement.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.apimanagement.employeemanagement.Entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    
    @Query(
        value = "select * from account where username = ?1",
        nativeQuery = true
    )
    Optional<Account> findByUsername(String username);
}
