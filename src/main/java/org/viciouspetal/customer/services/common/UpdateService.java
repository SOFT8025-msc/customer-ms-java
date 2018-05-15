package org.viciouspetal.customer.services.common;

import org.viciouspetal.customer.model.BaseObject;

public interface UpdateService<T extends BaseObject> {
    T update(T object);
}
