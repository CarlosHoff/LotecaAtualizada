package com.hoffmann.lotecaatualizada.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.hoffmann.lotecaatualizada.repository.RegisterRepository;

public class RegisterViewModel extends ViewModel {

    private final RegisterRepository registerRepository;

    public RegisterViewModel() {
        registerRepository = new RegisterRepository();
    }

    public LiveData<Void> register(String nome, String apelido, String email, String celular, String cpf, String senha){
        return registerRepository.register(nome, apelido, email, celular, cpf, senha);
    }
}
