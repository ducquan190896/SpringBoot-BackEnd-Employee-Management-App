package com.apimanagement.employeemanagement.Service;

import java.util.Optional;
import java.util.Set;

import com.apimanagement.employeemanagement.Entity.Role;

public interface RoleService {
    public Set<Role> getRoles();
    public Role getRole(String name);
}
