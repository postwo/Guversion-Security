package com.example.security.controller;

import com.example.security.config.SecurityConfig;
import com.example.security.model.User;
import com.example.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequiredArgsConstructor
public class IndexController {

   // private static final Logger log = LoggerFactory.getLogger(SecurityConfig.class);
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping({"","/"})
    public String index(){
        return "index";
    }

    @GetMapping("/user")
    public String user(){
        return "user";
    }

    @GetMapping("/admin")
    public String admin(){
        return "admin";
    }

    @GetMapping("/manager")
    public String manager(){
        return "manager";
    }

    @GetMapping("/loginForm")
    public String loginForm(){
        return "loginForm";
    }

    @GetMapping("/joinForm")
    public String joinForm(){
        return "joinForm";
    }


    @PostMapping("/join")
    public String join(User user){
        //System.out.println(user);
        String rawpassword = user.getPassword();
        String encpassword = bCryptPasswordEncoder.encode(rawpassword);
        user.setRole("ROLE_MANAGER");
        user.setPassword(encpassword);
        userRepository.save(user);
        return "redirect:/loginForm";
    }


}//redirect:/loginForm

