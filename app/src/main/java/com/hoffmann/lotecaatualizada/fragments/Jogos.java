package com.hoffmann.lotecaatualizada.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.hoffmann.lotecaatualizada.MegaTola;
import com.hoffmann.lotecaatualizada.R;
import com.hoffmann.lotecaatualizada.utilitario.SharedViewModel;

public class Jogos extends Fragment {

    private Button megaTolaBotao;
    private SharedViewModel model;
    private String token, email;

    public Jogos() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_jogos, container, false);

        megaTolaBotao = view.findViewById(R.id.game_mega_tola);

        model = new ViewModelProvider(getActivity()).get(SharedViewModel.class);
        model.getEmail().observe(getViewLifecycleOwner(), this::getEmail);
        model.getToken().observe(getViewLifecycleOwner(), this::getToken);

        megaTolaBotao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MegaTola.class);
                intent.putExtra("email", email);
                intent.putExtra("token", token);
                startActivity(intent);
            }
        });

        return view;
    }

    private void getToken(String token) {
        this.token = token;
    }

    private void getEmail(String email) {
        this.email = email;
    }
}