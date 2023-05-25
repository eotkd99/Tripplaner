package Capstone.Tripplaner.loginService.controller;

import Capstone.Tripplaner.ItemService.data.dto.ItemOneImage;
import Capstone.Tripplaner.ItemService.data.entity.ItemEntity;
import Capstone.Tripplaner.ItemService.service.ItemService;
import Capstone.Tripplaner.loginService.data.User;
import Capstone.Tripplaner.loginService.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@Slf4j
public class LoginController {

    UserService userService;
    ItemService itemService;
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

    public LoginController(UserService userService, ItemService itemService) {
        this.userService = userService;
        this.itemService = itemService;
    }

    @GetMapping("/")
    public String home(@ModelAttribute("user") User user, Model model) {
        List<ItemEntity> DLSLikeList = itemService.descLikeSort();
        List<ItemEntity> DLSViewList = itemService.descViewSort();
        List<ItemOneImage> a = itemService.processItemImgList(DLSLikeList);
        model.addAttribute("DLSLikeListImg", itemService.processItemImgList(DLSLikeList));
        model.addAttribute("DLSViewListImg", itemService.processItemImgList(DLSViewList));
        return "main/index";
    }

    @GetMapping("/login")
    public String loginForm(@ModelAttribute("user") User user) {
        return "login/loginForm";
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
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }
}
