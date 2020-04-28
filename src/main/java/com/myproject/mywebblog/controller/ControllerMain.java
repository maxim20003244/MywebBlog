package com.myproject.mywebblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class ControllerMain {

    @GetMapping("/")
    public String home( Model model) {
        model.addAttribute("title", "Home Page");
        return "home";
    }
    @GetMapping("/about")
    public String about( Model model) {
        model.addAttribute("title", "Page about us");
        return "about";
    }

}