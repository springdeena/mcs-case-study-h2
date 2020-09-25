package com.mcs.ipm.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@SequenceGenerator(name="order_item_seq", allocationSize=100)
public class OrderLineItem {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="order_item_seq")
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
