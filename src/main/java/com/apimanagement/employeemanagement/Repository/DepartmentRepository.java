package com.apimanagement.employeemanagement.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.apimanagement.employeemanagement.Entity.Department;

@Repository
public interface DepartmentRepository extends
JpaRepository<Department, Long> {

    @Query(value = "select * from department where name = ?1", nativeQuery = true)
    Optional<Department> findByName(String name);
}
