package org.viciouspetal.customer.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.viciouspetal.customer.model.Customer;
import org.viciouspetal.customer.repositories.CustomerRepository;
import org.viciouspetal.customer.services.CustomerService;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer create(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer update(Customer customer) {
        Customer existing = customerRepository.getOne(customer.getId());
        existing.setTitle(customer.getTitle());
        existing.setFirstName(customer.getFirstName());
        existing.setLastName(customer.getLastName());
        existing.setShippingAddresses(customer.getShippingAddresses());

        return customerRepository.save(existing);
    }

    @Override
    public List<Customer> list() {
        return customerRepository.findAll();
    }
}
