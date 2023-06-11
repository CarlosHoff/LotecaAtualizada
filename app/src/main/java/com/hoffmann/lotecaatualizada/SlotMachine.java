package com.hoffmann.lotecaatualizada;

import static com.hoffmann.lotecaatualizada.utilitario.Constantes.SCORE;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.hoffmann.lotecaatualizada.imageViewScrolling.IEventEnd;
import com.hoffmann.lotecaatualizada.imageViewScrolling.ImageViewScrolling;

import java.util.Random;

public class SlotMachine extends AppCompatActivity implements IEventEnd {

    private String token, email, nome, celular;
    ImageView btnUp, btnDown;
    ImageViewScrolling image1, image2, image3;
    TextView txtScore;
    Button btnVoltar;
    int countDone = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slot_machine);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        iniciaComponentes();

        token = getIntent().getStringExtra("token");
        email = getIntent().getStringExtra("email");
        nome = getIntent().getStringExtra("nome");
        celular = getIntent().getStringExtra("celular");

        image1.setEventEnd(SlotMachine.this);
        image2.setEventEnd(SlotMachine.this);
        image3.setEventEnd(SlotMachine.this);

        btnVoltar.setOnClickListener(v -> {
            Intent menu = new Intent(SlotMachine.this, Menu.class);
            menu.putExtra("token", token);
            menu.putExtra("email", email);
            menu.putExtra("nome", nome);
            menu.putExtra("celular", celular);
            startActivity(menu);
        });

        btnUp.setOnClickListener(v -> {
            if (SCORE >= 50) {
                btnUp.setVisibility(View.GONE);
                btnDown.setVisibility(View.VISIBLE);

                image1.setValueRandom(new Random().nextInt(6), new Random().nextInt(6));
                image2.setValueRandom(new Random().nextInt(6), new Random().nextInt(6));
                image3.setValueRandom(new Random().nextInt(6), new Random().nextInt(6));

                SCORE -= 50;

                txtScore.setText(String.valueOf(SCORE));
            } else {
                Toast.makeText(getApplicationContext(), "Você não tem dinheiro o suficiente.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void eventEnd(int result, int count) {
        if (countDone < 2) {
            countDone++;
        } else {
            btnDown.setVisibility(View.GONE);
            btnUp.setVisibility(View.VISIBLE);

            countDone = 0;

            if (image1.getValue() == image2.getValue() && image2.getValue() == image3.getValue()) {
                Toast.makeText(getApplicationContext(), "Vocē ganhou Bastante!!", Toast.LENGTH_SHORT).show();
                SCORE += 300;
                txtScore.setText(String.valueOf(SCORE));
            } else if (image1.getValue() == image2.getValue() || image2.getValue() == image3.getValue() || image1.getValue() == image3.getValue()) {
                Toast.makeText(getApplicationContext(), "Vocē ganhou Pouco!!", Toast.LENGTH_SHORT).show();
                SCORE += 100;
                txtScore.setText(String.valueOf(SCORE));
            } else {
                Toast.makeText(getApplicationContext(), "Vocē Perdeu!!", Toast.LENGTH_SHORT).show();
                SCORE -= 50;
                txtScore.setText(String.valueOf(SCORE));
            }
        }
    }

    private void iniciaComponentes() {
        btnUp = findViewById(R.id.btn_up);
        btnDown = findViewById(R.id.btn_down);
        btnVoltar = findViewById(R.id.btn_voltar);
        image1 = findViewById(R.id.image1);
        image2 = findViewById(R.id.image2);
        image3 = findViewById(R.id.image3);
        txtScore = findViewById(R.id.txt_score);
    }
}