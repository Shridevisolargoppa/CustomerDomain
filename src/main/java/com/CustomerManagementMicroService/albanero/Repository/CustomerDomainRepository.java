package com.CustomerManagementMicroService.albanero.Repository;

import com.CustomerManagementMicroService.albanero.Domain.CustomerDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerDomainRepository extends JpaRepository<CustomerDomain,Long> {
}
