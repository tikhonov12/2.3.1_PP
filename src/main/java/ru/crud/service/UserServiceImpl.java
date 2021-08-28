package ru.crud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ru.crud.model.Role;
import ru.crud.model.User;
import ru.crud.repository.RoleRepository;
import ru.crud.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;


    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        super();
        this.userRepository = userRepository;
    }

    @Override
    public boolean addUser(User user) {
        userRepository.save(user);
        return true;
    }

    @Override
    public User showUser(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public void removeUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<User> userList(){
         List<User> list = new ArrayList<>();
        list.addAll((Collection<? extends User>) userRepository.findAll());
         return list;
    }

    @Override
    public void update(Long id, User user) {
     User  userToBeUpdated = showUser(id);
       userToBeUpdated.setUsername(user.getUsername());
       userToBeUpdated.setPassword(user.getPassword());
       userToBeUpdated.setEmail(user.getEmail());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }
}
