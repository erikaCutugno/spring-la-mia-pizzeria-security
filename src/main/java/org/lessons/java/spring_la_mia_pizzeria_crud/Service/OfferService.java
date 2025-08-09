package org.lessons.java.spring_la_mia_pizzeria_crud.Service;

import java.util.Optional;

import org.lessons.java.spring_la_mia_pizzeria_crud.Model.Offer;
import org.lessons.java.spring_la_mia_pizzeria_crud.Repository.OfferRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
@Service
public class OfferService {
    @Autowired
    private OfferRepository offerRepository;

    public Offer create(Offer offer) {
        return offerRepository.save(offer);
    }
    public Offer update(Offer offer) {
        return offerRepository.save(offer);
    }

    public Offer getById(Integer id) {
        Optional<Offer> offerAttempt = offerRepository.findById(id);
        if (offerAttempt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Offer not found");
        }
        return offerAttempt.get();
    }

}
