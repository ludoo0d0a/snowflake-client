package com.example.snowflakeclient.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "h2")
@Data
public class H2Properties {

    private Datasource datasource = new Datasource();
    private Jpa jpa = new Jpa();

    @Data
    public static class Datasource {
        private String url;
        private String username;
        private String password;
        private String driverClassName;
    }

    @Data
    public static class Jpa {
        private String hbm2ddlAuto;
        private String dialect;
    }
}

