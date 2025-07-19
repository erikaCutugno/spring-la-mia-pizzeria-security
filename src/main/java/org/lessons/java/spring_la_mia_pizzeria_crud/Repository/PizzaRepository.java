package org.lessons.java.spring_la_mia_pizzeria_crud.Repository;

import java.util.List;

import org.lessons.java.spring_la_mia_pizzeria_crud.Model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PizzaRepository extends JpaRepository<Pizza, Integer> {
public List<Pizza> findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String name, String description);
    
    // Altri metodi di ricerca personalizzati possono essere aggiunti qui
    
}
