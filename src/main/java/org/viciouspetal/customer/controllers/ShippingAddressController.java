package org.viciouspetal.customer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.viciouspetal.customer.model.ShippingAddress;
import org.viciouspetal.customer.services.ShippingAddressService;

import java.util.List;

//@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/shippingAddresses")
public class ShippingAddressController {
    @Autowired
    private ShippingAddressService shippingAddressService;

    @CrossOrigin(origins = "https://assignment-ui.cfapps.io")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<ShippingAddress> getList() {
        return shippingAddressService.list();
    }


    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ShippingAddress create(@RequestBody ShippingAddress shippingAddress){
        return shippingAddressService.create(shippingAddress);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public ShippingAddress update(@RequestParam String id, @RequestBody ShippingAddress shippingAddress) {
        return shippingAddressService.update(shippingAddress);
    }

    @CrossOrigin(origins = {"http://localhost:4200", "https://assignment-ui.cfapps.io/"})
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public void delete(@RequestParam String id) {
        shippingAddressService.delete(id);
    }
}
