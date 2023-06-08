package com.hoffmann.lotecaatualizada.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.hoffmann.lotecaatualizada.R;

public class SucessScreen extends Fragment {

    private String token, email, nome, celular;

    public SucessScreen() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            token = getArguments().getString("token");
            email = getArguments().getString("email");
            nome = getArguments().getString("nome");
            celular = getArguments().getString("celular");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sucess_screen, container, false);

        new Handler().postDelayed(() -> replaceFragment(new Profile()), 5000);
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

        fragmentTransaction.replace(R.id.fragment_sucess_screen, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}