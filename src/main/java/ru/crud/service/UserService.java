package ru.crud.service;

import ru.crud.model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserService {
    void addUser(User user) throws SQLException;
    User showUser(Long id);
    void removeUser(Long id);
    List userList();
    void update(Long id, User user);
}
