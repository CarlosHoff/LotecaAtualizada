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

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class Roletola extends AppCompatActivity {

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

        roleta = findViewById(R.id.roleta);

        Button botaoGirar = findViewById(R.id.girar);

        generateSectorDegress();

        botaoGirar.setOnClickListener(v -> {
            if (!spinning) {
                spin();
                spinning = true;
            }
        });
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
                Log.i("Valor roleta:", String.valueOf(valorDaRoleta));
                spinning = false;
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });

        roleta.startAnimation(rotate);
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