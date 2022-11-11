package com.apimanagement.employeemanagement.Controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apimanagement.employeemanagement.Entity.Role;
import com.apimanagement.employeemanagement.Service.RoleService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/v1/roles")

public class RoleController {
    
    @Autowired
    RoleService roleService;

    @GetMapping("/")
    public ResponseEntity<Set<Role>> getALlRoles() {
        return new ResponseEntity<>(roleService.getRoles(), HttpStatus.OK);
    } 
    
    @GetMapping("/{name}")
    public ResponseEntity<Role> getRoles(@PathVariable String name) {
        System.out.println(name);
        return new ResponseEntity<>(roleService.getRole(name), HttpStatus.OK);
    } 

}
