package com.mcs.ipm.controller;

import com.mcs.ipm.dto.Order;
import com.mcs.ipm.entity.*;
import com.mcs.ipm.repository.CustomerSosRepository;
import com.mcs.ipm.repository.OrderLineItemRepository;
import com.mcs.ipm.repository.SalesOrderRepository;
import com.mcs.ipm.service.CustomerService;
import com.mcs.ipm.service.ItemService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.partitioningBy;

@RestController
@Slf4j
public class SalesOrderController {

    private static final Logger logger = LoggerFactory.getLogger(SalesOrderController.class);

    @Autowired
    SalesOrderRepository salesOrderRepository;

    @Autowired
    CustomerSosRepository customerSosRepository;

    @Autowired
    OrderLineItemRepository orderLineItemRepository;

    @Autowired
    CustomerService customerService;

    @Autowired
    ItemService itemService;

    @RequestMapping(value = "/orders", method = RequestMethod.POST,
            consumes = "application/json")
    @ResponseBody
    public ResponseEntity<Object> saveOrders(@RequestBody Order order){
        Optional<CustomerSos> optionalCustomerSos = customerSosRepository.findById(order.getCustomerId());
        if(!optionalCustomerSos.isPresent()){
            throw new RuntimeException(order.getCustomerId()+" - Customer not found");
        }
        List<Item> orderItems = new ArrayList<>();
        for(String itemName: order.getItemNames()) {
            Item item = itemService.getItemByName(itemName);
            if(item == null){
                throw new RuntimeException(itemName+" - Item not found");
            }
            orderItems.add(item);
        }
        BigDecimal totalPrice = orderItems.stream()
                .map(orderItem -> orderItem.getPrice())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        SalesOrder inputSalesOrder = new SalesOrder(order.getOrderDate(), order.getCustomerId(), order.getOrderDescription(), totalPrice);
        SalesOrder outputSalesOrder = salesOrderRepository.saveAndFlush(inputSalesOrder);

        Map<String, Long> itemCount = orderItems.stream().map(item -> item.getName()).collect(Collectors.groupingBy(string -> string, Collectors.counting()));
        List<OrderLineItem> inputLineItems = new ArrayList<>();
        itemCount.forEach((name, count) -> {
            inputLineItems.add(new OrderLineItem(name, count.intValue(), outputSalesOrder.getId()));
        });

        List<OrderLineItem> outputLineItems = orderLineItemRepository.saveAll(inputLineItems);
        outputLineItems.forEach(item -> System.out.println(item.getId()));

        return new ResponseEntity<>(outputSalesOrder.getId() +" Order created successfully", HttpStatus.OK);
    }

    @GetMapping(value = "/orders", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<SalesOrder> getAllSalesOrders() {
        logger.info("Inside getAllSalesOrders");
        List<SalesOrder> salesOrders = salesOrderRepository.findAll();
        log.info(salesOrders.size()+" Sales Orders(s) found!");
        return salesOrders;
    }

    @GetMapping(value="/orders/{orderId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public SalesOrder getOrderById(@PathVariable Long orderId) {
        logger.info("Inside getOrderById");
        Optional<SalesOrder> salesOrder = salesOrderRepository.findById(orderId);
        if(!salesOrder.isPresent())
            throw new RuntimeException(orderId+" - Order not found");
        return salesOrder.get();
    }

    @GetMapping(value="/default-customer", produces = MediaType.APPLICATION_JSON_VALUE)
    public Customer getDefaultCustomer() {
        Customer customer = customerService.getDefaultCustomer();
        return customer;
    }

    @GetMapping(value="/fault-item", produces = MediaType.APPLICATION_JSON_VALUE)
    public Item getFaultItem() {
        Item item = itemService.getItemByName("TV");
        return item;
    }
}
