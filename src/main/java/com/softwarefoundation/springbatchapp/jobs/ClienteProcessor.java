package com.softwarefoundation.springbatchapp.jobs;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;

@Slf4j
public class ClienteProcessor implements ItemProcessor<Cliente, Cliente> {


    @Override
    public Cliente process(Cliente cliente) throws Exception {
        log.info("Aplicando regras de negócio no cliente: {}",cliente.getEmail());
        return cliente;
    }
}
