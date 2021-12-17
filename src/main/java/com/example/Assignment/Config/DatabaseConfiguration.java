package com.example.Assignment.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfiguration {
    @Bean
    public JdbcTemplate getDataSource(@Autowired DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }

}
