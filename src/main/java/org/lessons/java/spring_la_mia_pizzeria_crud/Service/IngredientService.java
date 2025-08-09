package org.lessons.java.spring_la_mia_pizzeria_crud.Service;

import java.util.List;
import java.util.Optional;

import org.lessons.java.spring_la_mia_pizzeria_crud.Model.Ingredient;
import org.lessons.java.spring_la_mia_pizzeria_crud.Model.Pizza;
import org.lessons.java.spring_la_mia_pizzeria_crud.Repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
@Service
public class IngredientService {
    @Autowired
    private IngredientRepository ingredientRepository;

    public List<Ingredient> findAll() {
        return ingredientRepository.findAll();
    }
    public Ingredient getById(Integer id) {
        Optional<Ingredient> ingredientAttempt = ingredientRepository.findById(id);
        if (ingredientAttempt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ingredient not found");
        }
        return ingredientAttempt.get();
    }

    public Ingredient create(Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }
    public Ingredient update(Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }

    public void delete(Ingredient ingredient) {

    for (Pizza pizza :ingredient.getPizzas()){
        pizza.getIngredients().remove(ingredient);
    }
        ingredientRepository.delete(ingredient);
    }
    
    public void deleteById(Integer id) {
        Ingredient ingredient = getById(id);

    for (Pizza pizza :ingredient.getPizzas()){
        pizza.getIngredients().remove(ingredient);
    }

        ingredientRepository.delete(ingredient);
    }

}
