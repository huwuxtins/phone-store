package com.example.ecommerce.controllers;

import com.example.ecommerce.auth.AuthenticationRequest;
import com.example.ecommerce.auth.AuthenticationResponse;
import com.example.ecommerce.auth.AuthenticationService;
import com.example.ecommerce.auth.RegisterRequest;
import com.example.ecommerce.models.EmailDetail;
import com.example.ecommerce.models.Role;
import com.example.ecommerce.models.User;
import com.example.ecommerce.services.EmailDetailService;
import com.example.ecommerce.services.UserService;
import jakarta.annotation.Nullable;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class UserController {
    private final UserService userService;
    private final AuthenticationService authenticationService;
    private final EmailDetailService emailDetailService;
    @Autowired
    public UserController(UserService userService,
                          AuthenticationService authenticationService,
                          EmailDetailService emailDetailService){
        this.userService = userService;
        this.authenticationService = authenticationService;
        this.emailDetailService = emailDetailService;
    }

    @GetMapping("")
    public String loadHomePage(@Nullable @CookieValue String email, Model model){
        if(email != null){
            try{
                User user = userService.getInforUser(email);
                model.addAttribute("user", user);
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        return "index";
    }

    @GetMapping("account/see_detail")
    public String seeDetail(@CookieValue String email, Model model){
        try{
            User user = userService.getInforUser(email);
            model.addAttribute("user", user);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return "account";
    }

    @GetMapping("account/register")
    public String loadRegisterPage(){
        return "/register";
    }

    @PostMapping("account/register")
    public String register(@RequestParam Map<String, String> account,
                            Model model,
                            HttpServletResponse response){
        RegisterRequest user = new RegisterRequest(account.get("customer[first_name]")
                +" "+ account.get("customer[last_name]"),
                account.get("customer[password]"),
                account.get("customer[email]"),
                account.get("customer[phone]"),
                Role.USER);
        try{
            AuthenticationResponse jwtToken = authenticationService.register(user);

            emailDetailService.sendSimpleMail(new EmailDetail(user.getEmail(),
                    "Bạn vừa đăng kí thành công với chúng tôi",
                    "Thông báo đăng kí thành công"));

            setCookie(user.getEmail(), model, response, jwtToken.getToken());
            return "account";
        }
        catch(Exception e){
            return null;
        }
    }

    @PostMapping("account/login")
    public String login(@RequestParam Map<String, String> account,
                        Model model,
                        HttpServletResponse response){
        try{
            AuthenticationRequest authenticationRequest =
                    new AuthenticationRequest(account.get("customer[email]"), account.get("customer[password]"));
            AuthenticationResponse jwtToken = authenticationService.authenticate(authenticationRequest);

            setCookie(authenticationRequest.getEmail(), model, response, jwtToken.getToken());

            return "account";
        }
        catch(Exception e){
            return "login";
        }
    }

    @PostMapping("account/update")
    public String updateAccount(@RequestParam Map<String, String> account,
                                @CookieValue(name="email") String email,
                                @CookieValue(name="jwtToken") String jwtToken,
                                Model model,
                                HttpServletResponse response){
        User user = userService.getInforUser(email);

        user.setName(account.get("customer[name]"));
        user.setAddress(account.get("customer[address]"));
        user.setPhone(account.get("customer[phone]"));
        try{
            userService.updateUser(user);

            emailDetailService.sendSimpleMail(new EmailDetail(user.getEmail(),
                    "Bạn vừa thay đổi thông tin thành công với chúng tôi",
                    "Thông báo thay đổi thành công"));

            setCookie(user.getEmail(), model, response, jwtToken);

            return "account";
        }
        catch(Exception e){
            return null;
        }
    }

    @RequestMapping("account/logout")
    public String logout(HttpServletResponse response){
        Cookie cookieEmail = new Cookie("email", null);
        cookieEmail.setMaxAge(0);
        cookieEmail.setPath("/");

        Cookie cookieJwtToken = new Cookie("jwtToken", null);
        cookieJwtToken.setMaxAge(0);
        cookieJwtToken.setPath("/");

        response.addCookie(cookieEmail);
        response.addCookie(cookieJwtToken);

        return "index";
    }

    @PostMapping("account/recover")
    public String recover(){
        return "login";
    }

    private void setCookie(String email,
                           Model model,
                           HttpServletResponse response,
                           String jwtToken){
        User newUser = userService.getInforUser(email);

        Cookie cookieEmail = new Cookie("email", newUser.getEmail());
        cookieEmail.setMaxAge(604800);
        cookieEmail.setPath("/");

        Cookie cookieJwtToken = new Cookie("jwtToken", jwtToken);
        cookieJwtToken.setMaxAge(604800);
        cookieJwtToken.setPath("/");

        response.addCookie(cookieEmail);
        response.addCookie(cookieJwtToken);

        response.setHeader("Authorization", "Bearer "+jwtToken);

        model.addAttribute("user", newUser);
    }
}
