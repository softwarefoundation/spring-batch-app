package com.softwarefoundation.springbatchapp.jobimprimetexto;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("ImprimeTextoTasklet")
@Slf4j
public class ImprimeTextoTasklet implements Tasklet {

    @Override
    public RepeatStatus execute(StepContribution sc, ChunkContext cc) throws Exception {
        log.info(">>>> Spring Batch - Componentes separados >>>>>");
        return RepeatStatus.FINISHED;
    }

}
