package com.softwarefoundation.springbatchapp.jobs;

import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.ItemStreamReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.ResourceAwareItemReaderItemStream;
import org.springframework.core.io.Resource;

public class ArquivoClienteTransacaoResourceReaderConfig implements ItemStreamReader<Cliente> , ResourceAwareItemReaderItemStream<Cliente> {

    private Object registroAtual;
    private FlatFileItemReader<Object> delegate;

    public ArquivoClienteTransacaoResourceReaderConfig(FlatFileItemReader<Object> delegate) {
        this.delegate = delegate;
    }

    @Override
    public Cliente read() throws Exception {
        if (registroAtual == null) {
            registroAtual = delegate.read();
        }

        Cliente cliente = (Cliente) registroAtual;
        registroAtual = null;

        if (cliente != null) {
            while (peek() instanceof Transacao) {
                cliente.getTransacoes().add((Transacao) registroAtual);
            }
        }

        return cliente;
    }

    private Object peek() throws Exception {
        registroAtual = delegate.read();
        return registroAtual;
    }

    @Override
    public void open(ExecutionContext executionContext) throws ItemStreamException {
        delegate.open(executionContext);
    }

    @Override
    public void update(ExecutionContext executionContext) throws ItemStreamException {
        delegate.update(executionContext);
    }

    @Override
    public void close() throws ItemStreamException {
        delegate.close();
    }

    @Override
    public void setResource(Resource resource) {
        delegate.setResource(resource);
    }
}
