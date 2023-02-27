package com.hoffmann.lotecaatualizada;

import static com.hoffmann.lotecaatualizada.utilitario.Constantes.REGISTER_SUCCESS;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.hoffmann.lotecaatualizada.viewmodel.RegisterViewModel;

public class Cadastro extends AppCompatActivity {
    private TextView name, surname, email, cellphone, cpf, password;
    private Button registerButton;
    private RegisterViewModel registerViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        startComponents();

        registerViewModel = new ViewModelProvider(this).get(RegisterViewModel.class);
        registerButton.setOnClickListener(view -> {
            registerViewModel.register(name.getText().toString(), surname.getText().toString(),
                    email.getText().toString(), cellphone.getText().toString(), cpf.getText().toString(), password.getText().toString());
            Toast.makeText(Cadastro.this, REGISTER_SUCCESS, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Cadastro.this, Login.class);
            startActivity(intent);
        });
    }

    private void startComponents() {
        name = findViewById(R.id.nome_cadastro);
        surname = findViewById(R.id.apelido_cadastro);
        email = findViewById(R.id.email_cadastro);
        cellphone = findViewById(R.id.celular_cadastro);
        cpf = findViewById(R.id.cpf_cadastro);
        password = findViewById(R.id.senha_cadastro);
        registerButton = findViewById(R.id.botao_cadastro);
    }

}