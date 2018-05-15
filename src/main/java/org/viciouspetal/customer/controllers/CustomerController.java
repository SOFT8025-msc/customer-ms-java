package org.viciouspetal.customer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.viciouspetal.customer.model.Customer;
import org.viciouspetal.customer.services.CustomerService;

@RestController
@RequestMapping(value = "/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Customer create(@RequestBody Customer customer){
        return customerService.create(customer);
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public Customer update(@RequestParam String id, @RequestBody Customer object) {
        return customerService.update(object);
    }
}
