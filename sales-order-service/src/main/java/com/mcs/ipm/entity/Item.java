package com.mcs.ipm.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {

    private Long id;

    private String name;

    private String description;

    private BigDecimal price;

}
