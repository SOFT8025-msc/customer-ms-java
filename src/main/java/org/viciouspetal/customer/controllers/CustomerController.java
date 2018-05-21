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

    @CrossOrigin(origins = "https://assignment-ui.cfapps.io")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<Customer> getList() {
        return customerService.list();
    }

    @CrossOrigin(origins = "https://assignment-ui.cfapps.io")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Customer create(@RequestBody Customer customer){
        return customerService.create(customer);
    }

    @CrossOrigin(origins = "https://assignment-ui.cfapps.io")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public Customer update(@PathVariable String id, @RequestBody Customer object) {
        return customerService.update(object);
    }

    @CrossOrigin(origins = "https://assignment-ui.cfapps.io")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable String id) {
        customerService.delete(id);
    }
}
