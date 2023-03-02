package com.hoffmann.lotecaatualizada.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hoffmann.lotecaatualizada.R;
import com.hoffmann.lotecaatualizada.adapters.MyBetsAdapter;
import com.hoffmann.lotecaatualizada.utilitario.SharedViewModel;
import com.hoffmann.lotecaatualizada.viewmodel.MyBetsViewModel;

import java.util.ArrayList;

public class MyBets extends Fragment {

    private MyBetsAdapter adapter;
    private RecyclerView recyclerView;
    private String token, email;

    public MyBets() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedViewModel model = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        token = model.getToken().getValue();
        email = model.getEmail().getValue();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        MyBetsViewModel viewModel = new ViewModelProvider(this).get(MyBetsViewModel.class);
        View view = inflater.inflate(R.layout.fragment_minhas_apostas, container, false);

        recyclerView = view.findViewById(R.id.recicleViewMinhasApostas);

        setupRecyclerView();

        viewModel.getBets(token, email).observe(getViewLifecycleOwner(), allBetsResponses -> {
            adapter.setMyBets(allBetsResponses);
        });

        return view;
    }


    private void setupRecyclerView() {
        adapter = new MyBetsAdapter(getContext(), new ArrayList<>());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

}