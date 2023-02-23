package com.hoffmann.lotecaatualizada;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Random;

public class Niquel extends AppCompatActivity {

    private Button slot1, slot2, slot3, botaoApostar;
    Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_niquel);

        iniciaComponentes();

        botaoApostar.setOnClickListener(v -> {
            int valor1 = random.nextInt(7);
            int valor2 = random.nextInt(7);
            int valor3 = random.nextInt(7);

            animateSlot(slot1, valor1);
            animateSlot(slot2, valor2);
            animateSlot(slot3, valor3);
        });
    }

    private void animateSlot(View slot, int value) {
        slot.setBackground(getImage(value));
        AnimatorSet animacao = (AnimatorSet) AnimatorInflater.loadAnimator(Niquel.this, R.animator.fade_in);
        animacao.setTarget(slot);
        animacao.start();
    }

    private Drawable getImage(int valorImagem) {

        Drawable fundo;

        switch (valorImagem) {
            case 1:
                fundo = AppCompatResources.getDrawable(this, R.drawable.n1);
                break;
            case 2:
                fundo = AppCompatResources.getDrawable(this, R.drawable.n2);
                break;
            case 3:
                fundo = AppCompatResources.getDrawable(this, R.drawable.n3);
                break;
            case 4:
                fundo = AppCompatResources.getDrawable(this, R.drawable.n4);
                break;
            case 5:
                fundo = AppCompatResources.getDrawable(this, R.drawable.n5);
                break;
            case 6:
                fundo = AppCompatResources.getDrawable(this, R.drawable.n6);
                break;
            case 7:
                fundo = AppCompatResources.getDrawable(this, R.drawable.n7);
                break;
            default:
                fundo = AppCompatResources.getDrawable(this, R.drawable.n0);
                break;
        }
        return fundo;
    }

    private void iniciaComponentes() {
        slot1 = findViewById(R.id.slot1);
        slot2 = findViewById(R.id.slot2);
        slot3 = findViewById(R.id.slot3);
        botaoApostar = findViewById(R.id.botao_apostar);
    }
}