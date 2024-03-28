package com.example.FP.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Orders {
    @Id
    @GeneratedValue
    @Column(name = "orders_id")
    private Long id;

    private LocalDateTime orders_date;
    private String orders_receiver;
    private String orders_receiver_addr;
    private String orders_receiver_phone;
    private int orders_total_price;
    private int orders_sale_price;
    private int orders_used_point;
    private String orders_request;
    private String orders_non_member_name;
    private String orders_non_member_phone;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member orders_member;

    @Builder.Default
    @OneToMany(mappedBy = "orders_detail")
    private List<OrderDetails> order_ordersdetail_list = new ArrayList<>();

    public Orders(LocalDateTime orders_date, String orders_receiver, String orders_receiver_addr, String orders_receiver_phone, int orders_total_price, int orders_sale_price, int orders_used_point, String orders_request, String orders_non_member_name, String orders_non_member_phone, Member orders_member, List<OrderDetails> order_ordersdetail_list) {
        this.orders_date = orders_date;
        this.orders_receiver = orders_receiver;
        this.orders_receiver_addr = orders_receiver_addr;
        this.orders_receiver_phone = orders_receiver_phone;
        this.orders_total_price = orders_total_price;
        this.orders_sale_price = orders_sale_price;
        this.orders_used_point = orders_used_point;
        this.orders_request = orders_request;
        this.orders_non_member_name = orders_non_member_name;
        this.orders_non_member_phone = orders_non_member_phone;
        this.orders_member = orders_member;
        this.order_ordersdetail_list = order_ordersdetail_list;
    }
}
