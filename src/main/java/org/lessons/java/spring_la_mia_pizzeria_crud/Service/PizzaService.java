package org.lessons.java.spring_la_mia_pizzeria_crud.Service;

import java.util.List;
import java.util.Optional;

import org.lessons.java.spring_la_mia_pizzeria_crud.Model.Offer;
import org.lessons.java.spring_la_mia_pizzeria_crud.Model.Pizza;
import org.lessons.java.spring_la_mia_pizzeria_crud.Repository.OfferRepository;
import org.lessons.java.spring_la_mia_pizzeria_crud.Repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class PizzaService {
    @Autowired
    private PizzaRepository pizzaRepository;
    @Autowired
    private OfferRepository offerRepository;

public List<Pizza> findAll(){
    return pizzaRepository.findAll();
}
public Pizza getById(Integer id) {
    Optional<Pizza> pizzaAttempt = pizzaRepository.findById(id);
    if(pizzaAttempt.isEmpty()){
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pizza not found");
    }
    return pizzaAttempt.get();
}

public List<Pizza> findByNameOrDescription(String query) {
    return pizzaRepository.findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(query, query);
}

public Optional<Pizza> findById(Integer id) {
    return pizzaRepository.findById(id);
}
public Pizza create(Pizza pizza) {
    return pizzaRepository.save(pizza); 
}

public Pizza update (Pizza pizza){
    return pizzaRepository.save(pizza);
}

public void delete(Pizza pizza) {
      for (Offer offer : pizza.getOffers()) {

        offerRepository.delete(offer); 
    }
    pizzaRepository.delete(pizza);
}

public void deleteById(Integer id) {
    Pizza pizza = getById(id);
      for (Offer offer : pizza.getOffers()) {

        offerRepository.delete(offer);
    }
    pizzaRepository.delete(pizza);
}

public Boolean existsById(Integer id) {
    return pizzaRepository.existsById(id);
}

public Boolean exists (Pizza pizza){
    return existsById(pizza.getId());
}
}