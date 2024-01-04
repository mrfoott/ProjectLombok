package guru.springframework.spring6restmvc.services;

import guru.springframework.spring6restmvc.model.Customer;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CustomerService {

    List<Customer> getAllCustomer();

    Optional<Customer> getUserById(UUID id);

    Customer savedNewUser(Customer customer);

    void updateUserById(UUID id, Customer customer);

    void deleteUserById(UUID userId);

    void patchUserById(UUID userId, Customer customer);
}
