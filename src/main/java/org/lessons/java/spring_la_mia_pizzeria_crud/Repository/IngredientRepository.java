package org.lessons.java.spring_la_mia_pizzeria_crud.Repository;

import org.lessons.java.spring_la_mia_pizzeria_crud.Model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, Integer> {
   
    
}
