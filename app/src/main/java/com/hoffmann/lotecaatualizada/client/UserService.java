package com.hoffmann.lotecaatualizada.client;

import static com.hoffmann.lotecaatualizada.utilitario.Constantes.BUSCA_USUARIO;
import static com.hoffmann.lotecaatualizada.utilitario.Constantes.CADASTRA_USUARIO;

import com.hoffmann.lotecaatualizada.domain.response.ProfileResponse;
import com.hoffmann.lotecaatualizada.domain.request.UserRegisterRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface UserService {

    @POST(CADASTRA_USUARIO)
    Call<Void> cadastraUsuario(
            @Body UserRegisterRequest request);

    @GET(BUSCA_USUARIO)
    @Headers("Content-Type: application/json")
    Call<ProfileResponse> buscaUsuario(
            @Header("Authorization") String token,
            @Query("email") String email);
}
