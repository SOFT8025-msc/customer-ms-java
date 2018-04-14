package org.viciouspetal.customer;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.viciouspetal.customer.controllers.CustomerController;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ControllerSmokeTest {

    @Autowired
    private CustomerController customerController;

    @Test
    public void contextLoads() {
        assertThat(customerController).isNotNull();
    }
}