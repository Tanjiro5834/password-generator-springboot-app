package com.password.password_generator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RootController {

    @GetMapping("/")
    public String root() {
        return "authentication"; // loads authentication.html
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "index"; // loads index.html
    }
}