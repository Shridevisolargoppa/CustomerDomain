package com.CustomerManagementMicroService.albanero.Domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
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
}
