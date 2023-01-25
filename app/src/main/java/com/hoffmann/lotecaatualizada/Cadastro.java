package com.hoffmann.lotecaatualizada;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.hoffmann.lotecaatualizada.utilitario.Utils;

import org.json.JSONException;
import org.json.JSONObject;

public class Cadastro extends AppCompatActivity {

    private TextView nome, email, celular, cpf, senha;
    private Button botaoCadastrar;
    private RequestQueue queue;

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

    private void requestCadastro(){
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST,
                Utils.USER_URL + Utils.CADASTRO,
                createRequestCadastro(),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(Cadastro.this, "Cadastro efetuado com sucesso, faça o login !!", Toast.LENGTH_LONG).show();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent intent = new Intent(Cadastro.this, Login.class);
                                startActivity(intent);
                            }
                        }, 1000);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Intent intent = new Intent(Cadastro.this, TelaErro01.class);
                        startActivity(intent);
                    }
                }
        ){
        };
        queue.add(request);
    }

    private JSONObject createRequestCadastro() {
        JSONObject object = new JSONObject();
        try {
            object.put("nome", nome.getText().toString());
            object.put("email", email.getText().toString());
            object.put("celular", celular.getText().toString());
            object.put("cpf", cpf.getText().toString());
            object.put("senha", senha.getText().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object;
    }

    private void iniciarComponentes() {
        nome = findViewById(R.id.nome_cadastro);
        email = findViewById(R.id.email_cadastro);
        celular = findViewById(R.id.celular_cadastro);
        cpf = findViewById(R.id.cpf_cadastro);
        senha = findViewById(R.id.senha_cadastro);
        botaoCadastrar = findViewById(R.id.botao_cadastro);
        queue = Volley.newRequestQueue(this);
    }
}