package com.softwarefoundation.springbatchapp.jobimprimetexto;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class ImprimeTextoTasklet implements Tasklet {

    @Override
    public RepeatStatus execute(StepContribution sc, ChunkContext cc) throws Exception {
        System.out.println(">>>> Spring Batch - Componentes separados >>>>>");
        return RepeatStatus.FINISHED;
    }

}
