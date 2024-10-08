package com.itschool.springbootdeveloper.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class UserViewController {
    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/signup")
    public String signup(){
        return "signup";
    }
}
