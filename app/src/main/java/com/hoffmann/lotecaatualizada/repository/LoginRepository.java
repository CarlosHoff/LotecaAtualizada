package com.hoffmann.lotecaatualizada.repository;

import static com.hoffmann.lotecaatualizada.utilitario.Constantes.LOTECA_URL;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.hoffmann.lotecaatualizada.client.LoginService;
import com.hoffmann.lotecaatualizada.domain.request.LoginRequest;
import com.hoffmann.lotecaatualizada.domain.response.LoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginRepository {

    private LoginService loginService;

    public LoginRepository() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(LOTECA_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        loginService = retrofit.create(LoginService.class);
    }

    public LiveData<LoginResponse> login(String email, String senha) {
        MutableLiveData<LoginResponse> loginResponseLiveData = new MutableLiveData<>();
        Call<LoginResponse> loginRequest = loginService.login(new LoginRequest(email, senha));
        loginRequest.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()) {
                    loginResponseLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                loginResponseLiveData.setValue(null);
            }
        });
        return loginResponseLiveData;
    }
}
