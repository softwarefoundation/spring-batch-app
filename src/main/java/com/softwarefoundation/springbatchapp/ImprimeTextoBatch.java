package com.softwarefoundation.springbatchapp;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 *
 *
 */
@Configuration
@EnableBatchProcessing
public class ImprimeTextoBatch {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job imprimirTextoJob() {
        return jobBuilderFactory
                .get("Nome do Job")
                .start(imprimeTextoStep())
                .incrementer(new RunIdIncrementer())
                .build();
    }

    public Step imprimeTextoStep() {
        return stepBuilderFactory.
                get("Nome do Step")
                .tasklet(getTasklet(null))
                .build();

    }

    @Bean
    @StepScope
    public Tasklet getTasklet(@Value("#{jobParameters['nome']}") String nome) {
        Tasklet tasklet = new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution sc, ChunkContext cc) throws Exception {
                System.out.println(">>>> Spring Batch >>>>>");
                System.out.println(String.format("Olá, %s", nome));
                return RepeatStatus.FINISHED;
            }
        };
        return tasklet;
    }

}
