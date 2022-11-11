package com.apimanagement.employeemanagement.Service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apimanagement.employeemanagement.Entity.Employee;
import com.apimanagement.employeemanagement.Repository.EmployeeRepository;

@Service
public class EmployeeServiceImp implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;


    @Override
    public void deleteEmployee(String email) {
        Optional<Employee> entity = employeeRepository.findByEmail(email);
        Employee employee = checkEmployee(entity, 404L);
        employeeRepository.delete(employee);
        
    }

    @Override
    public Employee getEmployeeByEmail(String email) {
        Optional<Employee> entity = employeeRepository.findByEmail(email);
        Employee employee = checkEmployee(entity, 404L);
        return employee;

    }

    @Override
    public Employee getEmployeeById(Long Id) {
        Optional<Employee> entity = employeeRepository.findById(Id);
        Employee employee = checkEmployee(entity, 404L);
        return employee;
        
    }

    @Override
    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public void saveEmployee(Employee employee) {
        
        Optional<Employee> entity = employeeRepository.findByEmail(employee.getEmail());
        if(entity.isPresent()) {
            throw new EntityNotFoundException("the employee with email " + employee.getEmail() + " already exists");
        }
        employeeRepository.save(employee);
        
        
    }

    @Override
    public Employee updateEmployee(Long id, String lastname, String firstname) {
        Optional<Employee> entity = employeeRepository.findById(id);
        Employee employee = checkEmployee(entity, 404L);;
        if(lastname != null) {
            employee.setLastname(lastname);
        }
        if(firstname !=null) {
            employee.setFirstname(firstname);
        }
       return employeeRepository.save(employee);
    }

    public Employee checkEmployee(Optional<Employee> entity, Long id) {
        if(entity.isPresent()) return entity.get();
        throw new EntityNotFoundException("the employee with id "  + id + " not present");
    }
    
}
