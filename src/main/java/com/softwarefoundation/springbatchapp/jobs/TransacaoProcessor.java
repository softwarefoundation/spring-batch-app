package com.softwarefoundation.springbatchapp.jobs;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;

@Slf4j
public class TransacaoProcessor implements ItemProcessor<Transacao, Transacao> {

    @Override
    public Transacao process(Transacao transacao) throws Exception {
        log.info("Aplicando regras de negócio na transação: {}",transacao.getId());
        return transacao;
    }
}
