package com.hoffmann.lotecaatualizada.domain.dto;

import java.io.Serializable;

public class RoletolaStatusDto implements Serializable {

    private String numeroSorteado;
    private String numeroApostado;

    public RoletolaStatusDto() {
    }

    public RoletolaStatusDto(String numeroSorteado, String numeroApostado) {
        this.numeroSorteado = numeroSorteado;
        this.numeroApostado = numeroApostado;
    }

    public String getNumeroSorteado() {
        return numeroSorteado;
    }

    public void setNumeroSorteado(String numeroSorteado) {
        this.numeroSorteado = numeroSorteado;
    }

    public String getNumeroApostado() {
        return numeroApostado;
    }

    public void setNumeroApostado(String numeroApostado) {
        this.numeroApostado = numeroApostado;
    }
}
