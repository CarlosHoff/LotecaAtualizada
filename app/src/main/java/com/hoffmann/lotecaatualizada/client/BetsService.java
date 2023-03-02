package com.hoffmann.lotecaatualizada.client;

import static com.hoffmann.lotecaatualizada.utilitario.Constantes.BUSCA_LISTA_DE_APOSTAS;
import static com.hoffmann.lotecaatualizada.utilitario.Constantes.BUSCA_MINHAS_APOSTAS;
import static com.hoffmann.lotecaatualizada.utilitario.Constantes.CADASTRA_APOSTA;

import com.hoffmann.lotecaatualizada.domain.request.BetRequest;
import com.hoffmann.lotecaatualizada.domain.response.AllBetsResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface BetsService {

    @GET(BUSCA_LISTA_DE_APOSTAS)
    @Headers("Content-Type: application/json")
    Call<List<AllBetsResponse>> getAllBets(
            @Header("Authorization") String token
    );

    @GET(BUSCA_MINHAS_APOSTAS)
    @Headers("Content-Type: application/json")
    Call<List<AllBetsResponse>> getMyBets(
            @Header("Authorization") String token,
            @Query("email") String email);

    @POST(CADASTRA_APOSTA)
    @Headers("Content-Type: application/json")
    Call<Void> registerBet(
            @Header("Authorization") String token,
            @Body BetRequest request);

}
