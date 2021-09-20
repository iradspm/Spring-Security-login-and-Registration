package com.example.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AppController {
    @Autowired
    private UserRepository repo;
    @GetMapping("/")
    public String viewHomePage()
    {
        return "index";
    }
    @GetMapping("/register")
    public String showSignUpForm(Model model)
    {
        model.addAttribute("user",new User());
        return "signup_form";
    }
    @PostMapping("/process_register")
    public String processRegister(User user)
    {
        BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
        String encodedpassword=encoder.encode(user.getPassword());
        user.setPassword(encodedpassword);
        repo.save(user);
        return "register_success";
    }
    @GetMapping("/list_users")
    public String viewUsersList(Model model)
    {
        List<User> listUsers=repo.findAll();
        model.addAttribute("listUsers",listUsers);
        return "users";
    }
}
