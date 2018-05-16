package org.viciouspetal.customer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.viciouspetal.customer.model.Customer;
import org.viciouspetal.customer.services.CustomerService;

import java.util.List;

@RestController
@RequestMapping(value = "/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<Customer> getList() {
        return customerService.list();
    }


    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Customer create(@RequestBody Customer customer){
        return customerService.create(customer);
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public Customer update(@RequestParam String id, @RequestBody Customer object) {
        return customerService.update(object);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public void delete(@RequestParam String id) {
        customerService.delete(id);
    }
}
