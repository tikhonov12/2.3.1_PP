package ru.crud.contoller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.crud.model.User;
import ru.crud.service.UserService;

import java.sql.SQLException;


@Controller
@RequestMapping("/users")
public class UsersController {

    private UserService service;

    public UsersController() {
    }

    @Autowired
    public UsersController(UserService userService) {
        this.service = userService;
    }
    @GetMapping("/")
    public String homePage(){
        return "home";
    }
    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("users", service.userList());
        return "home";
    }

    @PostMapping("/addUser")
    public String addUser(@ModelAttribute User user) throws SQLException {
        service.addUser(user);
        return "redirect:/users/home";
    }

    @GetMapping("/addUser")
    public String addUser(Model model) {
        model.addAttribute("user", new User());
        return "addUser";
    }

    @GetMapping("/{id}/editUser")
    public String edit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("user", service.showUser(id));
        return "editUser";
    }
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user")User user,@PathVariable("id") Long id) {
        service.update(id, user);
        return "redirect:/users/home";
    }

    @GetMapping("home/linkDelete")
    public String delete(@RequestParam(required = false) Long status) {
        service.removeUser(status);
        return "redirect:/users/home";
    }
}
