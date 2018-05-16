package org.viciouspetal.customer.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.viciouspetal.customer.model.Customer;
import org.viciouspetal.customer.model.ShippingAddress;
import org.viciouspetal.customer.repositories.ShippingAddressRepository;
import org.viciouspetal.customer.services.CustomerService;
import org.viciouspetal.customer.services.ShippingAddressService;

import java.util.List;
import java.util.Objects;

@Service
public class ShippingAddressServiceImpl implements ShippingAddressService {
    @Autowired
    private ShippingAddressRepository shippingAddressRepository;

    @Override
    public ShippingAddress create(ShippingAddress customer) {
        return shippingAddressRepository.save(customer);
    }

    @Override
    public ShippingAddress update(ShippingAddress address) {
        ShippingAddress existing = shippingAddressRepository.getOne(address.getId());
        existing.setLine1(address.getLine1());
        existing.setLine2(address.getLine2());
        existing.setCity(address.getCity());
        existing.setCounty(address.getCounty());
        existing.setCountry(address.getCountry());
        existing.setPostalCode(address.getPostalCode());

        return shippingAddressRepository.save(existing);
    }

    @Override
    public List<ShippingAddress> list() {
        return shippingAddressRepository.findAll();
    }

    @Override
    public void delete(ShippingAddress customer) {
        shippingAddressRepository.delete(customer);
    }

    @Override
    public void delete(String id) {
        ShippingAddress toBeDeleted = shippingAddressRepository.getOne(id);

        if(Objects.nonNull(toBeDeleted)){
            delete(toBeDeleted);
        }

    }
}
