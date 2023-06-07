package com.hoffmann.lotecaatualizada;

import static com.hoffmann.lotecaatualizada.utilitario.Constantes.LOTECA_URL;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.hoffmann.lotecaatualizada.client.BetsService;
import com.hoffmann.lotecaatualizada.domain.dto.BetUserDto;
import com.hoffmann.lotecaatualizada.domain.request.BetRequest;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Pagamento extends AppCompatActivity {
    private List<BetUserDto> cartelaDeApostasFinal;
    private String email, token, nome, celular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagamento);

        Button botaoPagamento = findViewById(R.id.botao_pagar);
        botaoPagamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestCadastraAposta();
                startActivity(new Intent(Pagamento.this, TelaSucesso.class));
            }
        });
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
                Intent intent = new Intent(Pagamento.this, TelaSucesso.class);
                intent.putExtra("token", token);
                intent.putExtra("email", email);
                intent.putExtra("nome", nome);
                intent.putExtra("celular", celular);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Intent intent = new Intent(Pagamento.this, TelaErro01.class);
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

    @Override
    protected void onStart() {
        super.onStart();
        Bundle bundle = getIntent().getExtras();
        token = bundle.getString("token");
        email = bundle.getString("email");
        nome = bundle.getString("nome");
        celular = bundle.getString("celular");
        cartelaDeApostasFinal = bundle.getParcelableArrayList("cartelaDeApostasFinal");
    }

}