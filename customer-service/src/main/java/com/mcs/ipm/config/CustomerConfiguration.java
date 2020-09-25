package com.mcs.ipm.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix="customer-service")
@Component
@Data
public class CustomerConfiguration {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
}
