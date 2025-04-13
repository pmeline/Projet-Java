package com.oxyl.coursepfback.PERSISTANCE.DAO;

import org.springframework.context.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;


@Configuration
@ComponentScan("com.oxyl.coursepfback")
public class ConfigDatabase {

    @Bean
    public DataSource initDataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/pvz");
        dataSource.setUsername("epf");
        dataSource.setPassword("mot");

        return dataSource;
    }

    @Bean
    public JdbcTemplate initJdbcTemplate(){ //surcouche de JDBC qui permet de faire des requêtes à la main
        JdbcTemplate jdbcTemplate = new JdbcTemplate(initDataSource());
        return jdbcTemplate;
    }

}
