package com.hoffmann.lotecaatualizada.utilitario;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SharedViewModel extends ViewModel {
    private final MutableLiveData<String> email = new MutableLiveData<>();
    private final MutableLiveData<String> token = new MutableLiveData<>();
    private final MutableLiveData<String> nome = new MutableLiveData<>();
    private final MutableLiveData<String> celular = new MutableLiveData<>();

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

    public void setNome(String nome) {
        this.nome.setValue(nome);
    }
    public MutableLiveData<String> getNome() {
        return nome;
    }

    public void setCelular(String celular) {
        this.celular.setValue(celular);
    }
    public MutableLiveData<String> getCelular() {
        return celular;
    }
}
