package com.example.snowflakeclient.customer;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("DATA_CLOUD")
public class Customer {

    @Id
    private Long id;

    @Column("CUSTOMER")
    private String customer;

    public Customer(Long id, String customer) {
        this.id = id;
        this.customer = customer;
    }

    public Long getId() {
        return id;
    }

    public String getCustomer() {
        return customer;
    }
}

