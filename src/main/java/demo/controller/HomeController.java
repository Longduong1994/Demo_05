package demo.controller;

import demo.model.dto.request.LoginForm;
import demo.model.dto.request.RegisterForm;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping
    public String homePage() {
        return "/home";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String adminPage() {
        return "/admin";
    }

    @GetMapping("/user")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String userPage() {
        return "/user";
    }

    @GetMapping("/403")
    public String notFound() {
        return "/403";
    }

}
