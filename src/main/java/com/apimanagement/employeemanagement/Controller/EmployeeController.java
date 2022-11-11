package com.apimanagement.employeemanagement.Controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.apimanagement.employeemanagement.Entity.Employee;
import com.apimanagement.employeemanagement.Service.EmployeeService;

import lombok.val;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;


    @GetMapping("/")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return new ResponseEntity<>(employeeService.getEmployees(), HttpStatus.OK);
    }
    @GetMapping("/email/{email}")
    public ResponseEntity<Employee> getEmployeeByEmail(@PathVariable String email) {
        return new ResponseEntity<>(employeeService.getEmployeeByEmail(email), HttpStatus.OK);
    }
    @GetMapping("/id/{id}")
    public ResponseEntity<Employee> getEmployeeByid(@PathVariable Long id) {
        return new ResponseEntity<>(employeeService.getEmployeeById(id), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<HttpStatus> saveEmployee(@Valid @RequestBody Employee employee) {
        employeeService.saveEmployee(employee);
        return new ResponseEntity<HttpStatus>( HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmplooyee(@PathVariable Long id, @RequestParam(required = false) String lastname, @RequestParam(required = false) String firstname) {
        return new ResponseEntity<Employee>(employeeService.updateEmployee(id, lastname, firstname), HttpStatus.OK);
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable String email) {
        employeeService.deleteEmployee(email); 
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    
}
