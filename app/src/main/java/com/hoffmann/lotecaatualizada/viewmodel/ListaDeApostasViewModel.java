package com.hoffmann.lotecaatualizada.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.hoffmann.lotecaatualizada.domain.dto.ApostasUsuarioDto;

import java.util.Collections;
import java.util.List;

public class ListaDeApostasViewModel extends ViewModel {
    private MutableLiveData<List<ApostasUsuarioDto>> mCartelaDeApostas = new MutableLiveData<>();

    public void setCartelaDeApostas(List<ApostasUsuarioDto> cartelaDeApostas) {
        mCartelaDeApostas.setValue(cartelaDeApostas);
    }

    public LiveData<List<ApostasUsuarioDto>> getCartelaDeApostas() {
        return mCartelaDeApostas;
    }

    public void deleteSelectedItems(List<Integer> selectedItems) {
        List<ApostasUsuarioDto> apostas = mCartelaDeApostas.getValue();
        for (int position : selectedItems) {
            apostas.remove(position);
        }
        mCartelaDeApostas.setValue(apostas);
    }

    public void swapItems(int from, int to) {
        List<ApostasUsuarioDto> apostas = mCartelaDeApostas.getValue();
        Collections.swap(apostas, from, to);
        mCartelaDeApostas.setValue(apostas);
    }
}
