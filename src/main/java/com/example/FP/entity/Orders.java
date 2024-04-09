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

    private LocalDateTime ordersDate;
    private String ordersReceiver;
    private String ordersReceiverAddr;
    private String ordersReceiverPhone;
    private int ordersTotalPrice;
    private int ordersSalePrice;
    private int ordersUsedPoint;
    private String ordersRequest;
    private String ordersContent;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member ordersMember;


    @ManyToOne
    @JoinColumn(name = "order_state_id")
    private OrderState ordersOrderState;

    @Builder.Default
    @OneToMany(mappedBy = "ordersDetail")
    private List<OrderDetails> orderOrdersdetailList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "pointOrders")
    private List<Point> orderPointList = new ArrayList<>();



    public Orders(LocalDateTime ordersDate,
                  String ordersReceiver,
                  String ordersReceiverAddr,
                  String ordersReceiverPhone,
                  int ordersTotalPrice,
                  int ordersSalePrice,
                  Integer ordersUsedPoint,
                  String ordersRequest,
                  Member ordersMember,
                  List<OrderDetails> orderOrdersdetailList,
                  OrderState ordersOrderState,
                  List<Point> orderPointList,
                  String ordersContent

    ) {

        this.ordersDate = ordersDate;
        this.ordersReceiver = ordersReceiver;
        this.ordersReceiverAddr = ordersReceiverAddr;
        this.ordersReceiverPhone = ordersReceiverPhone;
        this.ordersTotalPrice = ordersTotalPrice;
        this.ordersSalePrice = ordersSalePrice;
        this.ordersUsedPoint = ordersUsedPoint;
        this.ordersRequest = ordersRequest;
        this.ordersMember = ordersMember;
        this.orderOrdersdetailList = orderOrdersdetailList;
        this.ordersOrderState = ordersOrderState;
        this.orderPointList = orderPointList;
        this.ordersContent = ordersContent;

    }
}
