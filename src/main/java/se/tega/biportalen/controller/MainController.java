package se.tega.biportalen.controller;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Locale;

@Controller
public class MainController {
    @GetMapping("/")
    public String index(Model model) {
        Locale currentLocale = LocaleContextHolder.getLocale();
        System.out.println("Current Locale: " + currentLocale.getLanguage());
        model.addAttribute("content", "index");
        return "main";
    }


}
