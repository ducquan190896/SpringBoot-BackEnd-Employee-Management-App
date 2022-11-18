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

import com.apimanagement.employeemanagement.Entity.Department;
import com.apimanagement.employeemanagement.Entity.Employee;
import com.apimanagement.employeemanagement.Service.DepartmentService;

@RestController
@RequestMapping("/api/v1/departments")
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;

    @GetMapping("/all")
    public ResponseEntity<List<Department>> getAllDepartments() {
        return new ResponseEntity<>(departmentService.getAllDepartment(), HttpStatus.OK);
    }
    @GetMapping("/deparmentName/{name}")
    public ResponseEntity<Department> getDepartment(@PathVariable String name) {
        return new ResponseEntity<Department>(departmentService.getDepartment(name), HttpStatus.OK);
    }
    @PostMapping("/")
    public ResponseEntity<HttpStatus> saveDepartment(@Valid @RequestBody Department department) {
        departmentService.saveDepartment(department);
        return new ResponseEntity<HttpStatus>( HttpStatus.CREATED);
    }
    @DeleteMapping("/departmentID/{id}")
    public ResponseEntity<HttpStatus> deleteDepartment(@PathVariable Long id) {
        departmentService.deleteDepartment(id);
        return new ResponseEntity<HttpStatus>( HttpStatus.NO_CONTENT);
    }
    @PutMapping("/departmentId/{id}/updateName")
    public ResponseEntity<Department> updateDepartment(@PathVariable Long id, @RequestParam(required = true) String name) {
        return new ResponseEntity<>(departmentService.updateDepartment(id, name), HttpStatus.ACCEPTED);
    }
    @PutMapping("/departmentId/{id}/AddEmployeeId/{employeeId}")
    public ResponseEntity<HttpStatus> AddEmployeeToDepartment(@PathVariable Long id, @PathVariable Long employeeId) {
        departmentService.addEmployeeToDepartment(id, employeeId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/departmentId/{id}/RemoveEmployeeId/{employeeId}")
    public ResponseEntity<HttpStatus> removeEmployeefromDepartment(@PathVariable Long id, @PathVariable Long employeeId) {
        departmentService.removeEmployeeToDepartment(id, employeeId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
