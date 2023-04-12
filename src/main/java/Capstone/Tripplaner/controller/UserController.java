package Capstone.Tripplaner.controller;

import Capstone.Tripplaner.data.dto.UserDto;
import Capstone.Tripplaner.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user")
    public String register(@ModelAttribute UserDto userDto){
        return "";
    }

    @PostMapping("/user")
    public String login(ModelAttribute userData, Model model){
        model.addAttribute("userData",userData);

    }
}
