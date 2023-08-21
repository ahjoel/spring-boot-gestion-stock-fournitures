package com.gestock.fourniture.rest.ressource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DashboardController {
    @GetMapping("/home")
    public String dashboard(Model model) {
        return "admin/dashboard";
    }

    @RequestMapping("/hello")
    @ResponseBody
    String home() {
        return "Hello World!";
    }
}
