package com.softwarefoundation.springbatchapp.readers.arquivo;

import java.util.ArrayList;
import java.util.List;

public class Cliente {

    private String nome;
    private String sobrenome;
    private Integer idade;
    private String email;
    private List<Transacao> transacoes = new ArrayList<>();

    public Cliente() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Transacao> getTransacoes() {
        return transacoes;
    }

    public void setTransacoes(List<Transacao> transacoes) {
        this.transacoes = transacoes;
    }

    @Override
    public String toString() {
        return String.format("Cliente[ nome = %s - sobrenome = %s - idade = %d - email = %s - Transacoes: %s]", getNome(), getSobrenome(), getIdade(), getEmail(), (transacoes.isEmpty()? "":transacoes));
    }
}
