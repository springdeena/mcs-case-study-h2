package com.mcs.ipm.service;

import com.mcs.ipm.entity.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "customer-service")
public interface CustomerService {

    @GetMapping("/customers/{customerId}")
    public Customer getCustomerDetails(@PathVariable Long customerId);
}
