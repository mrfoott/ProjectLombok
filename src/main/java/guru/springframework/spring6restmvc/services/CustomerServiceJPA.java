package guru.springframework.spring6restmvc.services;

import guru.springframework.spring6restmvc.mappers.CustomerMapper;
import guru.springframework.spring6restmvc.model.CustomerDTO;
import guru.springframework.spring6restmvc.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Primary
@RequiredArgsConstructor
public class CustomerServiceJPA implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Override
    public List<CustomerDTO> getAllCustomer() {
        return null;
    }

    @Override
    public Optional<CustomerDTO> getUserById(UUID id) {
        return Optional.empty();
    }

    @Override
    public CustomerDTO savedNewUser(CustomerDTO customer) {
        return null;
    }

    @Override
    public void updateUserById(UUID id, CustomerDTO customer) {

    }

    @Override
    public void deleteUserById(UUID userId) {

    }

    @Override
    public void patchUserById(UUID userId, CustomerDTO customer) {

    }
}
