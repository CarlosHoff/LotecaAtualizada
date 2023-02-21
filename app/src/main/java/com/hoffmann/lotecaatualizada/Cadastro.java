package com.hoffmann.lotecaatualizada;

import static com.hoffmann.lotecaatualizada.utilitario.Constantes.APELIDO;
import static com.hoffmann.lotecaatualizada.utilitario.Constantes.CELULAR;
import static com.hoffmann.lotecaatualizada.utilitario.Constantes.CPF;
import static com.hoffmann.lotecaatualizada.utilitario.Constantes.EMAIL;
import static com.hoffmann.lotecaatualizada.utilitario.Constantes.NOME;
import static com.hoffmann.lotecaatualizada.utilitario.Constantes.NOT_NULL;
import static com.hoffmann.lotecaatualizada.utilitario.Constantes.REGISTER_SUCCESS;
import static com.hoffmann.lotecaatualizada.utilitario.Constantes.SENHA;
import static com.hoffmann.lotecaatualizada.utilitario.Constantes.USER_URL;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.SpannableString;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
                if (validarCampos()) {
                    requestCadastro();
                }
            }
        });
    }

    private boolean validarCampos() {
        TextView[] fields = {nome, apelido, email, celular, cpf, senha};
        String[] fieldNames = {NOME, APELIDO, EMAIL, CELULAR, CPF, SENHA};

        for (int i = 0; i < fields.length; i++) {
            TextView field = fields[i];
            String fieldValue = field.getText().toString().trim();

            if (TextUtils.isEmpty(fieldValue)) {
                Toast.makeText(this, fieldNames[i] + NOT_NULL, Toast.LENGTH_SHORT).show();
                return false;
            }
        }

        return true;
    }

    private void requestCadastro() {
        validateInputs();
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
                            Toast.makeText(Cadastro.this, REGISTER_SUCCESS, Toast.LENGTH_SHORT).show();
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
        TextView[] fields = {nome, apelido, email, celular, cpf, senha};
        String[] fieldNames = {NOME, APELIDO, EMAIL, CELULAR, CPF, SENHA};

        for (int i = 0; i < fields.length; i++) {
            TextView field = fields[i];
            String fieldValue = field.getText().toString().trim();

            SpannableString errorMessage = new SpannableString(" " + NOT_NULL);
            field.setError(errorMessage);

            if (fieldValue.isEmpty()) {
                field.setError(fieldNames[i] + errorMessage);
            } else {
                field.setError(null);
                switch (i) {
                    case 0:
                        request.setNome(fieldValue);
                        break;
                    case 1:
                        request.setApelido(fieldValue);
                        break;
                    case 2:
                        request.setEmail(fieldValue);
                        break;
                    case 3:
                        request.setCelular(fieldValue);
                        break;
                    case 4:
                        request.setCpf(fieldValue);
                        break;
                    case 5:
                        request.setSenha(fieldValue);
                        break;
                }
            }
        }

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

    private void validateInputs() {
        TextView[] fields = {nome, apelido, email, celular, cpf, senha};
        String[] fieldNames = {NOME, APELIDO, EMAIL, CELULAR, CPF, SENHA};

        for (int i = 0; i < fields.length; i++) {
            TextView field = fields[i];
            String fieldValue = field.getText().toString().trim();

            if (fieldValue.isEmpty()) {
                field.setError(fieldNames[i] + " " + NOT_NULL);
            } else {
                field.setError(null);
            }
        }
    }
}