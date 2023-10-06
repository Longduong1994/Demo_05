package demo.controller;

import demo.exception.RegisterException;
import demo.model.dto.request.LoginForm;
import demo.model.dto.request.RegisterForm;
import demo.security.jwt.JwtEntryPoint;
import demo.security.jwt.JwtProvider;
import demo.security.user_principle.UserPrinciple;
import demo.service.impl.user.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.security.auth.login.LoginException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final IUserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;
    @GetMapping("/register")
    public ModelAndView register() {
        return new ModelAndView("register","user" ,new RegisterForm());
    }

    @GetMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("/login","user" ,new LoginForm());
    }

    @PostMapping("/register")
    public String register(@ModelAttribute @Valid RegisterForm registerForm) throws RegisterException {
        userService.save(registerForm);
        return "redirect:/";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute @Valid LoginForm loginForm, HttpServletResponse response) throws LoginException {
        Authentication authentication = null;
        try {
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginForm.getUsername(),loginForm.getPassword()));
        }catch (AuthenticationException e) {
            e.printStackTrace();
            throw new LoginException("Username or password is incorrect!");
        }
        UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
        if (userPrinciple.isStatus()== false){
            throw new LoginException("Account is locked");
        }
        String token = jwtProvider.generateToken(userPrinciple);
        Cookie cookie = new Cookie("token", token);
       response.addCookie(cookie);
        List<String> roles = userPrinciple.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        for (String r: roles) {
            if(r.equals("ROLE_ADMIN")){
                return "redirect:/admin";
            }
            if (r.equals("ROLE_USER")){
                return "redirect:/user";
            }
        }
        return "redirect:/403";
    }

}
