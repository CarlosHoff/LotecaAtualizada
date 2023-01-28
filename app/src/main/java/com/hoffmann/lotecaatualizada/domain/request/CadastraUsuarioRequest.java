package com.hoffmann.lotecaatualizada.domain.request;

public class CadastraUsuarioRequest {

    private String nome;
    private String cpf;
    private String apelido;
    private String senha;
    private String celular;
    private String email;

    public CadastraUsuarioRequest() {
    }

    public CadastraUsuarioRequest(String nome, String cpf, String apelido, String senha, String celular, String email) {
        this.nome = nome;
        this.cpf = cpf;
        this.apelido = apelido;
        this.senha = senha;
        this.celular = celular;
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
