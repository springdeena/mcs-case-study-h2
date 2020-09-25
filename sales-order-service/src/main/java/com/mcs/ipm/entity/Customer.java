package com.mcs.ipm.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer implements Serializable {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

}
