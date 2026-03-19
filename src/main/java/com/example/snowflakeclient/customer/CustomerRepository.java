package com.example.snowflakeclient.customer;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

    @Query("SELECT PI()")
    Double selectPi();

    @Query("SELECT CUSTOMER FROM DATA_CLOUD")
    List<String> selectCustomers();

    /**
     * Convenience wrapper used by tests.
     * If multiple customers exist, we just take the first row.
     */
    default String selectAnyCustomer() {
        return selectCustomers().stream().findFirst().orElse(null);
    }
}

