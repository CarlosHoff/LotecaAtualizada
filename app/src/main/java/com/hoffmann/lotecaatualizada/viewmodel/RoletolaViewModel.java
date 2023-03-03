package com.hoffmann.lotecaatualizada.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RoletolaViewModel extends ViewModel {
    private MutableLiveData<Integer> actualNumberBet = new MutableLiveData<>();
    private MutableLiveData<String> selectedValue = new MutableLiveData<>();
    private MutableLiveData<Boolean> spinning = new MutableLiveData<>();

    public void setActualBetNumber(int actualNumberBet) {
        this.actualNumberBet.setValue(actualNumberBet);
    }

    public void setSelectedValue(String selectedValue) {
        this.selectedValue.setValue(selectedValue);
    }

    public void setSpinning(boolean spinning) {
        this.spinning.setValue(spinning);
    }

    public LiveData<Integer> getActualNumberBet() {
        return actualNumberBet;
    }

    public LiveData<String> getSelectedValue() {
        return selectedValue;
    }

    public LiveData<Boolean> getSpinning() {
        return spinning;
    }
}
