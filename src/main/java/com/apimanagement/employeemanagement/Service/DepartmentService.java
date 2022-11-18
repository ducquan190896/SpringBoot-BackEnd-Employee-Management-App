package com.apimanagement.employeemanagement.Service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.apimanagement.employeemanagement.Entity.Department;
import com.apimanagement.employeemanagement.Entity.Employee;

public interface DepartmentService {
    List<Department> getAllDepartment();
    Department getDepartment(String name);
    void saveDepartment(Department department);
    void addEmployeeToDepartment(Long departmentId, Long employeeId);
    void removeEmployeeToDepartment(Long departmentId, Long employeeId);
    @Transactional
    Department updateDepartment(Long id, String name);
    @Transactional
    void deleteDepartment(Long id);
}
