package com.hoffmann.lotecaatualizada.client;

import static com.hoffmann.lotecaatualizada.utilitario.Constantes.LOGIN;

import com.hoffmann.lotecaatualizada.domain.request.LoginRequest;
import com.hoffmann.lotecaatualizada.domain.response.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface LoginService {
    @POST(LOGIN)
    @Headers("Content-Type: application/json")
    Call<LoginResponse> login(@Body LoginRequest request);
}
