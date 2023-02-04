package com.hoffmann.lotecaatualizada;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        TextView textLoteca = findViewById(R.id.text_loteca);
        Animation animationLoteca = AnimationUtils.loadAnimation(this, R.anim.text_loteca);
        textLoteca.startAnimation(animationLoteca);

        TextView textDo = findViewById(R.id.text_do);
        Animation animationDo = AnimationUtils.loadAnimation(this, R.anim.text_do);
        textDo.startAnimation(animationDo);

        TextView textBetola = findViewById(R.id.text_betola);
        Animation animationBetola = AnimationUtils.loadAnimation(this, R.anim.text_betola);
        textBetola.startAnimation(animationBetola);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(Splash.this, Login.class));
                }

        }, 3000);

    }
}