package com.hoffmann.lotecaatualizada;

import static com.hoffmann.lotecaatualizada.utilitario.Constantes.USER_URL;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.hoffmann.lotecaatualizada.client.UsuarioService;
import com.hoffmann.lotecaatualizada.domain.request.CadastraUsuarioRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Cadastro extends AppCompatActivity {

    private TextView nome, apelido, email, celular, cpf, senha;
    private Button botaoCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        iniciarComponentes();

        botaoCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestCadastro();
            }
        });
    }

    private void requestCadastro() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(USER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UsuarioService usuarioService = retrofit.create(UsuarioService.class);
        Call<Void> cadastraUsuarioRequest = usuarioService.cadastraUsuario(createUsuarioRequest());

        cadastraUsuarioRequest.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, retrofit2.Response<Void> response) {
                if (response.isSuccessful()) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(Cadastro.this, Login.class);
                            startActivity(intent);
                        }
                    }, 1000);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                startActivity(new Intent(Cadastro.this, TelaErro01.class));
            }
        });
    }

    private CadastraUsuarioRequest createUsuarioRequest() {
        CadastraUsuarioRequest request = new CadastraUsuarioRequest();
        request.setNome(nome.getText().toString());
        request.setApelido(apelido.getText().toString());
        request.setEmail(email.getText().toString());
        request.setCelular(celular.getText().toString());
        request.setCpf(cpf.getText().toString());
        request.setSenha(senha.getText().toString());
        return request;
    }

    private void iniciarComponentes() {
        nome = findViewById(R.id.nome_cadastro);
        apelido = findViewById(R.id.apelido_cadastro);
        email = findViewById(R.id.email_cadastro);
        celular = findViewById(R.id.celular_cadastro);
        cpf = findViewById(R.id.cpf_cadastro);
        senha = findViewById(R.id.senha_cadastro);
        botaoCadastrar = findViewById(R.id.botao_cadastro);
    }
}