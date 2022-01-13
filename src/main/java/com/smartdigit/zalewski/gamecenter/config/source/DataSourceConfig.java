package com.smartdigit.zalewski.gamecenter.config.source;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:db.properties")
public class DataSourceConfig {

    private final Environment env;

    @Autowired
    public DataSourceConfig(Environment env) {
        this.env = env;
    }

    @Bean
    public DataSource dataSource() {

        return DataSourceBuilder.create()
                .driverClassName(env.getProperty("spring.datasource.driver-class"))
                .url(env.getProperty("spring.datasource.url"))
                .username(env.getProperty("spring.datasource.username"))
                .password(env.getProperty("spring.datasource.password"))
                .build();


    }
}
