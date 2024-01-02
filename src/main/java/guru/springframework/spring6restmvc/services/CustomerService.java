package guru.springframework.spring6restmvc.services;

import guru.springframework.spring6restmvc.model.Customer;

import java.util.List;
import java.util.UUID;

public interface CustomerService {

    List<Customer> getAllCustomer();

    Customer getCustomerById(UUID id);

    Customer savedNewCustomer(Customer customer);

    void updateUserById(UUID id, Customer customer);

    void deleteById(UUID userId);

    void patchCustomerId(UUID userId, Customer customer);
}
