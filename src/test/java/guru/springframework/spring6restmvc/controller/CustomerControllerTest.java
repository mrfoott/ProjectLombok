package guru.springframework.spring6restmvc.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import guru.springframework.spring6restmvc.model.CustomerDTO;
import guru.springframework.spring6restmvc.services.CustomerService;
import guru.springframework.spring6restmvc.services.CustomerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


//@SpringBootTest
@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

//    @Autowired
//    CustomerController customerController;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    CustomerService customerService;

    CustomerServiceImpl customerServiceImpl;

    @Captor
    ArgumentCaptor<UUID> uuidArgumentCaptor;

    @Captor
    ArgumentCaptor<CustomerDTO> customerArgumentCaptor;

    CustomerDTO customer;

    @BeforeEach
    void setUp() {
        customerServiceImpl = new CustomerServiceImpl();
        customer = customerServiceImpl.getAllCustomer().get(0);
    }

    @Test
    void testPatchCustomer() throws Exception {

//        Customer customer = customerServiceImpl.getAllCustomer().get(0);

        Map<String, Object> customerMap = new HashMap<>();
        customerMap.put("name", "New Name");

        mockMvc.perform(patch(CustomerController.CUSTOMER_ID_PATH, customer.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customerMap)))
                .andExpect(status().isNoContent());

        verify(customerService).patchUserById(uuidArgumentCaptor.capture(), customerArgumentCaptor.capture());

        assertThat(customer.getId()).isEqualTo(uuidArgumentCaptor.getValue());
        assertThat(customerMap.get("name")).isEqualTo(customerArgumentCaptor.getValue().getName());

    }

    @Test
    void testDeleteCustomer() throws Exception {

//        Customer customer = customerServiceImpl.getAllCustomer().get(0);

        mockMvc.perform(delete(CustomerController.CUSTOMER_ID_PATH, customer.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

//        ArgumentCaptor<UUID> uuidArgumentCaptor = ArgumentCaptor.forClass(UUID.class);

        verify(customerService).deleteUserById(uuidArgumentCaptor.capture());

        assertThat(customer.getId()).isEqualTo(uuidArgumentCaptor.getValue());

    }

    @Test
    void testUpdateCustomer() throws Exception {

//        Customer customer = customerServiceImpl.getAllCustomer().get(0);

        mockMvc.perform(put(CustomerController.CUSTOMER_ID_PATH, customer.getId())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customer)))
                        .andExpect(status().isNoContent());

        verify(customerService).updateUserById(uuidArgumentCaptor.capture(), any(CustomerDTO.class));

        assertThat(customer.getId()).isEqualTo(uuidArgumentCaptor.getValue());
    }

    @Test
    void testCreateNewCustomer() throws Exception {
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.registerModule();

//        Customer customer = customerServiceImpl.getAllCustomer().get(0);
        customer.setName(null);

        given(customerService.savedNewUser(any(CustomerDTO.class))).willReturn(customerServiceImpl.getAllCustomer().get(0));

        mockMvc.perform(post(CustomerController.CUSTOMER_PATH)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(customer)))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"));

    }

    @Test
    void testListCustomer() throws Exception {

        given(customerService.getAllCustomer()).willReturn(customerServiceImpl.getAllCustomer());

        mockMvc.perform(get(CustomerController.CUSTOMER_PATH)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()", is(3)));

    }


    @Test
    void getUserByIdNotFound() throws Exception {

        given(customerService.getUserById(any(UUID.class))).willReturn(Optional.empty());

        mockMvc.perform(get(CustomerController.CUSTOMER_ID_PATH, UUID.randomUUID()))
                .andExpect(status().isNotFound());

    }

    @Test
    void getUserById() throws Exception {
//        System.out.println(customerController.getCustomerById(UUID.randomUUID()));

//        Customer testCustomer = customerServiceImpl.getAllCustomer().get(0);

        given(customerService.getUserById(customer.getId())).willReturn(Optional.of(customer));

        mockMvc.perform(get(CustomerController.CUSTOMER_ID_PATH, customer.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(customer.getId().toString())))
                .andExpect(jsonPath("$.name", is(customer.getName())));

    }


}
