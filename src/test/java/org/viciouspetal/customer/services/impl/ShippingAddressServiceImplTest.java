package org.viciouspetal.customer.services.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.viciouspetal.customer.model.ShippingAddress;
import org.viciouspetal.customer.repositories.ShippingAddressRepository;
import org.viciouspetal.customer.services.ShippingAddressService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ShippingAddressServiceImplTest {

    @Mock
    private ShippingAddressRepository shippingAddressRepositoryMock;

    @InjectMocks
    private ShippingAddressService shippingAddressService = new ShippingAddressServiceImpl();

    @Test
    public void create_when_validShippingAddressProvided_then_objectIsPersisted() {
        // Arrange
        ShippingAddress toBeSaved = getDefaultShippingAddress();
        when(shippingAddressRepositoryMock.save(toBeSaved)).thenReturn(new ShippingAddress());

        // Act
        ShippingAddress result = shippingAddressService.create(toBeSaved);

        // Assert
        assertThat(result, notNullValue());
        ArgumentCaptor<ShippingAddress> addressArgumentCaptor = ArgumentCaptor.forClass(ShippingAddress.class);
        verify(shippingAddressRepositoryMock).save(addressArgumentCaptor.capture());
        ShippingAddress shippingAddress = addressArgumentCaptor.getValue();
        assertThat(shippingAddress.getLine1(), is(toBeSaved.getLine1()));
        assertThat(shippingAddress.getLine2(), is(toBeSaved.getLine2()));
        assertThat(shippingAddress.getCity(), is(toBeSaved.getCity()));
        assertThat(shippingAddress.getCounty(), is(toBeSaved.getCounty()));
        assertThat(shippingAddress.getCountry(), is(toBeSaved.getCountry()));
        assertThat(shippingAddress.getPostalCode(), is(toBeSaved.getPostalCode()));
    }

    @Test
    public void update_when_validAddressProvided_then_objectIsUpdated() {
        // Arrange
        ShippingAddress existing = getDefaultShippingAddress();
        existing.setId(UUID.randomUUID().toString());

        ShippingAddress toBeSaved = new ShippingAddress();
        toBeSaved.setId(existing.getId());
        toBeSaved.setId(existing.getId());
        toBeSaved.setLine1("line1");
        toBeSaved.setLine2("line2");
        toBeSaved.setCity("city");
        toBeSaved.setCounty("county");
        toBeSaved.setCountry("country");
        toBeSaved.setPostalCode("123456");

        when(shippingAddressRepositoryMock.getOne(existing.getId())).thenReturn(existing);

        // Act
        shippingAddressService.update(toBeSaved);

        // Assert
        ArgumentCaptor<ShippingAddress> addressArgumentCaptor = ArgumentCaptor.forClass(ShippingAddress.class);
        verify(shippingAddressRepositoryMock).save(addressArgumentCaptor.capture());
        ShippingAddress shippingAddress = addressArgumentCaptor.getValue();
        assertThat(shippingAddress.getLine1(), is(toBeSaved.getLine1()));
        assertThat(shippingAddress.getLine2(), is(toBeSaved.getLine2()));
        assertThat(shippingAddress.getCity(), is(toBeSaved.getCity()));
        assertThat(shippingAddress.getCounty(), is(toBeSaved.getCounty()));
        assertThat(shippingAddress.getCountry(), is(toBeSaved.getCountry()));
        assertThat(shippingAddress.getPostalCode(), is(toBeSaved.getPostalCode()));
    }

    @Test
    public void list_when_addressesExist_then_addressesAreReturned() {
        // Arrange
        ShippingAddress address1 = getDefaultShippingAddress();
        address1.setId(UUID.randomUUID().toString());
        ShippingAddress address2 = new ShippingAddress();
        address2.setId(UUID.randomUUID().toString());
        address2.setLine1("line1");
        address2.setLine2("line2");
        address2.setCity("city");
        address2.setCounty("county");
        address2.setCountry("country");
        address2.setPostalCode("123456");
        List<ShippingAddress> addresses = Arrays.asList(address1, address2);

        when(shippingAddressRepositoryMock.findAll()).thenReturn(addresses);

        // Act
        List<ShippingAddress> results = shippingAddressService.list();

        // Assert
        assertThat(results.get(0).getLine1(), is(address1.getLine1()));
        assertThat(results.get(0).getLine2(), is(address1.getLine2()));
        assertThat(results.get(0).getCity(), is(address1.getCity()));
        assertThat(results.get(0).getCounty(), is(address1.getCounty()));
        assertThat(results.get(0).getCountry(), is(address1.getCountry()));
        assertThat(results.get(0).getPostalCode(), is(address1.getPostalCode()));
        assertThat(results.get(0).getId(), is(address1.getId()));

        assertThat(results.get(1).getLine1(), is(address2.getLine1()));
        assertThat(results.get(1).getLine2(), is(address2.getLine2()));
        assertThat(results.get(1).getCity(), is(address2.getCity()));
        assertThat(results.get(1).getCounty(), is(address2.getCounty()));
        assertThat(results.get(1).getCountry(), is(address2.getCountry()));
        assertThat(results.get(1).getPostalCode(), is(address2.getPostalCode()));
        assertThat(results.get(1).getId(), is(address2.getId()));

        verify(shippingAddressRepositoryMock).findAll();
    }

    @Test
    public void delete_when_addressExists_then_objectIsDeleted() {
        // Arrange
        ShippingAddress toBeDeleted = getDefaultShippingAddress();

        // Act
        shippingAddressService.delete(toBeDeleted);

        // Assert
        ArgumentCaptor<ShippingAddress> addressArgumentCaptor = ArgumentCaptor.forClass(ShippingAddress.class);
        verify(shippingAddressRepositoryMock).delete(addressArgumentCaptor.capture());
        ShippingAddress shippingAddress = addressArgumentCaptor.getValue();
        assertThat(shippingAddress.getLine1(), is(toBeDeleted.getLine1()));
        assertThat(shippingAddress.getLine2(), is(toBeDeleted.getLine2()));
        assertThat(shippingAddress.getCity(), is(toBeDeleted.getCity()));
        assertThat(shippingAddress.getCounty(), is(toBeDeleted.getCounty()));
        assertThat(shippingAddress.getCountry(), is(toBeDeleted.getCountry()));
        assertThat(shippingAddress.getPostalCode(), is(toBeDeleted.getPostalCode()));
        verify(shippingAddressRepositoryMock).delete(toBeDeleted);
    }

    @Test
    public void delete_when_idToBeDeletedDoesntExist_then_noAddressIsDeleted() {
        // Arrange
        when(shippingAddressRepositoryMock.getOne("id")).thenReturn(null);

        shippingAddressService.delete("id");

        verify(shippingAddressRepositoryMock).getOne("id");
        verify(shippingAddressRepositoryMock, never()).delete(Mockito.any(ShippingAddress.class));
    }
    @Test
    public void delete_when_idToBeDeletedExists_then_addressForThatIdIsDeleted() {
        // Arrange
        ShippingAddress toBeDeleted = getDefaultShippingAddress();
        toBeDeleted.setId(UUID.randomUUID().toString());
        when(shippingAddressRepositoryMock.getOne(toBeDeleted.getId())).thenReturn(toBeDeleted);

        // Act
        shippingAddressService.delete(toBeDeleted.getId());

        // Assert
        verify(shippingAddressRepositoryMock).delete(toBeDeleted);
    }

    private ShippingAddress getDefaultShippingAddress() {
        ShippingAddress toBeSaved = new ShippingAddress();
        toBeSaved.setCity("Ballincollig");
        toBeSaved.setCountry("Ireland");
        toBeSaved.setCounty("Cork");
        toBeSaved.setLine1("1 Miller's Court");
        toBeSaved.setLine2("Old Quarter");
        toBeSaved.setPostalCode("TVN1X4");
        return toBeSaved;
    }
}