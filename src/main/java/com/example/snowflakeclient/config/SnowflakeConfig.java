package com.example.snowflakeclient.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;
import java.sql.Driver;
import java.util.Properties;

@Configuration
public class SnowflakeConfig {

    private final SnowflakeProperties properties;

    public SnowflakeConfig(SnowflakeProperties properties) {
        this.properties = properties;
    }

    @Bean
    public DataSource snowflakeDataSource() throws Exception {
        // Load Snowflake JDBC driver
        Class<?> driverClass = Class.forName("net.snowflake.client.jdbc.SnowflakeDriver");
        Driver driver = (Driver) driverClass.getDeclaredConstructor().newInstance();

        Properties props = new Properties();
        props.put("user", properties.getUser());
        if (properties.getRole() != null) {
            props.put("role", properties.getRole());
        }
        if (properties.getWarehouse() != null) {
            props.put("warehouse", properties.getWarehouse());
        }

        // Private key based auth: we keep the key path and passphrase in the YAML config.
        // The driver will read the key file from disk.
        props.put("private_key_file", properties.getPrivateKeyPath());
        props.put("private_key_file_pwd", properties.getPrivateKeyPassphrase());

        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        dataSource.setDriver(driver);
        dataSource.setUrl(properties.getUrl());
        dataSource.setConnectionProperties(props);
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource snowflakeDataSource) {
        return new JdbcTemplate(snowflakeDataSource);
    }
}

