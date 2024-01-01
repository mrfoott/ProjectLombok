package guru.springframework.spring6restmvc.services;

import guru.springframework.spring6restmvc.model.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {

    private Map<UUID, Customer> customerMap;

    public CustomerServiceImpl() {
        Customer customer1 = Customer.builder()
                .id(UUID.randomUUID())
                .name("Foot")
                .version(1)
                .createdDate(LocalDateTime.now())
                .lastModifiedDay(LocalDateTime.now())
                .build();

        Customer customer2 = Customer.builder()
                .id(UUID.randomUUID())
                .name("Kor")
                .version(1)
                .createdDate(LocalDateTime.now())
                .lastModifiedDay(LocalDateTime.now())
                .build();

        Customer customer3 = Customer.builder()
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
    public List<Customer> getAllCustomer() {
        return new ArrayList<>(customerMap.values());
    }

    @Override
    public Customer getCustomerById(UUID id) {
        return customerMap.get(id);
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
