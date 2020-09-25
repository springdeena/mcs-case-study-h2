package com.mcs.ipm.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerSos {

    @Id
    private Long custId;

    private String custFirstName;

    private String custLastName;

    private String custEmail;
}
