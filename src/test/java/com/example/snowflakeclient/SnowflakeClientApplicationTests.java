package com.example.snowflakeclient;

import com.example.snowflakeclient.customer.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class SnowflakeClientApplicationTests {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    void selectPiWorks() {
        Double pi = customerRepository.selectPi();
        assertThat(pi).isNotNull();
        assertThat(pi).isBetween(3.14, 3.15);
    }

    @Test
    void selectCustomerFromDataCloudWorks() {
        String customer = customerRepository.selectAnyCustomer();
        assertThat(customer).isNotNull();
    }
}

