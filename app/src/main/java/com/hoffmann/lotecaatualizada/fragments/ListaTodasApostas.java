package com.hoffmann.lotecaatualizada.fragments;

import static com.hoffmann.lotecaatualizada.utilitario.Constantes.LOTECA_URL;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hoffmann.lotecaatualizada.R;
import com.hoffmann.lotecaatualizada.adapters.ListaDeApostasAdapter;
import com.hoffmann.lotecaatualizada.client.ApostaService;
import com.hoffmann.lotecaatualizada.domain.response.TodasApostasResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ListaTodasApostas extends Fragment {

    private ListaDeApostasAdapter adapter;

    private RecyclerView recyclerView;
    private String token, email;

    public ListaTodasApostas() {
        // Required empty public constructor
    }

//    public static ListaTodasApostas newInstance(String param1, String param2) {
//        ListaTodasApostas fragment = new ListaTodasApostas();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            email = args.getString("email");
            token = args.getString("token");
        }

        carregaLista();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lista_todas_apostas, container, false);
        recyclerView = view.findViewById(R.id.recicleViewTodasApostas);
        return view;
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

                adapter = new ListaDeApostasAdapter(getContext(), response.body());
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
}