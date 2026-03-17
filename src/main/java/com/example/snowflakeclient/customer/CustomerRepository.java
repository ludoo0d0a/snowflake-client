package com.example.snowflakeclient.customer;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

    @Query("SELECT PI()")
    Double selectPi();

    @Query("SELECT CUSTOMER FROM DATA_CLOUD LIMIT 1")
    String selectAnyCustomer();
}

