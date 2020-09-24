package com.softwarefoundation.springbatchapp.jobimprimetexto;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ImprimeTextoStepConfig {

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Step imprimeTextoStep( @Qualifier("ImprimeTextoTasklet") Tasklet tasklet) {
        return stepBuilderFactory.
                get("Nome do Step")
                .tasklet(tasklet)
                .build();

    }

}
