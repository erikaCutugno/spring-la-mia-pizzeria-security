package org.lessons.java.spring_la_mia_pizzeria_crud.Controller;

import java.util.List;

import org.lessons.java.spring_la_mia_pizzeria_crud.Model.Pizza;
import org.lessons.java.spring_la_mia_pizzeria_crud.Repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping("/pizzas")
public class PizzaController {
    @Autowired
    private PizzaRepository repository;
    @GetMapping()
    public String index(Model model) {
        // Logica per recuperare le pizze dal database e passarle al template
       List<Pizza> pizzas = repository.findAll();
        model.addAttribute("pizzas", pizzas);
        return "pizzas/index"; // Ritorna il template delle pizze
    }
@GetMapping("/{id}")
public String show(@PathVariable("id") Integer id, Model model) {
    
    Pizza pizza = repository.findById(id).get();
    model.addAttribute("pizza", pizza);
    return "pizzas/show"; // Ritorna il template della pizza specifica
}

@GetMapping("/search")
public String searchByNameOrIngredients(@RequestParam(name = "query") String query, Model model) {
    List<Pizza> pizzas = repository.findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(query, query);
    model.addAttribute("pizzas", pizzas);
    return "pizzas/index";
}

@GetMapping("/create")
public String create(Model model) {
    model.addAttribute("pizza", new Pizza());
    return "pizzas/create";
}
@PostMapping("/create")
public String store(@Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult, Model model) {
    if (bindingResult.hasErrors()) {
        
        return "pizzas/create";
    }
    model.addAttribute("pizza", formPizza);
   
    repository.save(formPizza);
    return "redirect:/pizzas"; 
 
}

}

 