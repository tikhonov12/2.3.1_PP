package ru.crud.contoller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.crud.model.Role;
import ru.crud.model.User;
import ru.crud.service.RoleService;
import ru.crud.service.UserService;

import java.sql.SQLException;
import java.util.*;


@Controller
@RequestMapping("/admin")
public class AdminController {

    private UserService userService;
    private RoleService roleService;

    public AdminController() {
    }

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/")
    public String homePage() {
        return "home";
    }

    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("users", userService.userList());
        return "home";
    }

    @PostMapping("/addUser")
    public String addUser(@ModelAttribute User user, @ModelAttribute("role") String role) throws SQLException {
        Set<Role> roles = new HashSet<>();
        if (roleService.findByName(role)==-1L){
            Role role1=new Role(role);
            roleService.addRole(role1);
            roleService.save(role1);
        }
        if (!role.equals("ROLE_USER")){
            roles.add(new Role("ROLE_USER"));
        }
        roles.add(roleService.findById(roleService.findByName(role)));

        user.setRoles(roles);
        userService.addUser(user);
        return "redirect:/admin/home";
    }

    @GetMapping("/addUser")
    public String addUser(Model model) {
        model.addAttribute("user", new User());
        return "addUser";
    }

    @GetMapping("/{id}/editUser")
    public String edit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("user", userService.showUser(id));
        return "editUser";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") Long id) {
        userService.update(id, user);
        return "redirect:/admin/home";
    }

    @GetMapping("home/linkDelete")
    public String delete(@RequestParam(required = false) Long status) {
        userService.removeUser(status);
        return "redirect:/admin/home";
    }
}
