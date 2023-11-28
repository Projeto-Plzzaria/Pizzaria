package com.pizzaria.entity.user;

public record RegisterDTO(String login, String password, UserRole role) {
}
