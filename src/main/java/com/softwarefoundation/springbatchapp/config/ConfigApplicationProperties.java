package com.softwarefoundation.springbatchapp.config;

import java.nio.file.Paths;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.FileSystemResource;

@Configuration
public class ConfigApplicationProperties {

    /**
     * Importa as configurações do arquivo application.properties armazenadas em
     * local externo.
     *
     * @return
     */
    @Bean
    public PropertySourcesPlaceholderConfigurer config() {
        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
        configurer.setLocation(new FileSystemResource(Paths.get("C:\\Sources\\spring-aplications-config\\application.properties")));
        return configurer;
    }

}
