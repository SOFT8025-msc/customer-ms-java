package org.viciouspetal.customer.services;

import org.viciouspetal.customer.model.Customer;
import org.viciouspetal.customer.services.common.CreateService;
import org.viciouspetal.customer.services.common.ListService;
import org.viciouspetal.customer.services.common.UpdateService;

public interface CustomerService extends CreateService<Customer>, UpdateService<Customer>, ListService<Customer> {
}
