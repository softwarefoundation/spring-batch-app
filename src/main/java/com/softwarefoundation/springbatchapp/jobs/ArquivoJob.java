package com.softwarefoundation.springbatchapp.jobs;

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

    //@Bean
    public Job arquivoComLarguraFixa(@Qualifier("arquivoLarguraFixaStep") Step step){
        return jobBuilderFactory
                .get("Arquivo com Largura Fixa")
                .start(step)
                .incrementer(new RunIdIncrementer())
                .build();

    }

    //@Bean
    public Job arquivoComDelimitador(@Qualifier("arquivoComDelimitadorStep") Step step){
        return jobBuilderFactory
                .get("Arquivo com Delimitador")
                .start(step)
                .incrementer(new RunIdIncrementer())
                .build();
    }

    //@Bean
    public Job arquivoMultiplosTipos(@Qualifier("arquivoMultiplosTiposStep") Step step){
        return jobBuilderFactory
                .get("Arquivo Multiplos Tipos")
                .start(step)
                .incrementer(new RunIdIncrementer())
                .build();
    }

    //@Bean
    public Job arquivoMultiplosTiposDelagate(@Qualifier("arquivoMultiplosTiposComDelegateStep") Step step){
        return jobBuilderFactory
                .get("Arquivo Multiplos Tipos com Delegate")
                .start(step)
                .incrementer(new RunIdIncrementer())
                .build();
    }

    //@Bean
    public Job multiplosArquivo(@Qualifier("multiplosArquivosStep") Step step){
        return jobBuilderFactory
                .get("Arquivo Multiplos Tipos com Delegate")
                .start(step)
                .incrementer(new RunIdIncrementer())
                .build();
    }

    //@Bean
    public Job jdbcCursorJob( @Qualifier("jdbcCursorStep") Step step){
        return jobBuilderFactory
                .get("Registro do banco de dados com cursor")
                .start(step)
                .incrementer(new RunIdIncrementer())
                .build();
    }

    //@Bean
    public Job jdbcPagingItemJob(@Qualifier("jdbcPagingItemStep") Step step){
        return jobBuilderFactory
                .get("Registro do banco de dados com paginação")
                .start(step)
                .incrementer(new RunIdIncrementer())
                .build();
    }

    //@Bean
    public Job skipExceptionJob(@Qualifier("skipExceptionStep") Step step){
        return jobBuilderFactory
                .get("Ignora o registro em caso de Exception")
                .start(step)
                .incrementer(new RunIdIncrementer())
                .build();
    }


    @Bean
    public Job validacaoArquivoJob(@Qualifier("arquivoValidacaoStep") Step step){
        return jobBuilderFactory.get("validacaoArquivoJob")
                .start(step)
                .incrementer(new RunIdIncrementer())
                .build();
    }







}
