package ru.crud.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.crud.model.User;
import ru.crud.service.UserService;

import java.security.Principal;

@Controller
public class MainController {

    private final UserService userService;

    @Autowired
    public MainController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String homePage() {
        return "homePage";
    }

    @GetMapping("/authenticated")
    public String pageForAuth(Model model, Principal principal) {
        User user = userService.findByName(principal.getName());
        model.addAttribute("user", user);
        model.addAttribute("roles", user.getRoleSet());
        return "userData";
    }

}
