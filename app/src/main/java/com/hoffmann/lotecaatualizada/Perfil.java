package com.hoffmann.lotecaatualizada;

import static com.hoffmann.lotecaatualizada.utilitario.Constantes.USER_URL;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.hoffmann.lotecaatualizada.client.UsuarioService;
import com.hoffmann.lotecaatualizada.domain.response.UsuarioResponse;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Perfil extends AppCompatActivity {

    private Button jogar, deslogar, listaTodasApostas;
    private TextView nome, emailTela, celular;
    private String token, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        iniciarComponentes();

        email = getIntent().getExtras().getString("email");
        token = getIntent().getExtras().getString("token");

        carregaPerfilUsuario(token, email);

        jogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Perfil.this, Jogo.class);
                intent.putExtra("email", getIntent().getExtras().getString("email"));
                intent.putExtra("token", getIntent().getExtras().getString("token"));
                startActivity(intent);
            }
        });

        listaTodasApostas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Perfil.this, ListaTodasApostas.class);
                intent.putExtra("token", getIntent().getExtras().getString("token"));
                startActivity(intent);
            }
        });

        deslogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Perfil.this, Login.class);
                startActivity(intent);
            }
        });

    }

    private void iniciarComponentes() {
        jogar = findViewById(R.id.botao_jogar);
        deslogar = findViewById(R.id.botao_deslogar);
        nome = findViewById(R.id.nome_perfil);
        emailTela = findViewById(R.id.email_perfil);
        celular = findViewById(R.id.celular_perfil);
        listaTodasApostas = findViewById(R.id.lista_todas_apostas);
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
                startActivity(new Intent(Perfil.this, TelaErro01.class));
            }
        });
    }

    private void preencheTela(UsuarioResponse usuarioResponse) {
        nome.setText(usuarioResponse.getNome());
        emailTela.setText(usuarioResponse.getEmail());
        celular.setText(usuarioResponse.getCelular());
    }
}