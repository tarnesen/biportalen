package se.tega.biportalen.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PlansController {
    @GetMapping("/user/plans")
    public String plans(Model model) {
        model.addAttribute("content", "plans/plans");
        model.addAttribute("submenu", "plans/submenu");
        return "main";

    }
    @GetMapping("/user/plans/langstroth")
    public String langstroth(Model model) {
        model.addAttribute("content", "plans/langstroth/introduction");
        model.addAttribute("submenu", "plans/submenu");
        return "main";

    }
}
