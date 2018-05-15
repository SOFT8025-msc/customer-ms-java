package org.viciouspetal.customer.services.common;

import org.viciouspetal.customer.model.BaseObject;

import java.util.List;


public interface ListService<T extends BaseObject> {
    List<T> list();
}
