package com.CustomerManagementMicroService.albanero;

import com.CustomerManagementMicroService.albanero.Controller.CustomerDomainController;
import com.CustomerManagementMicroService.albanero.Domain.CustomerDomain;
import com.CustomerManagementMicroService.albanero.Service.CustomerDomainService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class CustomerDomainControllerTest {

    @Mock
    private CustomerDomainService customerDomainService;

    @InjectMocks
    private CustomerDomainController customerDomainController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testPost() {
        CustomerDomain customerDomain = new CustomerDomain();
        when(customerDomainService.post(any())).thenReturn(customerDomain);

        ResponseEntity<CustomerDomain> responseEntity = customerDomainController.post(customerDomain);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(customerDomain, responseEntity.getBody());
    }

    @Test
    void testGetAllCustomers() {
        List<CustomerDomain> customerList = Arrays.asList(new CustomerDomain(), new CustomerDomain());
        when(customerDomainService.getAll()).thenReturn(customerList);

        ResponseEntity<List<CustomerDomain>> responseEntity = customerDomainController.getAllCustomers();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(customerList, responseEntity.getBody());
    }

    @Test
    void testGetCustomerById() {
        Long customerId = 1L;
        CustomerDomain customer = new CustomerDomain();
        when(customerDomainService.getById(customerId)).thenReturn(Optional.of(customer));

        ResponseEntity<CustomerDomain> responseEntity = customerDomainController.getCustomerById(customerId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(customer, responseEntity.getBody());
    }
}
