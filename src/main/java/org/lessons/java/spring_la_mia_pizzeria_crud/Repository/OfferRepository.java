package org.lessons.java.spring_la_mia_pizzeria_crud.Repository;

import org.lessons.java.spring_la_mia_pizzeria_crud.Model.Offer;
import org.springframework.data.jpa.repository.JpaRepository;



public interface OfferRepository extends JpaRepository<Offer, Integer> {

    

}
