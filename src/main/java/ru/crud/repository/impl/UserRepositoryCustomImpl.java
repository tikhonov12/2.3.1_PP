package ru.crud.repository.impl;

import org.springframework.stereotype.Repository;
import ru.crud.model.User;
import ru.crud.repository.UserRepositoryCustom;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
@Repository
public class UserRepositoryCustomImpl implements UserRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void detachUser(User u) {
        entityManager.detach(u);
    }
}
