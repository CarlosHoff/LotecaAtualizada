package com.hoffmann.lotecaatualizada.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.hoffmann.lotecaatualizada.R;
import com.hoffmann.lotecaatualizada.utilitario.SharedViewModel;

public class Games extends Fragment {

    private String token, email, nome, celular;

    public Games() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_jogos, container, false);

        Button megaTolaBotao = view.findViewById(R.id.game_mega_tola);
        Button roletola = view.findViewById(R.id.game_roletola);
        Button niquel = view.findViewById(R.id.game_niquel);

        SharedViewModel model = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        model.getEmail().observe(getViewLifecycleOwner(), this::getEmail);
        model.getToken().observe(getViewLifecycleOwner(), this::getToken);
        model.getNome().observe(getViewLifecycleOwner(), this::getName);
        model.getCelular().observe(getViewLifecycleOwner(), this::getCelular);

        megaTolaBotao.setOnClickListener(v -> replaceFragment(new MegaTola()));
        roletola.setOnClickListener(v -> replaceFragment(new Roleta()));
        niquel.setOnClickListener(v -> replaceFragment(new SlotMachine()));

        return view;
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Bundle args = new Bundle();
        args.putString("email", email);
        args.putString("token", token);
        args.putString("nome", nome);
        args.putString("celular", celular);
        fragment.setArguments(args);
        fragmentTransaction.replace(R.id.fragment_games, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private void getToken(String token) {
        this.token = token;
    }

    private void getEmail(String email) {
        this.email = email;
    }

    private void getName(String nome) {
        this.nome = nome;
    }

    private void getCelular(String celular) {
        this.celular = celular;
    }
}