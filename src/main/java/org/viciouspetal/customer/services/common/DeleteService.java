package org.viciouspetal.customer.services.common;

import org.viciouspetal.customer.model.BaseObject;

public interface DeleteService<T extends BaseObject> {
    void delete(T object);

    void delete(String id);
}
