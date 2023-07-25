package com.gestock.fourniture.presentation;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {
    @GetMapping("/home")
    public String dashboard(Model model) {
        return "admin/dashboard";
    }
}
