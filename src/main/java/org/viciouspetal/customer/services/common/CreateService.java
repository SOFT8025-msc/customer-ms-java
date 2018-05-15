package org.viciouspetal.customer.services.common;

import org.viciouspetal.customer.model.BaseObject;

public interface CreateService<T extends BaseObject> {
    T create(T object);
}
