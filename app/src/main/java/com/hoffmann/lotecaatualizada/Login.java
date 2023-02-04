package com.hoffmann.lotecaatualizada;

import static com.hoffmann.lotecaatualizada.utilitario.Constantes.LOTECA_URL;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.hoffmann.lotecaatualizada.client.LoginService;
import com.hoffmann.lotecaatualizada.domain.request.LoginRequest;
import com.hoffmann.lotecaatualizada.domain.response.LoginResponse;
import com.hoffmann.lotecaatualizada.fragments.Perfil;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Login extends AppCompatActivity {

    private EditText email, senha;
    private Button botaoEntrar;
    private TextView textoCadastrar;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        iniciarComponentes();

        textoCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, Cadastro.class);
                startActivity(intent);
            }
        });

        botaoEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestLogin();
            }
        });

    }

    private void requestLogin() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(LOTECA_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        LoginService loginService = retrofit.create(LoginService.class);
        Call<LoginResponse> loginRequest = loginService.login(new LoginRequest(email.getText().toString(), senha.getText().toString()));

        loginRequest.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, retrofit2.Response<LoginResponse> response) {
                if (response.isSuccessful()){
                    progressBar.setVisibility(View.VISIBLE);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent menu = new Intent(Login.this, Menu.class);
                            menu.putExtra("token", Objects.requireNonNull(response.body()).getToken());
                            menu.putExtra("email", response.body().getNome());
                            startActivity(menu);
                        }
                    }, 1000);
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                startActivity(new Intent(Login.this, TelaErro01.class));
            }
        });

    }

    private void iniciarComponentes() {
        email = findViewById(R.id.email_login);
        senha = findViewById(R.id.senha_login);
        botaoEntrar = findViewById(R.id.botao_entrar);
        textoCadastrar = findViewById(R.id.texto_cadastro);
        progressBar = findViewById(R.id.progress_bar);
    }
}