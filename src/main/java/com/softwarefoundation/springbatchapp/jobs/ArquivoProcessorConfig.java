package com.softwarefoundation.springbatchapp.jobs;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.support.ClassifierCompositeItemProcessor;
import org.springframework.batch.item.support.CompositeItemProcessor;
import org.springframework.batch.item.support.builder.ClassifierCompositeItemProcessorBuilder;
import org.springframework.batch.item.support.builder.CompositeItemProcessorBuilder;
import org.springframework.batch.item.validator.BeanValidatingItemProcessor;
import org.springframework.batch.item.validator.ValidatingItemProcessor;
import org.springframework.batch.item.validator.ValidationException;
import org.springframework.batch.item.validator.Validator;
import org.springframework.classify.Classifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashSet;
import java.util.Set;

@Configuration
@Slf4j
public class ArquivoProcessorConfig {

    private Set<String> emails = new HashSet<>();

    @Bean
    public ItemProcessor<Cliente, Cliente> validacaoArquivoProcessor() throws Exception {
        return getCompositeItemProcessor();
    }

    @Bean
    public ItemProcessor processadorClassificadorProcessor() {
        ClassifierCompositeItemProcessor processor = new ClassifierCompositeItemProcessorBuilder<>()
                .classifier(getClassifierItem())
                .build();
        return processor;
    }

    private Classifier getClassifierItem() {

        Classifier<Object, ItemProcessor> classifier = new Classifier<Object, ItemProcessor>() {
            @Override
            public ItemProcessor classify(Object o) {
                return (o instanceof Cliente) ? new ClienteProcessor() : new TransacaoProcessor();
            }
        };

        return classifier;
    }

    private CompositeItemProcessor getCompositeItemProcessor() throws Exception {
        CompositeItemProcessor<Cliente, Cliente> processor = new CompositeItemProcessorBuilder()
                .delegates(getBeanValidatingItemProcessor(), getClienteValidatingItemProcessor())
                .build();
        return processor;
    }

    private BeanValidatingItemProcessor getBeanValidatingItemProcessor() throws Exception {
        BeanValidatingItemProcessor processor = new BeanValidatingItemProcessor();
        processor.setFilter(true);
        processor.afterPropertiesSet();
        return processor;
    }

    private ValidatingItemProcessor<Cliente> getClienteValidatingItemProcessor() {
        ValidatingItemProcessor<Cliente> processor = new ValidatingItemProcessor<>();
        processor.setFilter(true);
        Validator<Cliente> validator = new Validator<Cliente>() {
            @Override
            public void validate(Cliente cliente) throws ValidationException {
                if (emails.contains(cliente.getEmail())) {
                    throw new ValidationException(String.format("Email já foi processado: %s", cliente.getEmail()));
                } else {
                    emails.add(cliente.getEmail());
                }
            }
        };

        processor.setValidator(validator);

        return processor;
    }

}
