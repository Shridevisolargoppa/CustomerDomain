package com.CustomerManagementMicroService.albanero;

import com.CustomerManagementMicroService.albanero.Domain.CustomerDomain;
import com.CustomerManagementMicroService.albanero.Exception.InvalidCustomerException;
import com.CustomerManagementMicroService.albanero.Repository.CustomerDomainRepository;
import com.CustomerManagementMicroService.albanero.Service.CustomerDomainService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class CustomerDomainServiceTest {

    @Mock
    private CustomerDomainRepository customerRepository;

    @InjectMocks
    private CustomerDomainService customerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testPost() {

        CustomerDomain customerDomain = new CustomerDomain();
        when(customerRepository.save(any())).thenReturn(customerDomain);

        CustomerDomain result = customerService.post(customerDomain);

        assertNotNull(result);
        verify(customerRepository, times(1)).save(customerDomain);
    }

    @Test
    void testGetAll() {
        List<CustomerDomain> customerList = Arrays.asList(new CustomerDomain(), new CustomerDomain());
        when(customerRepository.findAll()).thenReturn(customerList);

        List<CustomerDomain> result = customerService.getAll();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(customerRepository, times(1)).findAll();
    }

    @Test
    void testGetById() {
        Long customerId = 1L;
        CustomerDomain customer = new CustomerDomain();
        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));

        Optional<CustomerDomain> result = customerService.getById(customerId);

        assertTrue(result.isPresent());
        assertEquals(customer, result.get());
        verify(customerRepository, times(1)).findById(customerId);
    }

    @Test
    void testUpdate() throws InvalidCustomerException {
        Long customerId = 1L;
        CustomerDomain existingCustomer = new CustomerDomain();
        CustomerDomain updatedCustomer = new CustomerDomain();
        when(customerRepository.findById(customerId)).thenReturn(Optional.of(existingCustomer));
        when(customerRepository.save(updatedCustomer)).thenReturn(updatedCustomer);

        CustomerDomain result = customerService.update(customerId, updatedCustomer);

        assertNotNull(result);
        assertEquals(updatedCustomer, result);
        verify(customerRepository, times(1)).findById(customerId);
        verify(customerRepository, times(1)).save(updatedCustomer);
    }

    @Test
    void testUpdateWithInvalidCustomer() {
        Long customerId = 1L;
        when(customerRepository.findById(customerId)).thenReturn(Optional.empty());

        assertThrows(InvalidCustomerException.class,
                () -> customerService.update(customerId, new CustomerDomain()));
        verify(customerRepository, times(1)).findById(customerId);
        verify(customerRepository, never()).save(any());
    }

    @Test
    void testDelete() {
        Long customerId = 1L;
        when(customerRepository.existsById(customerId)).thenReturn(true);

        boolean result = customerService.delete(customerId);

        assertTrue(result);
        verify(customerRepository, times(1)).existsById(customerId);
        verify(customerRepository, times(1)).deleteById(customerId);
    }

    @Test
    void testDeleteNonExistingCustomer() {
        Long customerId = 1L;
        when(customerRepository.existsById(customerId)).thenReturn(false);

        boolean result = customerService.delete(customerId);
        assertFalse(result);
        verify(customerRepository, times(1)).existsById(customerId);
        verify(customerRepository, never()).deleteById(customerId);
    }


}

