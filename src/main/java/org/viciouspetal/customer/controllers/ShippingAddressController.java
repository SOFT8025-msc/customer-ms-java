package org.viciouspetal.customer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.viciouspetal.customer.model.ShippingAddress;
import org.viciouspetal.customer.services.ShippingAddressService;

import java.util.List;

@CrossOrigin(origins = "https://assignment-ui.cfapps.io")
@RestController
@RequestMapping(value = "/shippingAddresses")
public class ShippingAddressController {
    @Autowired
    private ShippingAddressService shippingAddressService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<ShippingAddress> getList() {
        return shippingAddressService.list();
    }


    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ShippingAddress create(@RequestBody ShippingAddress shippingAddress){
        return shippingAddressService.create(shippingAddress);
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public ShippingAddress update(@RequestParam String id, @RequestBody ShippingAddress shippingAddress) {
        return shippingAddressService.update(shippingAddress);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public void delete(@RequestParam String id) {
        shippingAddressService.delete(id);
    }
}
