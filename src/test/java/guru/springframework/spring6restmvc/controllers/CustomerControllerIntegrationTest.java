package guru.springframework.spring6restmvc.controllers;

import guru.springframework.spring6restmvc.entities.Customer;
import guru.springframework.spring6restmvc.mappers.CustomerMapper;
import guru.springframework.spring6restmvc.model.CustomerDTO;
import guru.springframework.spring6restmvc.repositories.CustomerRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CustomerControllerIntegrationTest {

    @Autowired
    CustomerController customerController;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CustomerMapper customerMapper;

    @Test
    void updateExistingCustomer() {
        Customer customer = customerRepository.findAll().get(0);
        CustomerDTO customerDTO = customerMapper.customerToCustomerDto(customer);
        customerDTO.setId(null);
        customerDTO.setVersion(null);
        final String name = "BBBBBBBBBBBBBBBBBBBBBBBBBBBBB";
        customerDTO.setName(name);

        ResponseEntity responseEntity = customerController.updateById(customer.getId(), customerDTO);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(204));

        Customer updatedCustomer = customerRepository.findById(customer.getId()).get();
        assertThat(updatedCustomer.getName()).isEqualTo(name);

    }

    @Transactional
    @Rollback
    @Test
    void savedNewCustomerTest() {
        CustomerDTO customerDTO = CustomerDTO.builder()
                .name("AAAAAA")
                .build();

        ResponseEntity responseEntity = customerController.handlePost(customerDTO);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.valueOf(201));
        assertThat(responseEntity.getHeaders().getLocation()).isNotNull();

        String[] locationUUID = responseEntity.getHeaders().getLocation().getPath().split("/");
        UUID savedUUID = UUID.fromString(locationUUID[4]);

        Customer customer = customerRepository.findById(savedUUID).get();
        assertThat(customer).isNotNull();

    }

    @Transactional
    @Rollback
    @Test
    void testEmptyCustomerList() {

        customerRepository.deleteAll();

        List<CustomerDTO> customerDTOS = customerController.listCustomers();
        assertThat(customerDTOS.size()).isEqualTo(0);

    }

    @Test
    void testGetCustomersList() {

        List<CustomerDTO> customerDTOS = customerController.listCustomers();

        assertThat(customerDTOS.size()).isEqualTo(3);

    }

    @Test
    void testGetCustomerByIdNotFound() {

        assertThrows(NotFoundException.class, () -> {
            customerController.getCustomerById(UUID.randomUUID());
        });

    }

    @Test
    void testGetCustomerById() {

        Customer customer = customerRepository.findAll().get(0);

        CustomerDTO customerDTO = customerController.getCustomerById(customer.getId());

        assertThat(customerDTO).isNotNull();

    }

}