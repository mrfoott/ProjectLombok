package guru.springframework.spring6restmvc.controller;

import guru.springframework.spring6restmvc.model.Customer;
import guru.springframework.spring6restmvc.services.CustomerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/user")
public class CustomerController {

    private final CustomerService customerService;

    @PatchMapping("{userId}")
    public ResponseEntity updateUserPatchById(@PathVariable("userId") UUID userId, @RequestBody Customer customer) {

        customerService.patchCustomerId(userId, customer);

        return new ResponseEntity(HttpStatus.NO_CONTENT);

    }

    @DeleteMapping("{userId}")
    public ResponseEntity deleteById(@PathVariable("userId") UUID userId) {

        customerService.deleteById(userId);

        return new ResponseEntity(HttpStatus.NO_CONTENT);

    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Customer> listCustomers() {
        return customerService.getAllCustomer();
    }

    @RequestMapping(value = "{customerId}", method = RequestMethod.GET)
    public Customer getCustomerById(@PathVariable("customerId") UUID customerId) {

        return customerService.getCustomerById(customerId);

    }

    @PostMapping
    public ResponseEntity handlePost(@RequestBody Customer customer) {

        Customer savedCustomer = customerService.savedNewCustomer(customer);

        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.add("Con Moe", "Con Moe");
        httpHeaders.add("Location", "/api/v1/beer/" + savedCustomer.getId());

        return new ResponseEntity(httpHeaders, HttpStatus.CREATED);

    }

    @PutMapping("{userId}")
    public ResponseEntity updateById(@PathVariable("userId") UUID userId, @RequestBody Customer customer) {
        customerService.updateUserById(userId, customer);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
