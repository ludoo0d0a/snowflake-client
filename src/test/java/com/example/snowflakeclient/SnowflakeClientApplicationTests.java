package com.example.snowflakeclient;

import com.example.snowflakeclient.customer.CustomerRepository;
import com.example.snowflakeclient.config.SnowflakeProperties;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class SnowflakeClientApplicationTests {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private SnowflakeProperties snowflakeProperties;

    private boolean snowflakeConfigured() {
        String url = snowflakeProperties.getUrl();
        String user = snowflakeProperties.getUser();
        String privateKeyPath = snowflakeProperties.getPrivateKeyPath();

        if (url == null || url.isBlank() || url.contains("<account>")) {
            return false;
        }
        if (user == null || user.isBlank() || user.contains("<YOUR_USER>")) {
            return false;
        }
        if (privateKeyPath == null || privateKeyPath.isBlank() || privateKeyPath.contains("/path/to/your/")) {
            return false;
        }
        return java.nio.file.Files.exists(java.nio.file.Path.of(privateKeyPath));
    }

    @Test
    void selectPiWorks() {
        Assumptions.assumeTrue(snowflakeConfigured(), "Skipping Snowflake test: YAML not configured with real credentials/key.");
        Double pi = customerRepository.selectPi();
        assertThat(pi).isNotNull();
        assertThat(pi).isBetween(3.14, 3.15);
    }

    @Test
    void selectCustomerFromDataCloudWorks() {
        Assumptions.assumeTrue(snowflakeConfigured(), "Skipping Snowflake test: YAML not configured with real credentials/key.");
        String customer = customerRepository.selectAnyCustomer();
        assertThat(customer).isNotNull();
    }
}

