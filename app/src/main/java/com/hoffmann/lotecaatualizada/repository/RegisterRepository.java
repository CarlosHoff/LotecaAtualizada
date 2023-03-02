package com.hoffmann.lotecaatualizada.repository;

import static com.hoffmann.lotecaatualizada.utilitario.Constantes.USER_URL;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.hoffmann.lotecaatualizada.client.UserService;
import com.hoffmann.lotecaatualizada.domain.request.UserRegisterRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterRepository {

    private final UserService userService;

    public RegisterRepository() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(USER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        userService = retrofit.create(UserService.class);
    }

    public LiveData<Void> register(String nome, String apelido, String email, String celular, String cpf, String senha){
        MutableLiveData<Void> registerResponseLiveData = new MutableLiveData<>();
        Call<Void> userRequest = userService.registerUser(createUserRequest(nome, apelido, email, celular, cpf, senha));
        userRequest.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    registerResponseLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                registerResponseLiveData.setValue(null);
            }
        });
        return registerResponseLiveData;
    }

    private UserRegisterRequest createUserRequest(String nome, String apelido, String email, String celular, String cpf, String senha) {
        UserRegisterRequest request = new UserRegisterRequest();
        String[] fields = {nome, apelido, email, celular, cpf, senha};

        for (int i = 0; i < fields.length; i++) {
            String fieldValue = fields[i].trim();
                switch (i) {
                    case 0:
                        request.setName(fieldValue);
                        break;
                    case 1:
                        request.setApelido(fieldValue);
                        break;
                    case 2:
                        request.setEmail(fieldValue);
                        break;
                    case 3:
                        request.setCelular(fieldValue);
                        break;
                    case 4:
                        request.setCpf(fieldValue);
                        break;
                    case 5:
                        request.setSenha(fieldValue);
                        break;
                }
        }
        return request;
    }
}
