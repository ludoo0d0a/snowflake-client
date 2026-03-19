package com.example.snowflakeclient.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;

@Configuration
@EnableJdbcRepositories(
        basePackages = "com.example.snowflakeclient.customer",
        jdbcOperationsRef = "snowflakeJdbcTemplate",
        transactionManagerRef = "snowflakeTransactionManager"
)
public class SnowflakeJdbcRepositoriesConfig {
}

