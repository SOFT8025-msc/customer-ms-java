package org.viciouspetal.customer.services;

import org.viciouspetal.customer.model.ShippingAddress;
import org.viciouspetal.customer.services.common.CreateService;
import org.viciouspetal.customer.services.common.DeleteService;
import org.viciouspetal.customer.services.common.ListService;
import org.viciouspetal.customer.services.common.UpdateService;

public interface ShippingAddressService extends CreateService<ShippingAddress>, UpdateService<ShippingAddress>, ListService<ShippingAddress>, DeleteService<ShippingAddress> {
}
