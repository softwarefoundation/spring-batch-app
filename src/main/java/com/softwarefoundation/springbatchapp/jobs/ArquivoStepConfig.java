package com.softwarefoundation.springbatchapp.jobs;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration

public class ArquivoStepConfig {

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    @Qualifier("arquivoLarguraFixaStep")
    public Step arquivoLarguraFixaStep(@Qualifier("arquivoComLarguraFixaReader") ItemReader reader, @Qualifier("escreverEmArquivoComLarguraFixaWriter") ItemWriter<Cliente> writer) {

        return stepBuilderFactory.get("Arquivo largura fixa")
                .<Cliente, Cliente>chunk(1)
                .reader(reader)
                .writer(writer)
                .build();
    }

    @Bean
    @Qualifier("arquivoComDelimitadorStep")
    public Step arquivoComDelimitadorStep(@Qualifier("arquivoComDelimitadorReader") ItemReader reader, ItemWriter<Cliente> writer) {

        return stepBuilderFactory.get("Arquivo com delimitador")
                .<Cliente, Cliente>chunk(1)
                .reader(reader)
                .writer(writer)
                .build();
    }

    @Bean
    @Qualifier("arquivoMultiplosTiposStep")
    public Step arquivoMultiplosTiposStep(@Qualifier("arquivoComLineMaperReader") FlatFileItemReader reader, @Qualifier("escreverNoConsoleFixaWriter") ItemWriter<Cliente> writer) {

        return stepBuilderFactory.get("Arquivo com multiplos tipos")
                .<Cliente, Cliente>chunk(1)
                .reader(reader)
                .writer(writer)
                .build();
    }

    @Bean
    @Qualifier("arquivoMultiplosTiposComDelegateStep")
    public Step arquivoMultiplosTiposComDelegateStep(@Qualifier("arquivoComLineMaperReader") FlatFileItemReader reader, ItemWriter<Cliente> writer) {

        return stepBuilderFactory.get("Arquivo com multiplos tipos com delegate")
                .<Cliente, Cliente>chunk(1)
                .reader(new ArquivoClienteTransacaoReader(reader))
                .writer(writer)
                .build();
    }

    @Bean
    @Qualifier("multiplosArquivosStep")
    public Step multiplosArquivosStep(@Qualifier("multiplosArquivoReader") MultiResourceItemReader<Cliente> reader, ItemWriter<Cliente> writer) {

        return stepBuilderFactory.get("Multiplos arquivos")
                .<Cliente, Cliente>chunk(1)
                .reader(reader)
                .writer(writer)
                .build();
    }

    @Bean
    @Qualifier("jdbcCursorStep")
    public Step jdbcCursorStep(@Qualifier("jdbcCursorReader") JdbcCursorItemReader<Cliente> reader, ItemWriter<Cliente> writer) {

        return stepBuilderFactory.get("Jdbc cursor step")
                .<Cliente, Cliente>chunk(1)
                .reader(reader)
                .writer(writer)
                .build();
    }

    @Bean
    @Qualifier("jdbcPagingItemStep")
    public Step jdbcPagingItemStep(@Qualifier("jdbcPagingItemReader") JdbcPagingItemReader<Cliente> reader, ItemWriter<Cliente> writer) {

        return stepBuilderFactory.get("Jdbc cursor step")
                .<Cliente, Cliente>chunk(1)
                .reader(reader)
                .writer(writer)
                .build();
    }

    @Bean
    public Step skipExceptionStep(@Qualifier("skipExceptionReader") JdbcCursorItemReader<Cliente> reader, ItemWriter<Cliente> writer) {

        return stepBuilderFactory.get("Jdbc cursor step")
                .<Cliente, Cliente>chunk(1)
                .reader(reader)
                .writer(writer)
                .faultTolerant()
                .skip(Exception.class)
                .skipLimit(3)
                .build();
    }

    @Bean
    public Step arquivoValidacaoStep(@Qualifier("arquivoParaValidacaoReader") FlatFileItemReader<Cliente> reader, @Qualifier("validacaoArquivoProcessor")ItemProcessor<Cliente,Cliente> processor, ItemWriter<Cliente> writer) {

        return stepBuilderFactory.get("arquivoValidacaoStep")
                .<Cliente,Cliente>chunk(1)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }

    @Bean
    public Step processadorClassificadorStep(@Qualifier("arquivoComLineMaperReader") FlatFileItemReader<Cliente> reader, @Qualifier("processadorClassificadorProcessor")ItemProcessor<Cliente,Cliente> processor, @Qualifier("escreverNoConsoleFixaWriter") ItemWriter<Cliente> writer) {

        return stepBuilderFactory.get("processadorClassificadorStep")
                .<Cliente,Cliente>chunk(1)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }


}
