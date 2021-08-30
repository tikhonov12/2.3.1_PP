package ru.crud.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.crud.model.Role;
import ru.crud.model.User;
import ru.crud.repository.UserRepository;
import ru.crud.repository.UserRepositoryCustom;
import ru.crud.service.RoleService;
import ru.crud.service.UserService;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepositoryCustom userRepositoryCustom;
    private final UserRepository userRepository;
    private final RoleService roleService;


    @Autowired
    public UserServiceImpl(@Qualifier("userRepository") UserRepositoryCustom userRepositoryCustom, UserRepository userRepository, RoleService roleService) {
        super();
        this.userRepositoryCustom = userRepositoryCustom;
        this.userRepository = userRepository;
        this.roleService = roleService;
    }

    @Override
    public User addUser(User user) {
        Role role =new Role();
        String DEFAULT_ROLE = "ROLE_USER";
        role.setRoleName(DEFAULT_ROLE);
        if (roleService.findByName(DEFAULT_ROLE).getRoleName() == null) {
            user.getRoleSet().add(roleService.addRole(role));
            return userRepository.save(user);
        }
        role=roleService.findByName(DEFAULT_ROLE);
        user.getRoleSet().add(role);
        return  userRepository.save(user);
    }

    @Override
    public void deleteById(Long id) {
        userRepositoryCustom.detachUser(findById(id));
        userRepository.deleteById(id);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public User findByName(String name) {
        return getAllUsers().stream().filter(s -> s.getUsername().equals(name)).findFirst().get();
    }

    @Override
    public Set<User> getAllUsers() {
        Set<User> userSet = new HashSet<>(userRepository.findAll());
        return userSet;
    }

    @Override
    public void updateUser(User user, Long id, String roleName) {

        Role role = roleService.findByName(roleName);
        User user1 = findByName(user.getUsername());

        if (role.getRoleName() == null) {
            role = new Role();
            role.setRoleName(roleName);
            roleService.addRole(role);
            user1.getRoleSet().add(roleService.findByName(roleName));
        } else {
            user1.getRoleSet().add(role);
        }
        userRepository.save(user1);
    }


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = findByName(s);
        if (user==null){
            throw new UsernameNotFoundException(String.format("user '%s' not found",s));
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(),
                user.getRoleSet()
                        .stream()
                        .map(q->new SimpleGrantedAuthority(q.getRoleName()))
                        .collect(Collectors.toList()));
    }
}
