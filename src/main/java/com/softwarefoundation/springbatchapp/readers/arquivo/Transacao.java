package com.softwarefoundation.springbatchapp.readers.arquivo;

public class Transacao {

    private String id;
    private String descricao;
    private String valor;

    public Transacao() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return String.format("Transacao[ ID: %s - Descrição: %s - Valor %s ]", this.id, this.descricao, this.valor);
    }
}
