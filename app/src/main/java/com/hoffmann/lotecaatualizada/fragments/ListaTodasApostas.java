package com.hoffmann.lotecaatualizada.fragments;

import static com.hoffmann.lotecaatualizada.utilitario.Constantes.LOTECA_URL;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hoffmann.lotecaatualizada.R;
import com.hoffmann.lotecaatualizada.TelaErro01;
import com.hoffmann.lotecaatualizada.adapters.ListaDeApostasAdapter;
import com.hoffmann.lotecaatualizada.client.ApostaService;
import com.hoffmann.lotecaatualizada.domain.response.TodasApostasResponse;
import com.hoffmann.lotecaatualizada.utilitario.SharedViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ListaTodasApostas extends Fragment {

    private ListaDeApostasAdapter adapter;

    private RecyclerView recyclerView;
    private String token, email, textoBusca;
    private SharedViewModel model;
    private SearchView searchView;

    public ListaTodasApostas() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        model = new ViewModelProvider(getActivity()).get(SharedViewModel.class);
        model.getEmail().observe(getViewLifecycleOwner(), this::getEmail);
        model.getToken().observe(getViewLifecycleOwner(), this::getToken);

        View view = inflater.inflate(R.layout.fragment_lista_todas_apostas, container, false);
        recyclerView = view.findViewById(R.id.recicleViewTodasApostas);
        searchView = view.findViewById(R.id.search_view);
        return view;
    }

    private void carregaLista(String token){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(LOTECA_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApostaService listaApostas = retrofit.create(ApostaService.class);
        Call<List<TodasApostasResponse>> requestListaTodasApostas = listaApostas.todasApostas(token);

        requestListaTodasApostas.enqueue(new Callback<List<TodasApostasResponse>>() {
            @Override
            public void onResponse(Call<List<TodasApostasResponse>> call, Response<List<TodasApostasResponse>> response) {

                createAdapter(response.body());
                buscaApostas(response);
            }

            @Override
            public void onFailure(Call<List<TodasApostasResponse>> call, Throwable t) {
                startActivity(new Intent(getContext(), TelaErro01.class));
            }
        });
    }

    private void buscaApostas(Response<List<TodasApostasResponse>> response) {
        List<TodasApostasResponse> resultadosDaBusca = new ArrayList<>();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (!textoBusca.isEmpty()) {
                    resultadosDaBusca.clear();
                    for (TodasApostasResponse item : Objects.requireNonNull(response.body())) {
                        if (item.getUsuario().getApelido().equalsIgnoreCase(textoBusca)) {

                            resultadosDaBusca.add(item);
                        }
                    }
                    createAdapter(resultadosDaBusca);

                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                textoBusca = newText;
                return false;
            }
        });
    }

    private void createAdapter(List<TodasApostasResponse> todasApostasResponses) {
        adapter = new ListaDeApostasAdapter(getContext(), todasApostasResponses);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(),
                RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void getToken(String token) {
        this.token = token;
        if (token != null) {
            carregaLista(token);
        }
    }

    private void getEmail(String email) {
        this.email = email;
    }
}