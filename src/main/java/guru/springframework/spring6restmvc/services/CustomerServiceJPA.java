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
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
@Primary
@RequiredArgsConstructor
public class CustomerServiceJPA implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Override
    public List<CustomerDTO> getAllCustomer() {
        return customerRepository.findAll()
                .stream()
                .map(customerMapper::customerToCustomerDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CustomerDTO> getUserById(UUID id) {
        return Optional.ofNullable(customerMapper.customerToCustomerDto(customerRepository.findById(id).
                orElse(null)));
    }

    @Override
    public CustomerDTO savedNewUser(CustomerDTO customerDTO) {
        return customerMapper.customerToCustomerDto(customerRepository.save(customerMapper.customerDtoToCustomer(customerDTO)));
    }

    @Override
    public Optional<CustomerDTO> updateUserById(UUID id, CustomerDTO customerDTO) {

        AtomicReference<Optional<CustomerDTO>> atomicReference = new AtomicReference<>();

        customerRepository.findById(id).ifPresentOrElse(foundCustomer -> {
            foundCustomer.setName(customerDTO.getName());
            customerRepository.save(foundCustomer);

            atomicReference.set(Optional.of(customerMapper
                    .customerToCustomerDto(customerRepository.save(foundCustomer))));
        }, () -> {
            atomicReference.set(Optional.empty());
        });

        return atomicReference.get();

    }

    @Override
    public void deleteUserById(UUID userId) {
        customerRepository.deleteById(userId);
    }

    @Override
    public void patchUserById(UUID userId, CustomerDTO customer) {

    }
}
