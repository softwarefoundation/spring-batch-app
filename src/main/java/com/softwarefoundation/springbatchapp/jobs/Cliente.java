package com.softwarefoundation.springbatchapp.jobs;


import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class Cliente {

    @NotNull
    @Size(min = 1, max = 100)
    @Pattern(regexp="[a-zA-Z\\s]+", message="Nome deve ser alfabético")
    private String nome;

    private String sobrenome;

    @NotNull
    @Range(min = 18, max = 200,message = "Idade inválida")
    private Integer idade;

    @NotNull
    @Size(min=1, max=50)
    @Pattern(regexp="^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$", message="Email inválido")
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
