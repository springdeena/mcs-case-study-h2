package com.mcs.ipm.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class OrderLineItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String itemName;

    private Integer itemQuantity;

    private Long orderId;

    public OrderLineItem() {
    }

    public OrderLineItem(String itemName, Integer itemQuantity, Long orderId) {
        this.itemName = itemName;
        this.itemQuantity = itemQuantity;
        this.orderId = orderId;
    }
}
