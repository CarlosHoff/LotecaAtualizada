package com.hoffmann.lotecaatualizada.domain.request;

public class UserRegisterRequest {

    private String name;
    private String cpf;
    private String apelido;
    private String senha;
    private String celular;
    private String email;

    public UserRegisterRequest() {
    }

    public UserRegisterRequest(String name, String cpf, String apelido, String senha, String celular, String email) {
        this.name = name;
        this.cpf = cpf;
        this.apelido = apelido;
        this.senha = senha;
        this.celular = celular;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
