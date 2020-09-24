package com.softwarefoundation.springbatchapp;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.function.FunctionItemProcessor;
import org.springframework.batch.item.support.IteratorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class ParImparBatch {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job parImparJob() {

        return jobBuilderFactory.get("Par Impar Job")
                .start(parImparStep())
                .incrementer(new RunIdIncrementer())
                .build();

    }

    public Step parImparStep() {
        return stepBuilderFactory
                .get("Par Impar Step")
                .<Integer, String>chunk(1)
                .reader(contarAteDezReader())
                .processor(parOuImparProcessor())
                .writer(imprimeWriter())
                .build();
    }

    private IteratorItemReader<Integer> contarAteDezReader() {
        List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        return new IteratorItemReader<>(numeros.iterator());
    }

    private ItemProcessor<Integer, String> parOuImparProcessor() {
        return new FunctionItemProcessor<Integer, String>(parImparFunction());
    }

    private Function<Integer, String> parImparFunction() {
        Function<Integer, String> function = new Function<Integer, String>() {
            @Override
            public String apply(Integer item) {
                return (item % 2) == 0 ? String.format("Par: %s", item) : String.format("Impar %s", item);
            }
        };

        return function;
    }

    private ItemWriter<String> imprimeWriter() {
        return itens -> itens.forEach(System.out::println);
    }

}
