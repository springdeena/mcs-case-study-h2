package com.mcs.ipm.mq;

import com.mcs.ipm.entity.Customer;
import com.mcs.ipm.entity.CustomerSos;
import com.mcs.ipm.repository.CustomerSosRepository;
import com.mcs.ipm.service.CustomerService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;

public class CustomerSubscriber {

    @Autowired
    CustomerService customerService;

    @Autowired
    CustomerSosRepository customerSosRepository;

    @RabbitListener(queues = "#{customerQueue.name}")
    public void subscribeCustomer(Customer inCustomer) {
        System.out.println("Message read from myQueue : " + inCustomer.toString());
        //Customer customer = customerService.getCustomerDetails(inCustomer.getId());
        System.out.println(inCustomer.toString());
        CustomerSos customerSos = new CustomerSos(inCustomer.getId(), inCustomer.getFirstName(), inCustomer.getLastName(), inCustomer.getEmail());
        customerSosRepository.saveAndFlush(customerSos);
    }
}
