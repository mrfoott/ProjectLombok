package guru.springframework.spring6restmvc.services;

import guru.springframework.spring6restmvc.model.CustomerDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CustomerService {

    List<CustomerDTO> getAllCustomer();

    Optional<CustomerDTO> getUserById(UUID id);

    CustomerDTO savedNewUser(CustomerDTO customer);

    void updateUserById(UUID id, CustomerDTO customer);

    void deleteUserById(UUID userId);

    void patchUserById(UUID userId, CustomerDTO customer);
}
