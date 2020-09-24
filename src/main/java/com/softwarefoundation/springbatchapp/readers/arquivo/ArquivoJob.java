package com.softwarefoundation.springbatchapp.readers.arquivo;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class ArquivoJob {

    @Autowired
    private JobBuilderFactory  jobBuilderFactory;

    @Bean
    public Job lerArquivo(@Qualifier("leituraArquivoStep") Step step){
        return jobBuilderFactory
                .get("lerArquivo")
                .start(step)
                .incrementer(new RunIdIncrementer())
                .build();

    }

}
