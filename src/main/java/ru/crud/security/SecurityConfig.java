package ru.crud.security;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import ru.crud.service.UserService;


public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private UserService userService;

    @Autowired
    public SecurityConfig(UserService userService) {
        this.userService = userService;
    }
}
