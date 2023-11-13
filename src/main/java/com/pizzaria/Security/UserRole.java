package com.pizzaria.Security;

public enum UserRole {
    ADMIN("admin"),
    USER("user");

    private String role;

    UserRole(String role) {
        this.role= role;
    }

    @Override
    public String toString() {
        return role;
    }
}
