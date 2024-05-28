package com.web3.ecommerceback.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
@Configuration
public class DBconfig {
    @Bean
    public DataSource postgresDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/"+System.getenv("DBname"));
        dataSource.setUsername(System.getenv("DBuser"));
        dataSource.setPassword(System.getenv("DBpassword"));
        return dataSource;
    }
}
