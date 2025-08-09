package org.lessons.java.spring_la_mia_pizzeria_crud.Controller;

import org.lessons.java.spring_la_mia_pizzeria_crud.Model.Offer;
import org.lessons.java.spring_la_mia_pizzeria_crud.Service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
@RequestMapping("/offers")
public class OfferController {

    @Autowired
    private OfferService offerService;

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("offer") Offer formOffer, BindingResult bindingResult, Model model) {
        if (formOffer.getStartDate() != null && formOffer.getEndDate() != null) {
            if (!formOffer.getEndDate().isAfter(formOffer.getStartDate())) {
                bindingResult.rejectValue("endDate", "invalid.endDate", "La data di fine deve essere successiva alla data di inizio");
            }
    }

    if (bindingResult.hasErrors()) {

        return "offers/create-or-edit";
    }
    model.addAttribute("offer", formOffer);

    offerService.create(formOffer);
    return "redirect:/pizzas/" + formOffer.getPizza().getId();
 
}
@GetMapping("/edit/{id}")
public String edit(@PathVariable("id") Integer id, Model model) {

    Offer offer = offerService.getById(id);
    model.addAttribute("offer", offer);
    model.addAttribute("edit", true); 
    return "offers/create-or-edit";

}
@PostMapping("/edit/{id}")
public String update(@Valid @ModelAttribute("offer") Offer formOffer,  BindingResult bindingResult, Model model) {
        if (formOffer.getStartDate() != null && formOffer.getEndDate() != null) {
        if (!formOffer.getEndDate().isAfter(formOffer.getStartDate())) {
            bindingResult.rejectValue("endDate", "invalid.endDate", "La data di fine deve essere successiva alla data di inizio");
        }
    }

    
    if (bindingResult.hasErrors()) {
        return "offers/create-or-edit";
    }
    
    model.addAttribute("offer", formOffer);

    offerService.update(formOffer);
    return "redirect:/pizzas/" + formOffer.getPizza().getId();
}
}
