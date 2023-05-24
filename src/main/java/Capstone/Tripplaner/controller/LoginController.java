package Capstone.Tripplaner.controller;

import Capstone.Tripplaner.data.dto.User;
import Capstone.Tripplaner.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@Slf4j
public class LoginController {

    UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String home(@ModelAttribute("user") User user) {
        return "main/index";
    }

    @GetMapping("/login")
    public String loginForm(@ModelAttribute("user") User user) {
        return "login/loginForm";
    }


    @PostMapping("/login")
    public String login(@Validated @ModelAttribute("user") User user, BindingResult bindingResult,
                        @RequestParam(defaultValue = "/") String redirectURL,
                        HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return "login/loginForm";
        }
        if (userService.login(user.getId(), user.getPassword()) == null) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "login/loginForm";
        }
        HttpSession session = request.getSession();
        session.setAttribute("user", user);
        return "main/index";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }
}
