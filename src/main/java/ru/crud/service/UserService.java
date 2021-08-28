package ru.crud.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.crud.model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserService extends UserDetailsService {
    boolean addUser(User user) throws SQLException;
    User showUser(Long id);
    void removeUser(Long id);
    List userList();
    void update(Long id, User user);
}
