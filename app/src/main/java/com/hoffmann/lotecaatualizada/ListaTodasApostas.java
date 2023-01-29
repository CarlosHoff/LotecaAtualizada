package com.hoffmann.lotecaatualizada;

import static com.hoffmann.lotecaatualizada.utilitario.Constantes.LOTECA_URL;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hoffmann.lotecaatualizada.client.ApostaService;
import com.hoffmann.lotecaatualizada.domain.response.TodasApostasResponse;
import com.hoffmann.lotecaatualizada.adapters.ListaDeApostasAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListaTodasApostas extends AppCompatActivity {

    private ListaDeApostasAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_todas_apostas);
        iniciaComponentes();

        carregaLista();
    }

    private void iniciaComponentes() {
        recyclerView = findViewById(R.id.recicleViewTodasApostas);
    }

    private void carregaLista(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(LOTECA_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApostaService listaApostas = retrofit.create(ApostaService.class);
        Call<List<TodasApostasResponse>> requestListaTodasApostas = listaApostas.todasApostas();

        requestListaTodasApostas.enqueue(new Callback<List<TodasApostasResponse>>() {
            @Override
            public void onResponse(Call<List<TodasApostasResponse>> call, Response<List<TodasApostasResponse>> response) {

                adapter = new ListaDeApostasAdapter(ListaTodasApostas.this, response.body());
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ListaTodasApostas.this,
                        RecyclerView.VERTICAL, false);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<TodasApostasResponse>> call, Throwable t) {
            }
        });
    }

}