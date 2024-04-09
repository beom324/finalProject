package com.example.FP.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class OrderDetails {
    @Id@GeneratedValue
    @Column(name = "order_detail_id")
    private Long id;

    private int ingredientPrice;

    @ManyToOne
    @JoinColumn(name = "orders_id")
    private Orders ordersDetail;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member ordersDetailsMember;


    @ManyToOne
    @JoinColumn(name = "ingredient_id")
    private Ingredient ordersIngredient;



    public OrderDetails(int ingredientPrice, Orders ordersDetail, Member orderDetailsMember, Ingredient ordersIngredient) {

        this.ingredientPrice = ingredientPrice;
        this.ordersDetail = ordersDetail;
        this.ordersDetailsMember = orderDetailsMember;
        this.ordersIngredient = ordersIngredient;

    }
}
