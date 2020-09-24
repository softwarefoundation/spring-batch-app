package com.softwarefoundation.springbatchapp.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * Responsável por criar a separação do banco de dados
 * do Spring Batch do Banco de dados da aplicação
 *
 * prefix = Conforme o prefixo a configuração é definida
 * para determinado Datasource
 *
 * @Primary = Datasource padrão quando não for informado
 * explicitamente outro Datasource.
 *
 */
@Configuration
public class DatabaseConfig {

    @Primary
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource springBatchDataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties(prefix = "app.datasource")
    public DataSource appDataSource(){
        return DataSourceBuilder.create().build();
    }

}
