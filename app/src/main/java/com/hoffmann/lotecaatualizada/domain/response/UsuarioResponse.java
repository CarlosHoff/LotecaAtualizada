package com.hoffmann.lotecaatualizada.domain.response;

import java.io.Serializable;

public class UsuarioResponse implements Serializable {

    private String nome;
    private String apelido;
    private String email;
    private String celular;

    public UsuarioResponse() {
    }

    public UsuarioResponse(String nome, String apelido, String email, String celular) {
        this.nome = nome;
        this.apelido = apelido;
        this.email = email;
        this.celular = celular;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
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
