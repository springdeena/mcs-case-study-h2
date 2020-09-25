package com.mcs.ipm.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Order {

    private String orderDescription;
    private Date orderDate;
    private Long customerId;
    private List<String> itemNames;
}
