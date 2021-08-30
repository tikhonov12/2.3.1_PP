package ru.crud.repository;

import ru.crud.model.User;

public interface UserRepositoryCustom {

    void detachUser(User u);

}