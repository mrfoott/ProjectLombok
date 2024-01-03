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
//@RequestMapping("/api/v1/user")
public class CustomerController {
    public static final String CUSTOMER_PATH = "/api/v1/user";
    public static final String CUSTOMER_ID_PATH = CUSTOMER_PATH + "/{userId}";

    private final CustomerService customerService;

    @PatchMapping(CUSTOMER_ID_PATH)
    public ResponseEntity updateUserPatchById(@PathVariable("userId") UUID userId, @RequestBody Customer customer) {

        customerService.patchUserById(userId, customer);

        return new ResponseEntity(HttpStatus.NO_CONTENT);

    }

    @DeleteMapping(CUSTOMER_ID_PATH)
    public ResponseEntity deleteById(@PathVariable("userId") UUID userId) {

        customerService.deleteUserById(userId);

        return new ResponseEntity(HttpStatus.NO_CONTENT);

    }

//    @RequestMapping(method = RequestMethod.GET)
    @GetMapping(CUSTOMER_PATH)
    public List<Customer> listCustomers() {
        return customerService.getAllCustomer();
    }

    @GetMapping(value = CUSTOMER_ID_PATH)
    public Customer getCustomerById(@PathVariable("userId") UUID userId) {

        return customerService.getUserById(userId);

    }

    @PostMapping(CUSTOMER_PATH)
    public ResponseEntity handlePost(@RequestBody Customer customer) {

        Customer savedCustomer = customerService.savedNewUser(customer);

        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.add("Con Moe", "Con Moe");
        httpHeaders.add("Location", CustomerController.CUSTOMER_PATH + "/" + savedCustomer.getId());

        return new ResponseEntity(httpHeaders, HttpStatus.CREATED);

    }

    @PutMapping(CUSTOMER_ID_PATH)
    public ResponseEntity updateById(@PathVariable("userId") UUID userId, @RequestBody Customer customer) {
        customerService.updateUserById(userId, customer);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
