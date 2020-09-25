package com.mcs.ipm.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@SequenceGenerator(name="item_seq", initialValue=5, allocationSize=100)
public class Item {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="item_seq")
    private Long id;

    private String name;

    private String description;

    private BigDecimal price;

}
