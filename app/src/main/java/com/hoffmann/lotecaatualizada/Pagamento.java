package com.hoffmann.lotecaatualizada;

import static com.hoffmann.lotecaatualizada.utilitario.Constantes.LOTECA_URL;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.hoffmann.lotecaatualizada.client.ApostaService;
import com.hoffmann.lotecaatualizada.domain.dto.ApostasUsuarioDto;
import com.hoffmann.lotecaatualizada.domain.request.ApostaRequest;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Pagamento extends AppCompatActivity {
    private List<ApostasUsuarioDto> cartelaDeApostasFinal;
    private String email, token;

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

        ApostaService apostaService = retrofit.create(ApostaService.class);
        Call<Void> apostaRequest = apostaService.cadastraApostas(createApostaRequest());

        apostaRequest.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, retrofit2.Response<Void> response) {
                startActivity(new Intent(Pagamento.this, TelaSucesso.class));
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

    private ApostaRequest createApostaRequest() {
        ApostaRequest request = new ApostaRequest();
        request.setNumeros(converteRequestParaOCore(cartelaDeApostasFinal));
        request.setEmail(email);
        return request;
    }

    private List<Long[]> converteRequestParaOCore(List<ApostasUsuarioDto> cartelaDeApostasFinal) {
        List<Long[]> listaDeApostas = new ArrayList<>();
        for (ApostasUsuarioDto apostasUsuarioDto : cartelaDeApostasFinal) {
            Long[] mapper = {
                    apostasUsuarioDto.getDezenas()[0],
                    apostasUsuarioDto.getDezenas()[1],
                    apostasUsuarioDto.getDezenas()[2],
                    apostasUsuarioDto.getDezenas()[3],
                    apostasUsuarioDto.getDezenas()[4],
                    apostasUsuarioDto.getDezenas()[5],
                    apostasUsuarioDto.getDezenas()[6],
                    apostasUsuarioDto.getDezenas()[7],
                    apostasUsuarioDto.getDezenas()[8],
                    apostasUsuarioDto.getDezenas()[9]};
            listaDeApostas.add(mapper);
        }
        return listaDeApostas;
    }

    @Override
    protected void onStart() {
        super.onStart();
        token = getIntent().getExtras().getString("token");
        email = getIntent().getExtras().getString("email");
        Bundle bundle = getIntent().getExtras();
        cartelaDeApostasFinal = (List<ApostasUsuarioDto>) bundle.getSerializable("cartelaDeApostasFinal");
    }

}