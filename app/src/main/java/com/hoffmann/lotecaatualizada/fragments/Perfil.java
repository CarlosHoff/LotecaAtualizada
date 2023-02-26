package com.hoffmann.lotecaatualizada.fragments;

import static com.hoffmann.lotecaatualizada.utilitario.Constantes.APLICATIVO_ALERTA;
import static com.hoffmann.lotecaatualizada.utilitario.Constantes.DESLOGAR;
import static com.hoffmann.lotecaatualizada.utilitario.Constantes.SIM;
import static com.hoffmann.lotecaatualizada.utilitario.Constantes.USER_URL;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.hoffmann.lotecaatualizada.Login;
import com.hoffmann.lotecaatualizada.R;
import com.hoffmann.lotecaatualizada.TelaErro01;
import com.hoffmann.lotecaatualizada.client.UsuarioService;
import com.hoffmann.lotecaatualizada.domain.response.UsuarioResponse;
import com.hoffmann.lotecaatualizada.utilitario.SharedViewModel;
import com.hoffmann.lotecaatualizada.utilitario.Utils;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Perfil extends Fragment {

    private TextView nome, emailTela, celular;
    private String token, email;
    private SharedViewModel model;

    private Utils utils = new Utils();

    public Perfil() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Dialog dialog = utils.createAlertDialog(getActivity(), APLICATIVO_ALERTA, SIM, DESLOGAR);
        dialog.show();
        TextView botaoPositivo = dialog.findViewById(R.id.botao_positive);
        botaoPositivo.setOnClickListener(v -> dialog.dismiss());

        // TODO CRIAR ENDPOINT PARA DESABILITAR O JWT
        TextView botaoNegativo = dialog.findViewById(R.id.botao_negative);
        botaoNegativo.setOnClickListener(v -> startActivity(new Intent(getContext(), Login.class)));

        model = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        model.getEmail().observe(getViewLifecycleOwner(), this::getEmail);
        model.getToken().observe(getViewLifecycleOwner(), this::getToken);

        View view = inflater.inflate(R.layout.fragment_perfil, container, false);
        nome = view.findViewById(R.id.nome_perfil);
        emailTela = view.findViewById(R.id.email_perfil);
        celular = view.findViewById(R.id.celular_perfil);

        return view;
    }

    private void carregaPerfilUsuario(String token, String email) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(USER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UsuarioService usuarioService = retrofit.create(UsuarioService.class);
        Call<UsuarioResponse> requestCadastro = usuarioService.buscaUsuario(token, email);

        requestCadastro.enqueue(new Callback<UsuarioResponse>() {
            @Override
            public void onResponse(Call<UsuarioResponse> call, Response<UsuarioResponse> response) {
                if (response.isSuccessful()) {
                    UsuarioResponse usuario = new UsuarioResponse();
                    usuario.setNome(Objects.requireNonNull(response.body()).getNome());
                    usuario.setApelido(Objects.requireNonNull(response.body().getApelido()));
                    usuario.setCelular(Objects.requireNonNull(response.body().getCelular()));
                    usuario.setEmail(Objects.requireNonNull(response.body().getEmail()));
                    preencheTela(usuario);
                }
            }

            @Override
            public void onFailure(Call<UsuarioResponse> call, Throwable t) {
                startActivity(new Intent(getContext(), TelaErro01.class));
            }
        });
    }

    private void preencheTela(UsuarioResponse usuarioResponse) {
        nome.setText(usuarioResponse.getNome());
        emailTela.setText(usuarioResponse.getEmail());
        celular.setText(usuarioResponse.getCelular());
    }

    private void getToken(String token) {
        this.token = token;
        if (token != null) {
            carregaPerfilUsuario(token, email);
        }
    }

    private void getEmail(String email) {
        this.email = email;
    }
}