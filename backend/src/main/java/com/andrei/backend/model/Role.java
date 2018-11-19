package com.andrei.backend.model;

public enum Role {
    BASIC("BASIC"),
    CLERK("CLERK"),
    ADMIN("ADMIN");

    private String value;

    Role(String role) {
        this.value = role;
    }

    public String getValue() {
        return value;
    }
}
