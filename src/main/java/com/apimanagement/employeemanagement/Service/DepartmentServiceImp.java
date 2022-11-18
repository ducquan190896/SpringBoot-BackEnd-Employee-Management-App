package com.apimanagement.employeemanagement.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apimanagement.employeemanagement.Entity.Department;
import com.apimanagement.employeemanagement.Entity.Employee;
import com.apimanagement.employeemanagement.Exception.EntityNotFoundException;
import com.apimanagement.employeemanagement.Repository.DepartmentRepository;
import com.apimanagement.employeemanagement.Repository.EmployeeRepository;

@Service
public class DepartmentServiceImp implements DepartmentService {
    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public void deleteDepartment(Long id) {
        
        Optional<Department> entity = departmentRepository.findById(id);
        Department department = isCheck(entity, 404L);
        departmentRepository.delete(department);
    }

    @Override
    public List<Department> getAllDepartment() {
        return departmentRepository.findAll();
    }

    @Override
    public Department getDepartment(String name) {
        
        Optional<Department> entity = departmentRepository.findByName(name);
        Department department = isCheck(entity, 404L);
        return department;
    }

    @Override
    public void saveDepartment(Department department) {
        departmentRepository.save(department);
        
    }

    @Override
    public Department updateDepartment(Long id, String name) {
        
        Optional<Department> entity = departmentRepository.findById(id);
        Department department = isCheck(entity, 404L);
        department.setName(name);
        departmentRepository.save(department);
        return department;
    }

    private Department isCheck(Optional<Department> entity, Long id) {
        if(entity.isPresent()) {
            return entity.get();
        }
        throw new EntityNotFoundException("the department with id " + id + " not found");
    }

    @Override
    public void addEmployeeToDepartment(Long departmentId, Long employeeId) {
        
        Optional<Department> entity = departmentRepository.findById(departmentId);
        Department department = isCheck(entity, 404L);
        Optional<Employee> employeeEntity = employeeRepository.findById(employeeId);
        if(!employeeEntity.isPresent()) {
            throw new EntityNotFoundException("employe with the id " + employeeId + " not found");
        }
        Employee employee = employeeEntity.get();
        department.getEmployees().add(employee);
        departmentRepository.save(department);
    }

    @Override
    public void removeEmployeeToDepartment(Long departmentId, Long employeeId) {
        Optional<Department> entity = departmentRepository.findById(departmentId);
        Department department = isCheck(entity, 404L);
        Optional<Employee> employeeEntity = employeeRepository.findById(employeeId);
        if(!employeeEntity.isPresent()) {
            throw new EntityNotFoundException("employe with the id " + employeeId + " not found");
        }
        Employee employee = employeeEntity.get();
        department.setEmployees(department.getEmployees().stream().filter(emp -> emp.getId() != employeeId).collect(Collectors.toList()));
        departmentRepository.save(department);
        
    }
    
}
