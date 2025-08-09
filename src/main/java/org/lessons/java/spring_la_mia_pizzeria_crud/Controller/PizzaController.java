package org.lessons.java.spring_la_mia_pizzeria_crud.Controller;

import java.util.List;

import org.lessons.java.spring_la_mia_pizzeria_crud.Model.Offer;
import org.lessons.java.spring_la_mia_pizzeria_crud.Model.Pizza;
import org.lessons.java.spring_la_mia_pizzeria_crud.Repository.IngredientRepository;
import org.lessons.java.spring_la_mia_pizzeria_crud.Repository.OfferRepository;

import org.lessons.java.spring_la_mia_pizzeria_crud.Service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
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
    private PizzaService pizzaService;
    @Autowired
    private OfferRepository offerRepository;
    @Autowired
    private IngredientRepository ingredientRepository;

    @GetMapping()
    public String index(Authentication authentication, Model model) {
        // Logica per recuperare le pizze dal database e passarle al template
       List<Pizza> pizzas = pizzaService.findAll();
        model.addAttribute("pizzas", pizzas);
        model.addAttribute("username", authentication.getName());
        return "pizzas/index"; // Ritorna il template delle pizze
    }
@GetMapping("/{id}")
public String show(@PathVariable("id") Integer id, Model model) {

    Pizza pizza = pizzaService.getById(id);
    model.addAttribute("pizza", pizza);
    return "pizzas/show"; // Ritorna il template della pizza specifica
}

@GetMapping("/search")
public String searchByNameOrIngredients(@RequestParam(name = "query") String query, Model model) {
    List<Pizza> pizzas = pizzaService.findByNameOrDescription(query);
    model.addAttribute("pizzas", pizzas);
    return "pizzas/index";
}

@GetMapping("/create")
public String create(Model model) {
    model.addAttribute("pizza", new Pizza());
    model.addAttribute("ingredients", ingredientRepository.findAll());
    return "pizzas/create-or-edit";
}
@PostMapping("/create")
public String store(@Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult, Model model) {
    if (bindingResult.hasErrors()) {
        model.addAttribute("ingredients", ingredientRepository.findAll());
        return "pizzas/create-or-edit";
    }
    model.addAttribute("pizza", formPizza);

    pizzaService.create(formPizza);
    return "redirect:/pizzas";

}

@GetMapping("/edit/{id}")
public String edit(@PathVariable("id") Integer id, Model model) {

model.addAttribute("pizza", pizzaService.getById(id));
 model.addAttribute("ingredients", ingredientRepository.findAll());
model.addAttribute("edit", true);
    return "pizzas/create-or-edit";

}
@PostMapping("/edit/{id}")
public String update(@Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult, Model model) {
    if (bindingResult.hasErrors()) {
        model.addAttribute("ingredients", ingredientRepository.findAll());
        return "pizzas/create-or-edit";
    }
    model.addAttribute("pizza", formPizza);


    pizzaService.update(formPizza);
    return "redirect:/pizzas";
}

@PostMapping("/delete/{id}")
public String delete(@PathVariable("id") Integer id) {
    Pizza pizza = pizzaService.getById(id);

    for (Offer offer : pizza.getOffers()) {

        offerRepository.delete(offer); // Elimino le offerte
    }
    pizzaService.delete(pizza);
    return "redirect:/pizzas"; 
}

@GetMapping("/{id}/offers")
public String offer(@PathVariable("id") Integer id, Model model) {
    Offer offer = new Offer();
    offer.setPizza(pizzaService.getById(id));
    model.addAttribute("offer", offer);
    return "offers/create-or-edit"; 
}
}

