package com.hoffmann.lotecaatualizada.utilitario;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SharedViewModel extends ViewModel {
    private final MutableLiveData<String> email = new MutableLiveData<>();
    private final MutableLiveData<String> token = new MutableLiveData<>();

    public void setEmail(String email) {
        this.email.setValue(email);
    }

    public void setToken(String token) {
        this.token.setValue(token);
    }

    public LiveData<String> getEmail() {
        return email;
    }

    public LiveData<String> getToken() {
        return token;
    }

}
