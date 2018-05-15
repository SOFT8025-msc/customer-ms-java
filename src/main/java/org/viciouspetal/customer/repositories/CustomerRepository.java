package org.viciouspetal.customer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.viciouspetal.customer.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {
}
