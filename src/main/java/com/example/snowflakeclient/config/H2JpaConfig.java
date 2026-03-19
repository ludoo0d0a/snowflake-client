package com.example.snowflakeclient.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;

import lombok.RequiredArgsConstructor;
import jakarta.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.example.snowflakeclient.h2.repository",
        entityManagerFactoryRef = "h2EntityManagerFactory",
        transactionManagerRef = "h2TransactionManager"
)
@RequiredArgsConstructor
public class H2JpaConfig {

    private final H2Properties h2Properties;

    @Bean(name = "h2DataSource")
    @Primary
    public DataSource h2DataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(h2Properties.getDatasource().getDriverClassName());
        dataSource.setUrl(h2Properties.getDatasource().getUrl());
        dataSource.setUsername(h2Properties.getDatasource().getUsername());
        dataSource.setPassword(h2Properties.getDatasource().getPassword());
        return dataSource;
    }

    @Bean(name = "h2EntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean h2EntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("h2DataSource") DataSource dataSource
    ) {
        Map<String, Object> jpaProps = new HashMap<>();
        jpaProps.put("hibernate.hbm2ddl.auto", h2Properties.getJpa().getHbm2ddlAuto());
        jpaProps.put("hibernate.dialect", h2Properties.getJpa().getDialect());

        return builder
                .dataSource(dataSource)
                .packages("com.example.snowflakeclient.h2.entity")
                .persistenceUnit("h2")
                .properties(jpaProps)
                .build();
    }

    @Bean(name = "h2TransactionManager")
    public JpaTransactionManager h2TransactionManager(
            @Qualifier("h2EntityManagerFactory") EntityManagerFactory entityManagerFactory
    ) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}

