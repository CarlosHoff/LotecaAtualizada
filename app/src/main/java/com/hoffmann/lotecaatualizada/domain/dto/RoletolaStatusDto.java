package com.hoffmann.lotecaatualizada.domain.dto;

import java.io.Serializable;

public class RoletolaStatusDto implements Serializable {

    private String index;
    private String ganhos;

    public RoletolaStatusDto() {
    }

    public RoletolaStatusDto(String index, String ganhos) {
        this.index = index;
        this.ganhos = ganhos;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getGanhos() {
        return ganhos;
    }

    public void setGanhos(String ganhos) {
        this.ganhos = ganhos;
    }
}
