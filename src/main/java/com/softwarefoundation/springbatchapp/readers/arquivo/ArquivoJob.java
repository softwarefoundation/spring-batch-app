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
    public Job arquivoComLarguraFixa(@Qualifier("arquivoLarguraFixaStep") Step step){
        return jobBuilderFactory
                .get("Arquivo com Largura Fixa")
                .start(step)
                .incrementer(new RunIdIncrementer())
                .build();

    }

    @Bean
    public Job arquivoComDelimitador(@Qualifier("arquivoComDelimitadorStep") Step step){
        return jobBuilderFactory
                .get("Arquivo com Delimitador")
                .start(step)
                .incrementer(new RunIdIncrementer())
                .build();
    }

    @Bean
    public Job arquivoMultiplosTipos(@Qualifier("arquivoMultiplosTiposStep") Step step){
        return jobBuilderFactory
                .get("Arquivo Multiplos Tipos")
                .start(step)
                .incrementer(new RunIdIncrementer())
                .build();
    }

    @Bean
    public Job arquivoMultiplosTiposDelagate(@Qualifier("arquivoMultiplosTiposComDelegateStep") Step step){
        return jobBuilderFactory
                .get("Arquivo Multiplos Tipos com Delegate")
                .start(step)
                .incrementer(new RunIdIncrementer())
                .build();
    }

    @Bean
    public Job multiplosArquivo(@Qualifier("multiplosArquivosStep") Step step){
        return jobBuilderFactory
                .get("Arquivo Multiplos Tipos com Delegate")
                .start(step)
                .incrementer(new RunIdIncrementer())
                .build();
    }




}
