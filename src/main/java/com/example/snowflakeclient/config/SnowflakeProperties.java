package com.example.snowflakeclient.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "snowflake")
@Data
public class SnowflakeProperties {

    /**
     * Full JDBC URL, for example:
     * jdbc:snowflake://<account>.snowflakecomputing.com/?db=MY_DB&schema=PUBLIC
     */
    private String url;

    private String user;

    private String role;

    private String warehouse;

    private String privateKeyPath;

    private String privateKeyPassphrase;
}

