package com.hoffmann.lotecaatualizada.fragments;

import static android.content.Context.MODE_PRIVATE;
import static com.hoffmann.lotecaatualizada.utilitario.Constantes.APLICATIVO_ALERTA;
import static com.hoffmann.lotecaatualizada.utilitario.Constantes.DESLOGAR;
import static com.hoffmann.lotecaatualizada.utilitario.Constantes.FIRST_ACCESS_FRAGMENT;
import static com.hoffmann.lotecaatualizada.utilitario.Constantes.PROFILE;
import static com.hoffmann.lotecaatualizada.utilitario.Constantes.SIM;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.hoffmann.lotecaatualizada.Login;
import com.hoffmann.lotecaatualizada.R;
import com.hoffmann.lotecaatualizada.domain.response.ProfileResponse;
import com.hoffmann.lotecaatualizada.utilitario.SharedViewModel;
import com.hoffmann.lotecaatualizada.utilitario.Utils;
import com.hoffmann.lotecaatualizada.viewmodel.PerfilViewModel;

import java.util.Objects;

public class Profile extends Fragment {

    private TextView name, emailScreen, cellphone;
    private String email, token;
    private final Utils utils = new Utils();
    SharedPreferences sharedPreferences;

    public Profile() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedPreferences = requireActivity().getSharedPreferences(PROFILE, MODE_PRIVATE);
        if (!sharedPreferences.getBoolean(FIRST_ACCESS_FRAGMENT, false)) {
            showAlertDialog();
        }
        sharedPreferences.edit().putBoolean(FIRST_ACCESS_FRAGMENT, true).apply();

        SharedViewModel model = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        email = model.getEmail().getValue();
        token = model.getToken().getValue();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        startComponents(view);

        PerfilViewModel perfilViewModel = new ViewModelProvider(this).get(PerfilViewModel.class);
        perfilViewModel.loadProfile(token, email).observe(getViewLifecycleOwner(), profileResponse -> {
            if (profileResponse != null) {
                ProfileResponse user = new ProfileResponse();
                SharedViewModel model = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

                model.setNome(profileResponse.getApelido());
                model.setCelular(profileResponse.getCelular());

                user.setNome(Objects.requireNonNull(profileResponse.getApelido()));
                user.setCelular(Objects.requireNonNull(profileResponse.getApelido()));
                user.setEmail(Objects.requireNonNull(email));
                fillScreen(user);
            } else {
                replaceFragment(new ErrorScreen());
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (email == null || token == null) {
            SharedViewModel model = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
            email = model.getEmail().getValue();
            token = model.getToken().getValue();
        }
    }

    private void startComponents(View view) {
        name = view.findViewById(R.id.nome_perfil);
        emailScreen = view.findViewById(R.id.email_perfil);
        cellphone = view.findViewById(R.id.celular_perfil);
    }

    private void fillScreen(ProfileResponse profileResponse) {
        name.setText(profileResponse.getNome());
        emailScreen.setText(profileResponse.getEmail());
        cellphone.setText(profileResponse.getCelular());
    }

    private void showAlertDialog() {
        Dialog dialog = utils.createAlertDialog(getActivity(), APLICATIVO_ALERTA, DESLOGAR, SIM);
        dialog.show();
        TextView positiveButton = dialog.findViewById(R.id.botao_positive);
        positiveButton.setOnClickListener(v -> dialog.dismiss());

        TextView negativeButton = dialog.findViewById(R.id.botao_negative);
        negativeButton.setOnClickListener(v -> startActivity(new Intent(getContext(), Login.class)));
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_perfil, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

}