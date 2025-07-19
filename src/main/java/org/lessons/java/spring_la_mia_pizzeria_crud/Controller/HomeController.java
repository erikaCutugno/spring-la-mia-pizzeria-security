package org.lessons.java.spring_la_mia_pizzeria_crud.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {
        
    // Questo controller gestisce la home page dell'applicazione
    @GetMapping
    public String home() {
        return "home"; 
    }
}
