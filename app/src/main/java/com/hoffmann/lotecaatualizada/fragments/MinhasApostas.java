package com.hoffmann.lotecaatualizada.fragments;

import static com.hoffmann.lotecaatualizada.utilitario.Constantes.LOTECA_URL;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hoffmann.lotecaatualizada.R;
import com.hoffmann.lotecaatualizada.adapters.MinhasApostasAdapter;
import com.hoffmann.lotecaatualizada.client.ApostaService;
import com.hoffmann.lotecaatualizada.domain.response.TodasApostasResponse;
import com.hoffmann.lotecaatualizada.utilitario.SharedViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MinhasApostas extends Fragment {

    private MinhasApostasAdapter adapter;
    private RecyclerView recyclerView;
    private SharedViewModel model;
    private String token, email;

    public MinhasApostas() {
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


        View view = inflater.inflate(R.layout.fragment_minhas_apostas, container, false);
        recyclerView = view.findViewById(R.id.recicleViewMinhasApostas);
        return view;
    }


    private void carregaApostas(String token, String email) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(LOTECA_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApostaService listaApostas = retrofit.create(ApostaService.class);
        Call<List<TodasApostasResponse>> requestListaMinhasApostas = listaApostas.minhasApostas(token, email);
        requestListaMinhasApostas.enqueue(new Callback<List<TodasApostasResponse>>() {
            @Override
            public void onResponse(Call<List<TodasApostasResponse>> call, Response<List<TodasApostasResponse>> response) {
                adapter = new MinhasApostasAdapter(getContext(), response.body());
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(),
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

    private void getToken(String token) {
        this.token = token;
        if (token != null) {
            carregaApostas(token, email);
        }
    }

    private void getEmail(String email) {
        this.email = email;
    }
}