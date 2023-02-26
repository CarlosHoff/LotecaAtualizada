package com.hoffmann.lotecaatualizada.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.hoffmann.lotecaatualizada.domain.response.LoginResponse;
import com.hoffmann.lotecaatualizada.repository.LoginRepository;

public class LoginViewModel extends ViewModel {

    private LoginRepository loginRepository;

    public LoginViewModel() {
        loginRepository = new LoginRepository();
    }

    public LiveData<LoginResponse> login(String email, String senha) {
        return loginRepository.login(email, senha);
    }
}
