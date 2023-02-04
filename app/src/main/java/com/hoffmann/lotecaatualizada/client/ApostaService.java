package com.hoffmann.lotecaatualizada.client;

import static com.hoffmann.lotecaatualizada.utilitario.Constantes.BUSCA_LISTA_DE_APOSTAS;
import static com.hoffmann.lotecaatualizada.utilitario.Constantes.BUSCA_MINHAS_APOSTAS;
import static com.hoffmann.lotecaatualizada.utilitario.Constantes.CADASTRA_APOSTA;

import com.hoffmann.lotecaatualizada.domain.request.ApostaRequest;
import com.hoffmann.lotecaatualizada.domain.response.TodasApostasResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApostaService {

    @GET(BUSCA_LISTA_DE_APOSTAS)
    Call<List<TodasApostasResponse>> todasApostas(
            @Header("Authorization") String token
    );

    @GET(BUSCA_MINHAS_APOSTAS)
    Call<List<TodasApostasResponse>> minhasApostas(
            @Header("Authorization") String token,
            @Query("email") String email);

    @POST(CADASTRA_APOSTA)
    Call<Void> cadastraApostas(
            @Header("Authorization") String token,
            @Body ApostaRequest request);

}
