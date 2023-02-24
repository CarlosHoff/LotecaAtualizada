package com.hoffmann.lotecaatualizada.fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.hoffmann.lotecaatualizada.MegaTola;
import com.hoffmann.lotecaatualizada.Niquel;
import com.hoffmann.lotecaatualizada.R;
import com.hoffmann.lotecaatualizada.Roletola;
import com.hoffmann.lotecaatualizada.utilitario.SharedViewModel;

import java.util.Objects;

public class Jogos extends Fragment {

    private Button megaTolaBotao, roletola, niquel;
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
        roletola = view.findViewById(R.id.game_roletola);
        niquel = view.findViewById(R.id.game_niquel);

        model = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        model.getEmail().observe(getViewLifecycleOwner(), this::getEmail);
        model.getToken().observe(getViewLifecycleOwner(), this::getToken);

        megaTolaBotao.setOnClickListener(v -> abrirActivity(MegaTola.class));
        roletola.setOnClickListener(v -> abrirActivity(Roletola.class));
        niquel.setOnClickListener(v -> abrirActivity(Niquel.class));

        return view;
    }

    private void abrirActivity(Class<?> activityClass) {
        Intent intent = new Intent(getActivity(), activityClass);
        intent.putExtra("email", email);
        intent.putExtra("token", token);
        startActivity(intent);
    }

    private void getToken(String token) {
        this.token = token;
    }

    private void getEmail(String email) {
        this.email = email;
    }
}