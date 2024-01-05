package guru.springframework.spring6restmvc.services;

import guru.springframework.spring6restmvc.model.CustomerDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {

    private Map<UUID, CustomerDTO> customerMap;

    @Override
    public void patchUserById(UUID userId, CustomerDTO customer) {
        CustomerDTO existing = customerMap.get(userId);

        if (StringUtils.hasText(customer.getName())) {
            existing.setName(customer.getName());
        }
    }

    @Override
    public void deleteUserById(UUID userId) {
        customerMap.remove(userId);
    }

    public CustomerServiceImpl() {
        CustomerDTO customer1 = CustomerDTO.builder()
                .id(UUID.randomUUID())
                .name("Foot")
                .version(1)
                .createdDate(LocalDateTime.now())
                .lastModifiedDay(LocalDateTime.now())
                .build();

        CustomerDTO customer2 = CustomerDTO.builder()
                .id(UUID.randomUUID())
                .name("Kor")
                .version(1)
                .createdDate(LocalDateTime.now())
                .lastModifiedDay(LocalDateTime.now())
                .build();

        CustomerDTO customer3 = CustomerDTO.builder()
                .id(UUID.randomUUID())
                .name("Ngoc")
                .version(1)
                .createdDate(LocalDateTime.now())
                .lastModifiedDay(LocalDateTime.now())
                .build();

        customerMap = new HashMap<>();
        customerMap.put(customer1.getId(), customer1);
        customerMap.put(customer2.getId(), customer2);
        customerMap.put(customer3.getId(), customer3);
    }

    @Override
    public List<CustomerDTO> getAllCustomer() {
        return new ArrayList<>(customerMap.values());
    }

    @Override
    public Optional<CustomerDTO> getUserById(UUID id) {
        return Optional.of(customerMap.get(id));
    }

    @Override
    public CustomerDTO savedNewUser(CustomerDTO customer) {
        CustomerDTO savedCustomer = CustomerDTO.builder()
                .id(UUID.randomUUID())
                .name(customer.getName())
                .version(customer.getVersion())
                .createdDate(LocalDateTime.now())
                .lastModifiedDay(LocalDateTime.now())
                .build();

        customerMap.put(savedCustomer.getId(), savedCustomer);

        return savedCustomer;
    }

    @Override
    public void updateUserById(UUID id, CustomerDTO customer) {

        CustomerDTO existing = customerMap.get(id);

        existing.setName(customer.getName());

        customerMap.put(existing.getId(), existing);

    }


//    @Override
//    public Customer getCustomerById(UUID id) {
//        return customerMap.get(id);
//    }
//
//    @Override
//    public List<Customer> getAllCustomers() {
//        return new ArrayList<>(customerMap.values());
//    }
}
