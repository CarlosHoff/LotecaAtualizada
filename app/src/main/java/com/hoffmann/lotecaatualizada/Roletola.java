package com.hoffmann.lotecaatualizada;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class Roletola extends AppCompatActivity {

    private Button botaoGirar, roletola_numero_01, roletola_numero_02, roletola_numero_03, roletola_numero_04, roletola_numero_05,
    roletola_numero_06, roletola_numero_07, roletola_numero_08, roletola_numero_09, roletola_numero_10;
    final int[] sectors = {1, 4, 7, 2, 8, 5, 3, 6};
    final int[] sectorDegress = new int[sectors.length];
    int index = 0;
    boolean spinning = false;
    private ImageView roleta;
    Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roletola);

        iniciaComponentes();

        generateSectorDegress();

        roletola_numero_01.setOnClickListener(v -> {regrasDeValidacao(roletola_numero_01);});

        roletola_numero_02.setOnClickListener(v -> {regrasDeValidacao(roletola_numero_02);});

        roletola_numero_03.setOnClickListener(v -> {regrasDeValidacao(roletola_numero_03);});

        roletola_numero_04.setOnClickListener(v -> {regrasDeValidacao(roletola_numero_04);});

        roletola_numero_05.setOnClickListener(v -> {regrasDeValidacao(roletola_numero_05);});

        roletola_numero_06.setOnClickListener(v -> {regrasDeValidacao(roletola_numero_06);});

        roletola_numero_07.setOnClickListener(v -> {regrasDeValidacao(roletola_numero_07);});

        roletola_numero_08.setOnClickListener(v -> {regrasDeValidacao(roletola_numero_08);});

        roletola_numero_09.setOnClickListener(v -> {regrasDeValidacao(roletola_numero_09);});

        roletola_numero_10.setOnClickListener(v -> {regrasDeValidacao(roletola_numero_10);});

        botaoGirar.setOnClickListener(v -> {
            if (!spinning) {
                spin();
                spinning = true;
            }
        });
    }

    private void regrasDeValidacao(Button botao) {

        if (!roletola_numero_01.isClickable() || !roletola_numero_02.isClickable() || !roletola_numero_03.isClickable() ||
                !roletola_numero_04.isClickable() || !roletola_numero_05.isClickable() || !roletola_numero_06.isClickable() ||
                !roletola_numero_07.isClickable() || !roletola_numero_08.isClickable() || !roletola_numero_09.isClickable() ||
                !roletola_numero_10.isClickable()) {
            botao.setEnabled(false);
        } else {
            botao.setBackground((getApplication().getDrawable(R.drawable.shape_botao_redondo_selecionado)));
            botao.setTextColor(getApplication().getColor(R.color.roxo));
            botao.setClickable(false);

            botaoGirar.setEnabled(true);
            botaoGirar.setBackground(getApplication().getDrawable(R.drawable.botao_desativado_aposta));
            botaoGirar.setTextColor(getApplication().getColor(R.color.roxo));
        }
    }

    private void iniciaComponentes() {
        roleta = findViewById(R.id.roleta);
        botaoGirar = findViewById(R.id.girar);
        roletola_numero_01 = findViewById(R.id.roletola_01);
        roletola_numero_02 = findViewById(R.id.roletola_02);
        roletola_numero_03 = findViewById(R.id.roletola_03);
        roletola_numero_04 = findViewById(R.id.roletola_04);
        roletola_numero_05 = findViewById(R.id.roletola_05);
        roletola_numero_06 = findViewById(R.id.roletola_06);
        roletola_numero_07 = findViewById(R.id.roletola_07);
        roletola_numero_08 = findViewById(R.id.roletola_08);
        roletola_numero_09 = findViewById(R.id.roletola_09);
        roletola_numero_10 = findViewById(R.id.roletola_10);
    }

    private void spin() {
        index = random.nextInt(sectors.length);

        int randomValor = geraNumeroRandom();

        RotateAnimation rotate = new RotateAnimation(0, randomValor, RotateAnimation.RELATIVE_TO_SELF,
                0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);

        rotate.setDuration(3600);
        rotate.setFillAfter(true);
        rotate.setInterpolator(new DecelerateInterpolator());
        rotate.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                int valorDaRoleta = sectors[sectors.length - (index + 1)];

                int numeroApostado = getApostNumber();

                validaAPosta(valorDaRoleta, numeroApostado);

                spinning = false;
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });

        roleta.startAnimation(rotate);
    }

    private void validaAPosta(int valorDaRoleta, int numeroApostado) {
        if (valorDaRoleta == numeroApostado) {
            Toast.makeText(this, "Você ganhou !!!", Toast.LENGTH_SHORT).show();
        }
    }

    private int getApostNumber() {

        if (!roletola_numero_01.isClickable()){
            return 1;
        } else if (!roletola_numero_02.isClickable()) {
            return 2;
        } else if (!roletola_numero_03.isClickable()) {
            return 3;
        } else if (!roletola_numero_04.isClickable()) {
            return 4;
        } else if (!roletola_numero_05.isClickable()) {
            return 5;
        } else if (!roletola_numero_06.isClickable()) {
            return 6;
        } else if (!roletola_numero_07.isClickable()) {
            return 7;
        } else if (!roletola_numero_08.isClickable()) {
            return 8;
        } else if (!roletola_numero_09.isClickable()) {
            return 9;
        } else {
            return 10;
        }
    }

    private int geraNumeroRandom() {
        return (360 * sectors.length) + sectorDegress[index];
    }

    private void generateSectorDegress() {
        int sectorDegree = 360/sectors.length;

        for (int i = 0; i < sectors.length; i++) {
            sectorDegress[i] = (i+1) * sectorDegree;
        }
    }
}