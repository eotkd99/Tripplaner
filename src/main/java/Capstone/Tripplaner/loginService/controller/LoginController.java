package Capstone.Tripplaner.loginService.controller;

import Capstone.Tripplaner.loginService.data.User;
import Capstone.Tripplaner.loginService.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
@Slf4j
public class LoginController {

    UserService userService;
    @RequestMapping("/404")
    public String accessDenied404() {
        return "security/404Denied";
    }
    @RequestMapping("/500")
    public String accessDenied505() {
        return "security/500Denied";
    }
    @RequestMapping("/403")
    public String accessDenied403() {
        return "security/403Denied";
    }

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String home(@SessionAttribute("user") User user, Model model) {
        model.addAttribute("user", user);
        return "main/index";
    }


    @GetMapping("/login")
    public String loginForm(@ModelAttribute("user") User user) {
        return "login/loginForm";
    }


    @GetMapping("/info")
    public String getUserInfo(Principal principal, HttpServletRequest request) {
        if (principal instanceof OAuth2AuthenticationToken) {
            OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) principal;
            OAuth2User oauthUser = oauthToken.getPrincipal();

            String username = oauthUser.getAttribute("name");

            HttpSession session = request.getSession();
            User user= new User();
            user.setUsername(username);
            user.setRole("USER");
            userService.saveUser(user);
            session.setAttribute("user", user);
            return "redirect:/";
        }
        return "User Info not available";
    }

    @PostMapping("/custom-login")
    public String login(@Validated @ModelAttribute("user") User user, BindingResult bindingResult, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
                return "login/loginForm";
        }
        if (userService.login(user.getUsername(), user.getPassword()) == null) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "login/loginForm";
        }
        HttpSession session = request.getSession();
        session.setAttribute("user", user);
        return "redirect:/";
    }
    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {session.invalidate();}
        return "redirect:/";
    }

    @GetMapping("/register")
    public String registerForm(@ModelAttribute("user") User user) {
        return "login/addForm";
    }

    @PostMapping("/register")
    public String register(@Validated @ModelAttribute("user") User user, BindingResult bindingResult, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return "login/addForm";
        }
        if (userService.login(user.getUsername(), user.getPassword()) != null) {
            bindingResult.reject("loginFail", "이미 존재하는 아이디 입니다.");
            return "login/addForm";
        }
        if(user.getUsername().equals("admin")) user.setRole("ADMIN");
        else user.setRole("USER");
        userService.saveUser(user);
        return "redirect:/";
    }
}
