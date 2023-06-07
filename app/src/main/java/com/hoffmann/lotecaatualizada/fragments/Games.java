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

import com.hoffmann.lotecaatualizada.Niquel;
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

        megaTolaBotao.setOnClickListener(v -> goMegaTola());
        roletola.setOnClickListener(v -> goFragment());
        niquel.setOnClickListener(v -> goActivity(Niquel.class));

        return view;
    }

    private void goMegaTola() {
        Fragment megatola = new MegaTola();
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_games, megatola);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private void goFragment() {
        Fragment roleta = new Roleta();
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_games, roleta);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private void goActivity(Class<?> activityClass) {
        Intent intent = new Intent(getActivity(), activityClass);
        intent.putExtra("email", email);
        intent.putExtra("token", token);
        intent.putExtra("nome", nome);
        intent.putExtra("celular", celular);
        startActivity(intent);
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