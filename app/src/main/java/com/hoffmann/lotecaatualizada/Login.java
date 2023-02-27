package com.hoffmann.lotecaatualizada;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.hoffmann.lotecaatualizada.viewmodel.LoginViewModel;

public class Login extends AppCompatActivity {

    private LoginViewModel loginViewModel;
    private EditText email, password;
    private Button enterButton;
    private TextView registerButton;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        startComponents();

        registerButton.setOnClickListener(view -> {
            Intent intent = new Intent(Login.this, Cadastro.class);
            startActivity(intent);
        });

        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        enterButton.setOnClickListener(view -> loginViewModel.login(email.getText().toString(), password.getText().toString()).observe(Login.this, loginResponse -> {
            if (loginResponse != null) {
                progressBar.setVisibility(View.VISIBLE);
                new Handler().postDelayed(() -> {
                    Intent menu = new Intent(Login.this, Menu.class);
                    menu.putExtra("token", loginResponse.getToken());
                    menu.putExtra("email", loginResponse.getNome());
                    startActivity(menu);
                }, 1000);
            } else {
                startActivity(new Intent(Login.this, TelaErro01.class));
            }
        }));
    }

    private void startComponents() {
        email = findViewById(R.id.email_login);
        password = findViewById(R.id.senha_login);
        enterButton = findViewById(R.id.botao_entrar);
        registerButton = findViewById(R.id.texto_cadastro);
        progressBar = findViewById(R.id.progress_bar);
    }
}