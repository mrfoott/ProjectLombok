package guru.springframework.spring6restmvc.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
public class CustomerControllerTest {

    @Autowired
    CustomerController customerController;

    @Test
    void getCustomerById() {
        System.out.println(customerController.getCustomerById(UUID.randomUUID()));
    }

}
