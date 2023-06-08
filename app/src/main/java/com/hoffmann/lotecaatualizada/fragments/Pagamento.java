package com.hoffmann.lotecaatualizada.fragments;

import static com.hoffmann.lotecaatualizada.utilitario.Constantes.LOTECA_URL;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.hoffmann.lotecaatualizada.R;
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
            replaceFragment(new SucessScreen());
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
                if (response.isSuccessful()) {
                    replaceFragment(new SucessScreen());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                replaceFragment(new ErrorScreen());
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

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Bundle args = new Bundle();
        args.putString("email", email);
        args.putString("token", token);
        args.putString("nome", nome);
        args.putString("celular", celular);
        fragment.setArguments(args);
        fragmentTransaction.replace(R.id.fragment_pagamento, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

}