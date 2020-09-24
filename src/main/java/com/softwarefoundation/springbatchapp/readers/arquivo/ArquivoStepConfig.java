package com.softwarefoundation.springbatchapp.readers.arquivo;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
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
    public Step arquivoLarguraFixaStep( @Qualifier("arquivoComLarguraFixaReader") ItemReader reader, ItemWriter<Cliente> writer ){

        return stepBuilderFactory.get("Arquivo largura fixa")
                .<Cliente,Cliente>chunk(1)
                .reader(reader)
                .writer(writer)
                .build();
    }

    @Bean
    @Qualifier("arquivoComDelimitadorStep")
    public Step arquivoComDelimitadorStep(@Qualifier("arquivoComDelimitadorReader") ItemReader reader, ItemWriter<Cliente> writer ){

        return stepBuilderFactory.get("Arquivo com delimitador")
                .<Cliente,Cliente>chunk(1)
                .reader(reader)
                .writer(writer)
                .build();
    }

    @Bean
    @Qualifier("arquivoMultiplosTiposStep")
    public Step arquivoMultiplosTiposStep(@Qualifier("arquivoComLineMaperReader") FlatFileItemReader reader, ItemWriter<Cliente> writer ){

        return stepBuilderFactory.get("Arquivo com multiplos tipos")
                .<Cliente,Cliente>chunk(1)
                .reader(reader)
                .writer(writer)
                .build();
    }

    @Bean
    @Qualifier("arquivoMultiplosTiposComDelegateStep")
    public Step arquivoMultiplosTiposComDelegateStep(@Qualifier("arquivoComLineMaperReader") FlatFileItemReader reader, ItemWriter<Cliente> writer ){

        return stepBuilderFactory.get("Arquivo com multiplos tipos com delegate")
                .<Cliente,Cliente>chunk(1)
                .reader(new ArquivoClienteTransacaoReader(reader))
                .writer(writer)
                .build();
    }


}
