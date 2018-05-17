package org.viciouspetal.customer.services.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.viciouspetal.customer.model.Customer;
import org.viciouspetal.customer.model.ShippingAddress;
import org.viciouspetal.customer.repositories.CustomerRepository;
import org.viciouspetal.customer.services.common.ListService;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceImplTest {

    @Mock
    private CustomerRepository customerRepositoryMock;

    @InjectMocks
    private CustomerServiceImpl customerService = new CustomerServiceImpl();

    @Test
    public void create_when_validCustomerProvided_then_customerIsCreated() {
        // Arrange
        Customer toBeCreated = getDefaultCustomer();

        when(customerRepositoryMock.save(toBeCreated)).thenReturn(toBeCreated);

        // Act
        customerService.create(toBeCreated);

        // Assert
        ArgumentCaptor<Customer> customerCaptor = ArgumentCaptor.forClass(Customer.class);
        verify(customerRepositoryMock).save(customerCaptor.capture());
        Customer capturedCustomer = customerCaptor.getValue();
        assertThat(capturedCustomer.getFirstName(), is(toBeCreated.getFirstName()));
        assertThat(capturedCustomer.getLastName(), is(toBeCreated.getLastName()));
        assertThat(capturedCustomer.getTitle(), is(toBeCreated.getTitle()));
    }



    @Test
    public void update_when_customerExists_Then_customerIsUpdated() {
        // Arrange
        Customer existing = getDefaultCustomer();
        existing.setId(UUID.randomUUID().toString());

        Customer toBeSaved = new Customer();
        toBeSaved.setId(existing.getId());
        toBeSaved.setTitle("Master");
        toBeSaved.setFirstName("Anakin");
        toBeSaved.setLastName("Skywalker");

        when(customerRepositoryMock.getOne(toBeSaved.getId())).thenReturn(existing);


        // Act
       customerService.update(toBeSaved);

        // Assert
        ArgumentCaptor<Customer> customerCaptor = ArgumentCaptor.forClass(Customer.class);

        verify(customerRepositoryMock).save(customerCaptor.capture());
        Customer capturedCustomer = customerCaptor.getValue();
        assertThat(capturedCustomer.getFirstName(), is(toBeSaved.getFirstName()));
        assertThat(capturedCustomer.getLastName(), is(toBeSaved.getLastName()));
        assertThat(capturedCustomer.getTitle(), is(toBeSaved.getTitle()));
    }

    @Test
    public void list_when_customersExist_then_customersAreReturned(){
        // Arrange
        List<Customer> customers = Arrays.asList(getDefaultCustomer(), getDefaultCustomer());
        when(customerRepositoryMock.findAll()).thenReturn(customers);

        // Act
        List<Customer> result = customerService.list();

        // Assert
        assertThat(result, hasSize(2));
        assertThat(result.get(0).getFirstName(), is(getDefaultCustomer().getFirstName()));
        assertThat(result.get(0).getLastName(), is(getDefaultCustomer().getLastName()));
        assertThat(result.get(0).getTitle(), is(getDefaultCustomer().getTitle()));
        assertThat(result.get(1).getFirstName(), is(getDefaultCustomer().getFirstName()));
        assertThat(result.get(1).getLastName(), is(getDefaultCustomer().getLastName()));
        assertThat(result.get(1).getTitle(), is(getDefaultCustomer().getTitle()));
    }

    @Test
    public void delete_when_idToBeDeletedExists_then_customerForThatIdIsDeleted() {
        // Arrange
        Customer toBeDeleted = getDefaultCustomer();
        toBeDeleted.setId(UUID.randomUUID().toString());
        when(customerRepositoryMock.getOne(toBeDeleted.getId())).thenReturn(toBeDeleted);

        // Act
        customerService.delete(toBeDeleted.getId());

        // Assert
        verify(customerRepositoryMock).getOne(toBeDeleted.getId());
        verify(customerRepositoryMock).delete(toBeDeleted);
    }

    @Test
    public void delete_when_idToBeDeletedDoesntExist_then_noBrandIsDeleted() {
        when(customerRepositoryMock.getOne("id")).thenReturn(null);

        customerService.delete("id");

        verify(customerRepositoryMock).getOne("id");
        verify(customerRepositoryMock, never()).delete(Mockito.any(Customer.class));
    }

    @Test
    public void delete_when_CustomerToBeDeletedExists_then_customerIsDeleted(){
        // Arrange
        Customer toBeDeleted = getDefaultCustomer();

        // Act
        customerService.delete(toBeDeleted);

        // Assert
        ArgumentCaptor<Customer> customerArgumentCaptor = ArgumentCaptor.forClass(Customer.class);
        verify(customerRepositoryMock).delete(customerArgumentCaptor.capture());
        Customer capturedCustomer = customerArgumentCaptor.getValue();
        assertThat(capturedCustomer.getFirstName(), is(toBeDeleted.getFirstName()));
        assertThat(capturedCustomer.getLastName(), is(toBeDeleted.getLastName()));
        assertThat(capturedCustomer.getTitle(), is(toBeDeleted.getTitle()));

        verify(customerRepositoryMock).delete(toBeDeleted);
    }

    private Customer getDefaultCustomer() {
        Customer toBeCreated = new Customer();
        toBeCreated.setFirstName("Darth");
        toBeCreated.setLastName("Vader");
        toBeCreated.setTitle("Lord");
        toBeCreated.setShippingAddresses(Collections.singletonList(new ShippingAddress()));
        return toBeCreated;
    }
}