package com.apimanagement.employeemanagement.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.apimanagement.employeemanagement.Entity.Role;
import com.apimanagement.employeemanagement.Entity.RoleType;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    
    @Query(
        value = "select * from role where name = ?1",
        nativeQuery = true
    )
    Optional<Role> findByName(String name);
}
