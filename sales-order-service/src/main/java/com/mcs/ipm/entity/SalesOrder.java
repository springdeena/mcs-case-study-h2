package com.mcs.ipm.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@SequenceGenerator(name="sales_order_seq", allocationSize=100)
public class SalesOrder {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="sales_order_seq")
    private Long id;

    private Date orderDate;

    private Long custId;

    private String orderDescription;

    private BigDecimal totalPrice;

    public SalesOrder() {

    }

    public SalesOrder(Date orderDate, Long custId, String orderDescription, BigDecimal totalPrice) {
        this.orderDate = orderDate;
        this.custId = custId;
        this.orderDescription = orderDescription;
        this.totalPrice = totalPrice;
    }

}
