package com.ernok.boottest003.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

// Dummy controller for homepage.
// Directs to index.html

@Controller
public class HomeController {

    @RequestMapping(value = "/")
    public String getIndex() {
        return "index";
    }

}
