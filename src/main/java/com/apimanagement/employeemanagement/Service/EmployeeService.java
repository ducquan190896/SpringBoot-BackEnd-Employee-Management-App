package com.apimanagement.employeemanagement.Service;

import java.util.List;

import com.apimanagement.employeemanagement.Entity.Employee;

public interface EmployeeService {
    public List<Employee> getEmployees();
    public Employee getEmployeeByEmail(String email);
    public Employee getEmployeeById(Long Id);
    public void saveEmployee(Employee employee);
    public Employee updateEmployee(Long id, String lastname, String firstname);
    public void deleteEmployee(String email);
}
