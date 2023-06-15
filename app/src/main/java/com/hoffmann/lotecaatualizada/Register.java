package com.hoffmann.lotecaatualizada;

import static com.hoffmann.lotecaatualizada.utilitario.Constantes.REGISTER_SUCCESS;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.hoffmann.lotecaatualizada.fragments.ErrorScreen;
import com.hoffmann.lotecaatualizada.viewmodel.RegisterViewModel;

public class Register extends AppCompatActivity {
    private TextView name, surname, email, cellphone, cpf, password;
    private Button registerButton;
    private RegisterViewModel registerViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        startComponents();

        registerViewModel = new ViewModelProvider(this).get(RegisterViewModel.class);
        registerButton.setOnClickListener(view -> registerViewModel.register(name.getText().toString(), surname.getText().toString(),
                email.getText().toString(), cellphone.getText().toString(), cpf.getText().toString(), password.getText().toString()).observe(Register.this, registerResponse -> {
            if (registerResponse) {
                Toast.makeText(Register.this, REGISTER_SUCCESS, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Register.this, Login.class);
                startActivity(intent);
            } else {
                registerButton.setVisibility(View.INVISIBLE);
                ErrorScreen errorScreen = new ErrorScreen();
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                int containerId = R.id.activity_cadastro;
                fragmentTransaction.replace(containerId, errorScreen);
                fragmentTransaction.commit();
            }}));
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