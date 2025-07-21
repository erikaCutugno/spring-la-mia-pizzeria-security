package org.lessons.java.spring_la_mia_pizzeria_crud.Controller;

import java.util.List;

import org.lessons.java.spring_la_mia_pizzeria_crud.Model.Ingredient;
import org.lessons.java.spring_la_mia_pizzeria_crud.Model.Pizza;
import org.lessons.java.spring_la_mia_pizzeria_crud.Repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/ingredients")
public class IngredientController {
    @Autowired
    private IngredientRepository ingredientRepository;
    
     @GetMapping()
    public String index(Model model) {
        
       List<Ingredient> ingredients = ingredientRepository.findAll();
        model.addAttribute("ingredients", ingredients);
        return "ingredients/index"; 
    }

@GetMapping("/{id}")
public String show(@PathVariable("id") Integer id, Model model) {
    Ingredient ingredient = ingredientRepository.findById(id).get();
    model.addAttribute("ingredient", ingredient);
    return "ingredients/show";
}
@GetMapping("/create")
public String create(Model model) {
    model.addAttribute("ingredient", new Ingredient());
    return "ingredients/create-or-edit";
}

@PostMapping("/create")
public String store(@Valid @ModelAttribute("ingredient") Ingredient formIngredient, BindingResult bindingResult, Model model) {
    if (bindingResult.hasErrors()) {
        return "ingredients/create-or-edit";
    }
    model.addAttribute("ingredient", formIngredient);
    ingredientRepository.save(formIngredient);
    return "redirect:/ingredients";
}

    
@GetMapping("/edit/{id}")
public String edit(@PathVariable("id") Integer id, Model model) {

    Ingredient ingredient = ingredientRepository.findById(id).get();
    model.addAttribute("ingredient", ingredient);
    model.addAttribute("edit", true);
    return "ingredients/create-or-edit";

}
@PostMapping("/edit/{id}")
public String update(@Valid @ModelAttribute("ingredient") Ingredient formIngredient,  BindingResult bindingResult, Model model) {

    if (bindingResult.hasErrors()) {
        return "ingredients/create-or-edit";
    }

    model.addAttribute("ingredient", formIngredient);

    ingredientRepository.save(formIngredient);
    return "redirect:/ingredients";
}

@PostMapping("/delete/{id}")
public String delete(@PathVariable("id") Integer id) {
    Ingredient ingredient = ingredientRepository.findById(id).get();

    for (Pizza pizza :ingredient.getPizzas()){
        pizza.getIngredients().remove(ingredient);
    }
    
    ingredientRepository.delete(ingredient);
    return "redirect:/ingredients";
    }
}
