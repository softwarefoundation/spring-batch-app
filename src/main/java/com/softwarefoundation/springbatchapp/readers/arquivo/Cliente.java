package com.softwarefoundation.springbatchapp.readers.arquivo;

public class Cliente {

    private String nome;
    private String sobrenome;
    private Integer idade;
    private String email;

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

    @Override
    public String toString() {
        return String.format("Cliente[ nome = %s - sobrenome = %s - idade = %d - email = %s ]", getNome(), getSobrenome(), getIdade(), getEmail());
    }
}
