package com.hoffmann.lotecaatualizada;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.hoffmann.lotecaatualizada.fragments.Perfil;

public class TelaSucesso extends AppCompatActivity {

    private Button botaoHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_sucesso);

        botaoHome = findViewById(R.id.botao_home);

        botaoHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TelaSucesso.this, Menu.class);
                intent.putExtra("token", getIntent().getExtras().getString("token"));
                intent.putExtra("email", getIntent().getExtras().getString("email"));
                startActivity(intent);
            }
        });
    }
}