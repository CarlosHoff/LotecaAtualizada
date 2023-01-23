package com.hoffmann.lotecaatualizada;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
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

public class Login extends AppCompatActivity {

    private EditText email, senha;
    private Button botaoEntrar;
    private TextView textoCadastrar;
    private ProgressBar progressBar;
    private RequestQueue queue;

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
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST,
                Utils.LOGIN_URL,
                createRequestUser(),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        progressBar.setVisibility(View.VISIBLE);

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent intent = new Intent(Login.this, Perfil.class);
                                try {
                                    String token = response.getString("token");
                                    intent.putExtra("token", token);
                                    intent.putExtra("email", createRequestUser().getString("username"));
                                    startActivity(intent);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, 2000);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Login.this, "Erro ao logar", Toast.LENGTH_LONG).show();
                    }
                }
        ) {
        };
        queue.add(request);
    }

    private void iniciarComponentes() {
        email = findViewById(R.id.email_login);
        senha = findViewById(R.id.senha_login);
        botaoEntrar = findViewById(R.id.botao_entrar);
        textoCadastrar = findViewById(R.id.texto_cadastro);
        progressBar = findViewById(R.id.progress_bar);
        queue = Volley.newRequestQueue(Login.this);
    }

    private JSONObject createRequestUser(){
        JSONObject object = new JSONObject();
        try {
            object.put("username", email.getText().toString());
            object.put("password", senha.getText().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object;
    }


}