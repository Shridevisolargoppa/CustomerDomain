package com.CustomerManagementMicroService.albanero.Domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerDomain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    long customerID;

    @Column(name = "name")
    String name;

    @Column(name = "email")
    String email;

    @Column(name = "phone")
    long phone;

    @Column(name = "address")
    String address;

    @Getter
    @Column(name = "company_name")
    String companyName;

    @Column(name = "industry_type")
    String industryType;

    @Column(name = "customer_status")
    String customerStatus;

    @Column(name = "account_manager")
    String accountManager;

    @Column(name = "audit")
    LocalDateTime audit;

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setIndustryType(String industryType) {
        this.industryType = industryType;
    }

    public void setCustomerStatus(String customerStatus) {
        this.customerStatus = customerStatus;
    }

    public void setAccountManager(String accountManager) {
        this.accountManager = accountManager;
    }

    public void setAudit(LocalDateTime audit) {
        this.audit = audit;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public long getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getIndustryType() {
        return industryType;
    }

    public String getCustomerStatus() {
        return customerStatus;
    }

    public String getAccountManager() {
        return accountManager;
    }

    public LocalDateTime getAudit() {
        return audit;
    }

    public CustomerDomain(String name, String email, long phone, String address, String companyName, String industryType, String customerStatus, String accountManager) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.companyName = companyName;
        this.industryType = industryType;
        this.customerStatus = customerStatus;
        this.accountManager = accountManager;
    }

    public CustomerDomain()
    {

    }
}
