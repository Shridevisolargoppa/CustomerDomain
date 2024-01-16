package com.CustomerManagementMicroService.albanero.Controller;

import com.CustomerManagementMicroService.albanero.Domain.CustomerDomain;
import com.CustomerManagementMicroService.albanero.Exception.InvalidCustomerException;
import com.CustomerManagementMicroService.albanero.Service.CustomerDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/CustomerDomain")
public class CustomerDomainController {

    private final CustomerDomainService customerDomainService;

    @Autowired
    public CustomerDomainController(CustomerDomainService customerDomainService) {
        this.customerDomainService = customerDomainService;
    }
    @PostMapping("Create_Customer")
    public ResponseEntity<CustomerDomain> post(@RequestBody CustomerDomain customerDomain) {
        CustomerDomain createdCustomer = customerDomainService.post(customerDomain);
        return new ResponseEntity<>(createdCustomer, HttpStatus.CREATED);
    }

    @GetMapping("/Get_All_Customers")
    public ResponseEntity<List<CustomerDomain>> getAllCustomers() {
        List<CustomerDomain> customers = customerDomainService.getAll();
        if (!customers.isEmpty()) {
            return ResponseEntity.ok(customers);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(customers);
        }
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<?> getCustomerById(@PathVariable Long customerId) {
        try {
            CustomerDomain customer = customerDomainService.getById(customerId);
            return new ResponseEntity<>(customer, HttpStatus.OK);
        } catch (InvalidCustomerException e) {
            String notFoundMessage = "Customer with ID " + customerId + " not found.";
            return new ResponseEntity<>(notFoundMessage, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long customerId) {
        boolean deleted = customerDomainService.delete(customerId);
        if (deleted) {
            String successMessage = "Customer with ID " + customerId + " has been deleted successfully.";
            return new ResponseEntity<>(successMessage, HttpStatus.OK);
        } else {
            String notFoundMessage = "Customer with ID " + customerId + " not found.";
            return new ResponseEntity<>(notFoundMessage, HttpStatus.NOT_FOUND);
        }
    }


    @PutMapping("/{customerId}")
    public ResponseEntity<?> updateCustomer(@PathVariable Long customerId, @RequestBody CustomerDomain updatedCustomer) {
        try {
            CustomerDomain result = customerDomainService.update(customerId, updatedCustomer);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (InvalidCustomerException e) {
            return new ResponseEntity<>("Invalid Customer: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
