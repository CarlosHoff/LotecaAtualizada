package com.hoffmann.lotecaatualizada.domain.dto;

import java.io.Serializable;

public class ApostasUsuarioDto implements Serializable {

    private Long[] dezenas;
    private boolean selected;

    public ApostasUsuarioDto() {
    }

    public ApostasUsuarioDto(Long[] dezenas) {
        this.dezenas = dezenas;
    }

    public Long[] getDezenas() {
        return dezenas;
    }

    public void setDezenas(Long[] dezenas) {
        this.dezenas = dezenas;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
