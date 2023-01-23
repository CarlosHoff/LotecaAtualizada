package com.hoffmann.lotecaatualizada.response;

import java.io.Serializable;

public class ApostasUsuario implements Serializable {

    private Long[] dezenas;
    private boolean selected;

    public ApostasUsuario() {
    }

    public ApostasUsuario(Long[] dezenas) {
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
