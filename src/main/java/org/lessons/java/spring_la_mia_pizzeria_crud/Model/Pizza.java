package org.lessons.java.spring_la_mia_pizzeria_crud.Model;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
@Table(name = "pizzas")
public class Pizza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Il nome della pizza non può essere vuoto")
    @Size(min = 2, max = 50, message = "Il nome della pizza deve essere tra 2 e 50 caratteri")
    private String name;

    @NotBlank(message = "La descrizione non può essere vuota")
    @Size(min = 5, max = 500, message = "La descrizione deve essere tra 5 e 500 caratteri")
    private String description;

    @NotBlank(message = "L'URL della pizza non può essere vuota")
    @Pattern(regexp = "^(http(s?):)([/|.|\\w|\\s|-])*\\.(?:jpg|jpeg|png)$",
             message = "L'URL deve essere un link valido a un'immagine (jpg, jpeg, png)")
    private String url;

    @NotNull(message = "Il prezzo della pizza non può essere nullo")
    @DecimalMin(value = "0.01", message = "Il prezzo deve essere maggiore di 0")
    private BigDecimal price;

    @OneToMany(mappedBy = "pizza")
    private List<Offer> offers;

    @ManyToMany
    @JoinTable(name = "ingredient_pizza",
               joinColumns = @JoinColumn(name = "pizza_id"),
               inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
    private List<Ingredient> ingredients;

    public List<Ingredient> getIngredients() {
        return ingredients;
    }
    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
    public List<Offer> getOffers() {
        return offers;
    }
    public void setOffers(List<Offer> offers) {
        this.offers = offers;
    }

     public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    @Override
    public String toString() {
        return String.format("%s %s %s.2f", this.name, this.description, this.price);
    }
}
