package com.apimanagement.employeemanagement.Entity;

public enum RoleType {
    ROLE_USER("ROLE_USER"),
    ROLE_ADMIN("ROLE_ADMIN"),
    ROLE_MANAGER("ROLE_MANAGER");

    private String role;

     RoleType(String role) {
        this.role = role;
    }

    public String getRoleType() {
        return this.role;
    }
}
