package ru.crud.service;

import ru.crud.model.Role;

import java.util.Set;

public interface RoleService {
    Role addRole(Role role);

    void deleteById(Long id);

    Role findById(Long id);

    Role findByName(String name);

    Set<Role> getAllRoles();
}
