package com.hoffmann.lotecaatualizada.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.hoffmann.lotecaatualizada.MegaTola;
import com.hoffmann.lotecaatualizada.R;

public class Jogos extends Fragment {

    private Button megaTolaBotao;
    private String token, email;

    public Jogos() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (getArguments() != null) {
            email = args.getString("email");
            token = args.getString("token");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_jogos, container, false);
        megaTolaBotao = view.findViewById(R.id.game_mega_tola);

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
}