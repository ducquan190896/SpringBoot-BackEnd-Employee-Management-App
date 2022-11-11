package com.apimanagement.employeemanagement.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.apimanagement.employeemanagement.Entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{
    
    @Query(
        value = "select * from employee where email = ?1",
        nativeQuery = true
    )
    Optional<Employee> findByEmail(String email);
}
