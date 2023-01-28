package com.hoffmann.lotecaatualizada.domain.response;

import java.io.Serializable;

public class LoginResponse implements Serializable {

    private String token;
    private String nome;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
