package com.hoffmann.lotecaatualizada.domain.request;

import java.util.List;

public class BetRequest {

    private List<Long[]> numeros;
    private String email;

    public BetRequest() {

    }

    public BetRequest(List<Long[]> numeros, String email) {
        this.numeros = numeros;
        this.email = email;
    }

    public List<Long[]> getNumeros() {
        return numeros;
    }

    public void setNumeros(List<Long[]> numeros) {
        this.numeros = numeros;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
