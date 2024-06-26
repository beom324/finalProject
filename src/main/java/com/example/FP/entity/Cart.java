package com.example.FP.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cart {
    @Id@GeneratedValue
    @Column(name = "cart_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member cartMember;

    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private Recipe cartRecipe;
    @ManyToOne
    @JoinColumn(name = "ingredient_id")
    private Ingredient cartIngredient;

    public Cart(Member cartMember, Recipe cartRecipe, Ingredient cartIngredient) {
        this.cartMember = cartMember;
        this.cartRecipe = cartRecipe;
        this.cartIngredient = cartIngredient;
    }

    public Cart(Member cartMember, Ingredient cartIngredient) {
        this.cartMember = cartMember;
        this.cartIngredient = cartIngredient;
    }
}
