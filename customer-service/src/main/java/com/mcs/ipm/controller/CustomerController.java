package com.mcs.ipm.controller;

import com.mcs.ipm.config.CustomerConfiguration;
import com.mcs.ipm.entity.Customer;
import com.mcs.ipm.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
public class CustomerController {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CustomerConfiguration customerConfiguration;

    @Autowired
    private RabbitTemplate template;

    @Autowired
    TopicExchange topicExchange;

    @Value("${customer.rabbitmq.routingkey}")
    private String routingKey;

    @GetMapping(value = "/customers", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Customer> getAllCustomers() {
        log.info("Inside getAllCustomers");
        List<Customer> customers = customerRepository.findAll();
        log.info(customers.size()+" Customer(s) found!");
        return customers;
    }

    @GetMapping(value = "/customers/{customerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Customer getCustomerDetails(@PathVariable Long customerId) {
        log.info("Inside getCustomerDetails");
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        if(!optionalCustomer.isPresent())
            throw new RuntimeException(customerId+" - Customer not found");
        log.info(customerId+" Customer found!");
        return optionalCustomer.get();
    }

    @PostMapping("/customers")
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
        Customer newCustomer = customerRepository.saveAndFlush(customer);
        log.info(newCustomer.getId()+" Customer added!");
        template.convertAndSend(topicExchange.getName(), routingKey, newCustomer);
        return ResponseEntity.ok(customer);

    }

    @PutMapping("/customers/{customerId}")
    public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer, @PathVariable long customerId) {

        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        if (!optionalCustomer.isPresent())
            return ResponseEntity.notFound().build();

        customer.setId(customerId);
        customerRepository.save(customer);

        log.info(customerId+" Customer updated!");
        return ResponseEntity.ok(customer);
    }

    @DeleteMapping("/customers/{customerId}")
    public void removeCustomer(@PathVariable long customerId) {
        customerRepository.deleteById(customerId);
        log.info(customerId+" Customer removed!");
    }

    @GetMapping(value="/customers/default-customer", produces = MediaType.APPLICATION_JSON_VALUE)
    public Customer getDefaultCustomer() {
        Customer defaultCustomer = new Customer(customerConfiguration.getId(), customerConfiguration.getFirstName(), customerConfiguration.getLastName(), customerConfiguration.getEmail());
        return defaultCustomer;
    }
}
