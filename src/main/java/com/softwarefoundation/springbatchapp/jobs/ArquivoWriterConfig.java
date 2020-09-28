package com.softwarefoundation.springbatchapp.jobs;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

@Configuration
public class ArquivoWriterConfig {

    @Primary
    @Bean
    public ItemWriter escreverNoConsoleFixaWriter(){
        System.out.println();
        return items -> items.forEach( System.out::println);
    }

    @StepScope
    @Bean
    public FlatFileItemWriter<Cliente> escreverEmArquivoComLarguraFixaWriter(){
        Resource resource = new FileSystemResource("files/cliente-saida.txt");
        FlatFileItemWriter<Cliente> writer = new FlatFileItemWriterBuilder<Cliente>()
                .name("escreverEmArquivoComLarguraFixaWriter")
                .resource(resource)
                .formatted()
                .format("%-9s %-9s %-2s %-19s")
                .names(new String[]{"nome","sobrenome", "idade", "email"})
                .build();

        return writer;
    }

}
