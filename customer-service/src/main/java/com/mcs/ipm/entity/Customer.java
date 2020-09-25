package com.mcs.ipm.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(name="customer_seq", initialValue=4, allocationSize=100)
public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="customer_seq")
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

}
