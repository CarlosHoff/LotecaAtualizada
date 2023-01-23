package com.hoffmann.lotecaatualizada;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.hoffmann.lotecaatualizada.response.PerfilUsuario;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Perfil extends AppCompatActivity {

    private Button jogar, deslogar;
    private TextView nome, email, celular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        iniciarComponentes();

        jogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Perfil.this, Jogo.class);
                intent.putExtra("email", getIntent().getExtras().getString("email"));
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
        email = findViewById(R.id.email_perfil);
        celular = findViewById(R.id.celular_perfil);
    }

    @Override
    protected void onStart() {
        super.onStart();
        String email = getIntent().getExtras().getString("email");
        String token = getIntent().getExtras().getString("token");
        carregaPerfilUsuario(email, token);
    }

    private void carregaPerfilUsuario(String email, String token) {

        String uri = String.format("http://10.0.2.2:8091/v1/usuario/buscaUsuario?email=%1$s", email);
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET,
                uri,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            PerfilUsuario usuario = new PerfilUsuario();
                            usuario.setNome(response.getString("nome"));
                            usuario.setCelular(response.getString("celular"));
                            usuario.setEmail(response.getString("email"));
                            preencheTela(usuario);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }
        ) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<String, String>();
                headers.put("Authorization", token);
                return headers;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(request);
    }

    private void preencheTela(PerfilUsuario perfilUsuario) {
        nome.setText(perfilUsuario.getNome());
        email.setText(perfilUsuario.getEmail());
        celular.setText(perfilUsuario.getCelular());
    }

}