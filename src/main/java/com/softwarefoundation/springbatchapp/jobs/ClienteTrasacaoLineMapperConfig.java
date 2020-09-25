package com.softwarefoundation.springbatchapp.jobs;

import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.mapping.PatternMatchingCompositeLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ClienteTrasacaoLineMapperConfig {

    @Bean
    public PatternMatchingCompositeLineMapper lineMapper(){
        PatternMatchingCompositeLineMapper lineMapper = new PatternMatchingCompositeLineMapper();
        lineMapper.setTokenizers(tokenizers());
        lineMapper.setFieldSetMappers(fieldSetMappers());


        return lineMapper;
    }

    private Map<String, FieldSetMapper> fieldSetMappers() {
        Map<String,FieldSetMapper> fieldSetMapperMap = new HashMap<>();
        fieldSetMapperMap.put("0*",fieldSetMapperMap(Cliente.class));
        fieldSetMapperMap.put("1*",fieldSetMapperMap(Transacao.class));
        return fieldSetMapperMap;
    }

    private FieldSetMapper fieldSetMapperMap(Class clazz) {
        BeanWrapperFieldSetMapper fieldSetMapper = new BeanWrapperFieldSetMapper();
        fieldSetMapper.setTargetType(clazz);
        return fieldSetMapper;
    }

    private Map<String, LineTokenizer> tokenizers() {
        Map<String, LineTokenizer> tokenizerMap = new HashMap<>();
        tokenizerMap.put("0*",clienteTokenizers());
        tokenizerMap.put("1*", transacaoTokenizers());
        return tokenizerMap;
    }

    /**
     * Ignora o nº identificador do tipo de registro,
     * pois quando mapeada para a respectiva classe, não é
     * necessário o identificador.
     *
     * @return
     */
    private LineTokenizer clienteTokenizers() {
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setNames("nome","sobrenome","idade","email");
        lineTokenizer.setIncludedFields(1,2,3,4);
        return lineTokenizer;
    }

    /**
     * Ignora o nº identificador do tipo de registro,
     * pois quando mapeada para a respectiva classe, não é
     * necessário o identificador.
     *
     * @return
     */
    private LineTokenizer transacaoTokenizers() {
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setNames("id","descricao","valor");
        lineTokenizer.setIncludedFields(1,2,3);
        return lineTokenizer;
    }

}
