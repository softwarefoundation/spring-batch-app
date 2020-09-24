package com.softwarefoundation.springbatchapp.readers.arquivo;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration

public class ArquivoStepConfig {

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    @Qualifier("leituraArquivoStep")
    public Step leituraArquivoStep(ItemReader lerArquivoReader, ItemWriter<Cliente> lerAquivoWriter ){

        return stepBuilderFactory.get("leituraArquivoStep")
                .<Cliente,Cliente>chunk(1)
                .reader(lerArquivoReader)
                .writer(lerAquivoWriter)
                .build();

    }


}
