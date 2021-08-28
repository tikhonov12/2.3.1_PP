package ru.crud.service;

import org.springframework.stereotype.Service;
import ru.crud.model.Role;

import ru.crud.repository.RoleRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
@Transactional
public class RoleServiceImpl implements RoleService {
    private RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        super();
        this.roleRepository = roleRepository;
    }

    @Override
    public Role findById(Long id) {
       return roleRepository.findById(id).get();
    }

    @Override
    public void addRole(Role role) {
        roleRepository.save(role);
    }

    @Override
    public List<Role> listRoles() {
        List<Role> list = new ArrayList<>();
        list.addAll(roleRepository.findAll());
        return list;
    }

    @Override
    public Long findByName(String role) {
        return listRoles().stream().filter(s->s.equalsRole(role)).map(Role::getId).findFirst().orElse(-1L);
    }

    @Override
    public void save(Role role) {
        roleRepository.save(role);
    }

}
