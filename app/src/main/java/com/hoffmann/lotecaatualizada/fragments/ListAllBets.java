package com.hoffmann.lotecaatualizada.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hoffmann.lotecaatualizada.R;
import com.hoffmann.lotecaatualizada.adapters.ListAllBetsAdapter;
import com.hoffmann.lotecaatualizada.utilitario.SharedViewModel;
import com.hoffmann.lotecaatualizada.viewmodel.ListAllBetsViewModel;

import java.util.ArrayList;

public class ListAllBets extends Fragment {
    private String token, queryGlobal;
    private ListAllBetsAdapter adapter;
    private RecyclerView recyclerView;
    private SearchView searchView;
    private ListAllBetsViewModel viewModel;


    public ListAllBets() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedViewModel model = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        token = model.getToken().getValue();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(ListAllBetsViewModel.class);
        View view = inflater.inflate(R.layout.fragment_lista_todas_apostas, container, false);

        recyclerView = view.findViewById(R.id.recicleViewTodasApostas);
        searchView = view.findViewById(R.id.home_search);
        setupRecyclerView();
        viewModel.getBets(token).observe(getViewLifecycleOwner(), AllBetsResponses -> {
            adapter.setAllBetsResponseList(AllBetsResponses);
            adapter.notifyDataSetChanged();
        });
        setupSearchView();

        return view;
    }

    private void setupRecyclerView() {
        adapter = new ListAllBetsAdapter(getContext(), new ArrayList<>());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void setupSearchView() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                queryGlobal = query;
                viewModel.searchBets(token, queryGlobal);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.isEmpty()) {
                    viewModel.getBets(token).observe(getViewLifecycleOwner(), allBetsResponses -> {
                        adapter.setAllBetsResponseList(allBetsResponses);
                        adapter.notifyDataSetChanged();
                    });
                }
                return false;
            }
        });

        viewModel.searchBets(token, queryGlobal);
        adapter.setAllBetsResponseList(viewModel.searchBets(token, queryGlobal));

    }
}