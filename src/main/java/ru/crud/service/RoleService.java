package ru.crud.service;

import ru.crud.model.Role;
import ru.crud.model.User;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface RoleService {

    Role findById(Long id);

    void addRole(Role role);

    List<Role> listRoles();

    Long findByName(String role);

    void save(Role role);
}
