package ru.ilinov.about.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.ilinov.about.entity.User;
import ru.ilinov.about.service.UserService;

@Controller
public class UserController {

    private final UserService userService;

    UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(User user) {
        userService.createUser(user);
        return "redirect:/";
    }
}
