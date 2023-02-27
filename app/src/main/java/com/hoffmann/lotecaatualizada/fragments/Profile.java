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
import androidx.lifecycle.ViewModelProvider;

import com.hoffmann.lotecaatualizada.Login;
import com.hoffmann.lotecaatualizada.R;
import com.hoffmann.lotecaatualizada.TelaErro01;
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
        startComponents(inflater.inflate(R.layout.fragment_profile, container, false));

        PerfilViewModel perfilViewModel = new ViewModelProvider(this).get(PerfilViewModel.class);
        perfilViewModel.loadProfile(token, email).observe(getViewLifecycleOwner(), profileResponse -> {
            if (profileResponse != null) {
                ProfileResponse user = new ProfileResponse();
                user.setName(Objects.requireNonNull(profileResponse.getName()));
                user.setSurname(Objects.requireNonNull(profileResponse.getSurname()));
                user.setCellphone(Objects.requireNonNull(profileResponse.getCellphone()));
                user.setEmail(Objects.requireNonNull(profileResponse.getEmail()));
                fillScreen(user);
            } else {
                startActivity(new Intent(getContext(), TelaErro01.class));
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
        name.setText(profileResponse.getName());
        emailScreen.setText(profileResponse.getEmail());
        cellphone.setText(profileResponse.getCellphone());
    }

    private void showAlertDialog() {
        Dialog dialog = utils.createAlertDialog(getActivity(), APLICATIVO_ALERTA, DESLOGAR, SIM);
        dialog.show();
        TextView positiveButton = dialog.findViewById(R.id.botao_positive);
        positiveButton.setOnClickListener(v -> dialog.dismiss());

        TextView negativeButton = dialog.findViewById(R.id.botao_negative);
        negativeButton.setOnClickListener(v -> startActivity(new Intent(getContext(), Login.class)));
    }

}