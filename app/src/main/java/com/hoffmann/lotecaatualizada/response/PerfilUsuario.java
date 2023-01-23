package com.hoffmann.lotecaatualizada.response;

public class PerfilUsuario {

    private String nome;
    private String email;
    private String celular;

    public PerfilUsuario() {
    }

    public PerfilUsuario(String nome, String email, String celular) {
        this.nome = nome;
        this.email = email;
        this.celular = celular;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }
}
