package ru.crud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.crud.model.User;
import ru.crud.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private UserRepository repository;

    public UserServiceImpl() {
    }
    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        super();
        this.repository = userRepository;
    }

    @Override
    public void addUser(User user){
        repository.save(user);
    }

    @Override
    public User showUser(Long id) {
        return repository.findById(id).get();
    }

    @Override
    public void removeUser(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<User> userList(){
         List<User> list = new ArrayList<>();
         repository.findAll().forEach(list::add);
         return list;
    }

    @Override
    public void update(Long id, User user) {
     User  userToBeUpdated = showUser(id);
       userToBeUpdated.setName(user.getName());
       userToBeUpdated.setLastName(user.getLastName());
       userToBeUpdated.setAge(user.getAge());
    }
}
