package com.hoffmann.lotecaatualizada.domain.response;

import java.io.Serializable;

public class AllBetsResponse implements Serializable {

    private Long dezenaUm;
    private Long dezenaDois;
    private Long dezenaTres;
    private Long dezenaQuatro;
    private Long dezenaCinco;
    private Long dezenaSeis;
    private Long dezenaSete;
    private Long dezenaOito;
    private Long dezenaNove;
    private Long dezenaDez;
    private Long qtdAcertos;

    private ProfileResponse usuario;


    public Long getDezenaUm() {
        return dezenaUm;
    }

    public void setDezenaUm(Long dezenaUm) {
        this.dezenaUm = dezenaUm;
    }

    public Long getDezenaDois() {
        return dezenaDois;
    }

    public void setDezenaDois(Long dezenaDois) {
        this.dezenaDois = dezenaDois;
    }

    public Long getDezenaTres() {
        return dezenaTres;
    }

    public void setDezenaTres(Long dezenaTres) {
        this.dezenaTres = dezenaTres;
    }

    public Long getDezenaQuatro() {
        return dezenaQuatro;
    }

    public void setDezenaQuatro(Long dezenaQuatro) {
        this.dezenaQuatro = dezenaQuatro;
    }

    public Long getDezenaCinco() {
        return dezenaCinco;
    }

    public void setDezenaCinco(Long dezenaCinco) {
        this.dezenaCinco = dezenaCinco;
    }

    public Long getDezenaSeis() {
        return dezenaSeis;
    }

    public void setDezenaSeis(Long dezenaSeis) {
        this.dezenaSeis = dezenaSeis;
    }

    public Long getDezenaSete() {
        return dezenaSete;
    }

    public void setDezenaSete(Long dezenaSete) {
        this.dezenaSete = dezenaSete;
    }

    public Long getDezenaOito() {
        return dezenaOito;
    }

    public void setDezenaOito(Long dezenaOito) {
        this.dezenaOito = dezenaOito;
    }

    public Long getDezenaNove() {
        return dezenaNove;
    }

    public void setDezenaNove(Long dezenaNove) {
        this.dezenaNove = dezenaNove;
    }

    public Long getDezenaDez() {
        return dezenaDez;
    }

    public void setDezenaDez(Long dezenaDez) {
        this.dezenaDez = dezenaDez;
    }

    public Long getQtdAcertos() {
        return qtdAcertos;
    }

    public void setQtdAcertos(Long qtdAcertos) {
        this.qtdAcertos = qtdAcertos;
    }

    public ProfileResponse getUsuario() {
        return usuario;
    }

    public void setUsuario(ProfileResponse usuario) {
        this.usuario = usuario;
    }
}
