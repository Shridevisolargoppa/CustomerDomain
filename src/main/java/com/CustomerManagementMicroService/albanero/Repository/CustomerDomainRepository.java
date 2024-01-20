package com.CustomerManagementMicroService.albanero.Repository;

import com.CustomerManagementMicroService.albanero.Domain.CustomerDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerDomainRepository extends JpaRepository<CustomerDomain,Long> {
    List<CustomerDomain> findByCompanyName(String companyName);
}
