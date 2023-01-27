package com.hoffmann.lotecaatualizada;

import static com.hoffmann.lotecaatualizada.utilitario.Utils.CADASTRA_APOSTA;
import static com.hoffmann.lotecaatualizada.utilitario.Utils.LOTECA_URL;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.hoffmann.lotecaatualizada.response.ApostasUsuario;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Pagamento extends AppCompatActivity {
    private List<ApostasUsuario> cartelaDeApostasFinal;
    private RequestQueue queue;
    private String email, token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagamento);

        queue = Volley.newRequestQueue(this);
        Button botaoPagamento = findViewById(R.id.botao_pagar);
        botaoPagamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestCadastraAposta();
                startActivity(new Intent(Pagamento.this, TelaSucesso.class));
            }
        });
    }

    private void requestCadastraAposta() {
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST,
                LOTECA_URL + CADASTRA_APOSTA,
                createJsonRequest(),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        startActivity(new Intent(Pagamento.this, TelaSucesso.class));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.getMessage();
                    }
                }
        ){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<String, String>();
                headers.put("Authorization", token);
                return headers;
            }
        };
        queue.add(request);
    }

    private JSONObject createJsonRequest() {

        JSONObject object = new JSONObject();
        try {
            object.put("numeros", new JSONArray(converteRequestParaOCore(cartelaDeApostasFinal)));
            object.put("email", email);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object;
    }

    private List<Long[]> converteRequestParaOCore(List<ApostasUsuario> cartelaDeApostasFinal) {
        List<Long[]> listaDeApostas = new ArrayList<>();
        for (ApostasUsuario apostasUsuario : cartelaDeApostasFinal) {
            Long[] mapper = {
                    apostasUsuario.getDezenas()[0],
                    apostasUsuario.getDezenas()[1],
                    apostasUsuario.getDezenas()[2],
                    apostasUsuario.getDezenas()[3],
                    apostasUsuario.getDezenas()[4],
                    apostasUsuario.getDezenas()[5],
                    apostasUsuario.getDezenas()[6],
                    apostasUsuario.getDezenas()[7],
                    apostasUsuario.getDezenas()[8],
                    apostasUsuario.getDezenas()[9]};
            listaDeApostas.add(mapper);
        }
        return listaDeApostas;
    }

    @Override
    protected void onStart() {
        super.onStart();
        token = getIntent().getExtras().getString("token");
        email = getIntent().getExtras().getString("email");
        Bundle bundle = getIntent().getExtras();
        cartelaDeApostasFinal = (List<ApostasUsuario>) bundle.getSerializable("cartelaDeApostasFinal");
    }

}