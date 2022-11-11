package com.apimanagement.employeemanagement.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import com.apimanagement.employeemanagement.Entity.Role;
import com.apimanagement.employeemanagement.Repository.RoleRepository;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class RoleServiceImp implements RoleService {

    RoleRepository roleRepository;


    @Override
    public Role getRole(String name) {
      
        Optional<Role> entity = roleRepository.findByName(name);
        if(!entity.isPresent()) {
           
            throw new EntityNotFoundException("role with name " + name + "  is not exist");
        }
        return entity.get();
    }

    @Override
    public Set<Role> getRoles() {
       
        return roleRepository.findAll().stream().collect(Collectors.toSet());
    }

    
    
}
