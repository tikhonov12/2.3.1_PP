package ru.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.crud.model.User;


@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    User findByUsername(String username);
}