package com.hoffmann.lotecaatualizada.fragments;

import static com.hoffmann.lotecaatualizada.utilitario.Constantes.LOTECA_URL;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.hoffmann.lotecaatualizada.R;
import com.hoffmann.lotecaatualizada.TelaErro01;
import com.hoffmann.lotecaatualizada.TelaSucesso;
import com.hoffmann.lotecaatualizada.client.BetsService;
import com.hoffmann.lotecaatualizada.domain.dto.BetUserDto;
import com.hoffmann.lotecaatualizada.domain.request.BetRequest;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Pagamento extends Fragment {

    private List<BetUserDto> cartelaDeApostasFinal;
    private String email, token, nome, celular;

    public Pagamento() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            token = getArguments().getString("token");
            email = getArguments().getString("email");
            nome = getArguments().getString("nome");
            celular = getArguments().getString("celular");
            cartelaDeApostasFinal = getArguments().getParcelableArrayList("cartelaDeApostasFinal");
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pagamento, container, false);

        Button botaoPagamento = view.findViewById(R.id.botao_pagar);
        botaoPagamento.setOnClickListener(v -> {
            requestCadastraAposta();
            startActivity(new Intent(requireContext(), TelaSucesso.class));
        });

        return view;
    }

    private void requestCadastraAposta() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(LOTECA_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        BetsService betsService = retrofit.create(BetsService.class);
        Call<Void> apostaRequest = betsService.registerBet(token, createApostaRequest());

        apostaRequest.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, retrofit2.Response<Void> response) {
                Intent intent = new Intent(requireContext(), TelaSucesso.class);
                intent.putExtra("token", token);
                intent.putExtra("email", email);
                intent.putExtra("nome", nome);
                intent.putExtra("celular", celular);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Intent intent = new Intent(requireContext(), TelaErro01.class);
                startActivity(intent);
            }
        });
    }

    private BetRequest createApostaRequest() {
        BetRequest request = new BetRequest();
        request.setNumeros(converteRequestParaOCore(cartelaDeApostasFinal));
        request.setEmail(email);
        return request;
    }

    private List<Long[]> converteRequestParaOCore(List<BetUserDto> cartelaDeApostasFinal) {
        List<Long[]> listaDeApostas = new ArrayList<>();
        for (BetUserDto betUserDto : cartelaDeApostasFinal) {
            Long[] mapper = {
                    betUserDto.getDezenas()[0],
                    betUserDto.getDezenas()[1],
                    betUserDto.getDezenas()[2],
                    betUserDto.getDezenas()[3],
                    betUserDto.getDezenas()[4],
                    betUserDto.getDezenas()[5],
                    betUserDto.getDezenas()[6],
                    betUserDto.getDezenas()[7],
                    betUserDto.getDezenas()[8],
                    betUserDto.getDezenas()[9]};
            listaDeApostas.add(mapper);
        }
        return listaDeApostas;
    }

}