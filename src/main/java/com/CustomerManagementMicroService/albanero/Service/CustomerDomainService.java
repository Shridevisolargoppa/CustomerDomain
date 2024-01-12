package com.CustomerManagementMicroService.albanero.Service;

import com.CustomerManagementMicroService.albanero.Domain.CustomerDomain;
import com.CustomerManagementMicroService.albanero.Exception.InvalidCustomerException;
import com.CustomerManagementMicroService.albanero.Repository.CustomerDomainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerDomainService {

    private final CustomerDomainRepository customerRepository;

    @Autowired
    public CustomerDomainService(CustomerDomainRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public CustomerDomain post(CustomerDomain customerDomain) {
        customerDomain.setAudit(LocalDateTime.now());
        return customerRepository.save(customerDomain);
    }

    public List<CustomerDomain> getAll() {
        return customerRepository.findAll();
    }

    public Optional<CustomerDomain> getById(Long customerId) {
        return customerRepository.findById(customerId);
    }

    public CustomerDomain update(Long customerId, CustomerDomain updatedCustomer) throws InvalidCustomerException {
        Optional<CustomerDomain> existingCustomerOptional = customerRepository.findById(customerId);

        if (existingCustomerOptional.isPresent()) {

            updatedCustomer.setAudit(LocalDateTime.now());

            return customerRepository.save(updatedCustomer);
        } else {
            throw new InvalidCustomerException("Given id is Invalid");
        }
    }

    public boolean delete(Long customerId) {
        if (customerRepository.existsById(customerId)) {
            customerRepository.deleteById(customerId);
            return true;
        } else {
            return false;
        }
    }


}