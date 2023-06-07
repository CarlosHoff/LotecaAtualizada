package com.hoffmann.lotecaatualizada;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class TelaErro01 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_erro01);

        new Handler().postDelayed(() -> startActivity(new Intent(TelaErro01.this, Login.class)), 5000);

    }
}