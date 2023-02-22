package com.hoffmann.lotecaatualizada;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hoffmann.lotecaatualizada.adapters.RoletolaAdapter;
import com.hoffmann.lotecaatualizada.domain.dto.RoletolaStatusDto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Roletola extends AppCompatActivity {

    private Button botaoGirar, roletola_numero_01, roletola_numero_02, roletola_numero_03, roletola_numero_04, roletola_numero_05,
            roletola_numero_06, roletola_numero_07, roletola_numero_08, roletola_numero_09, roletola_numero_10;
    final int[] sectors = {1, 4, 7, 2, 8, 5, 3, 6};
    final int[] sectorDegress = new int[sectors.length];
    int index = 0;
    boolean spinning = false;
    private ImageView roleta, rodarRoleta;
    Random random = new Random();
    List<RoletolaStatusDto> apostas = new ArrayList<>();
    private RoletolaAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roletola);

        iniciaComponentes();

        generateSectorDegress();

        roletola_numero_01.setOnClickListener(v -> {
            regrasDeValidacao(roletola_numero_01);
        });

        roletola_numero_02.setOnClickListener(v -> {
            regrasDeValidacao(roletola_numero_02);
        });

        roletola_numero_03.setOnClickListener(v -> {
            regrasDeValidacao(roletola_numero_03);
        });

        roletola_numero_04.setOnClickListener(v -> {
            regrasDeValidacao(roletola_numero_04);
        });

        roletola_numero_05.setOnClickListener(v -> {
            regrasDeValidacao(roletola_numero_05);
        });

        roletola_numero_06.setOnClickListener(v -> {
            regrasDeValidacao(roletola_numero_06);
        });

        roletola_numero_07.setOnClickListener(v -> {
            regrasDeValidacao(roletola_numero_07);
        });

        roletola_numero_08.setOnClickListener(v -> {
            regrasDeValidacao(roletola_numero_08);
        });

        roletola_numero_09.setOnClickListener(v -> {
            regrasDeValidacao(roletola_numero_09);
        });

        roletola_numero_10.setOnClickListener(v -> {
            regrasDeValidacao(roletola_numero_10);
        });

        botaoGirar.setOnClickListener(v -> {
            if (!spinning) {
                spin();
                spinning = true;
            }
        });

        rodarRoleta.setOnClickListener(v -> botaoGirar.performClick());
    }


    private void resetaBotoes() {
        List<Button> botoes = Arrays.asList(
                roletola_numero_01,
                roletola_numero_02,
                roletola_numero_03,
                roletola_numero_04,
                roletola_numero_05,
                roletola_numero_06,
                roletola_numero_07,
                roletola_numero_08,
                roletola_numero_09,
                roletola_numero_10);

        for (Button botao : botoes) {
            if (!botao.isClickable()) {
                botao.setClickable(true);
                botao.setBackground((getApplication().getDrawable(R.drawable.shape_botao_redondo_normal)));
                botao.setTextColor(getApplication().getColor(R.color.white));
                botao.setClickable(true);
                botaoGirar.setTextColor(getApplication().getColor(R.color.white));
                botaoGirar.setEnabled(false);
                rodarRoleta.setEnabled(false);
            }
        }
    }


    private void regrasDeValidacao(Button botao) {

        Button[] botoes = {roletola_numero_01, roletola_numero_02, roletola_numero_03, roletola_numero_04, roletola_numero_05,
                roletola_numero_06, roletola_numero_07, roletola_numero_08, roletola_numero_09, roletola_numero_10};

        for (Button botaoIteracao : botoes) {
            if (botao.getText().equals(botaoIteracao.getText())) {
                botaoIteracao.setBackground((getApplication().getDrawable(R.drawable.shape_botao_redondo_selecionado)));
                botaoIteracao.setTextColor(getApplication().getColor(R.color.roxo));
                botaoIteracao.setClickable(false);

                botaoGirar.setEnabled(true);
                botaoGirar.setBackground(getApplication().getDrawable(R.drawable.botao_desativado_aposta));
                botaoGirar.setTextColor(getApplication().getColor(R.color.roxo));

                rodarRoleta.setEnabled(true);
            } else {
                botaoIteracao.setClickable(false);
            }
        }
    }

    private void iniciaComponentes() {
        roleta = findViewById(R.id.roleta);
        botaoGirar = findViewById(R.id.girar);
        rodarRoleta = findViewById(R.id.rodar_roleta);
        rodarRoleta.setEnabled(false);
        recyclerView = findViewById(R.id.roletolaRecicleViewId);
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


                RoletolaStatusDto dto = new RoletolaStatusDto();
                dto.setIndex(String.valueOf(numeroApostado));
                dto.setGanhos(String.valueOf(numeroApostado));
                apostas.add(dto);

                adapter = new RoletolaAdapter(Roletola.this, apostas);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(Roletola.this,
                        RecyclerView.HORIZONTAL, false);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();


                resetaBotoes();

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
        int apostNumber = 0;
        for (int i = 1; i <= 10; i++) {
            if (!getRoletolaNumero(i).isClickable()) {
                apostNumber = i;
                break;
            }
        }
        return apostNumber;
    }

    private Button getRoletolaNumero(int i) {
        switch (i) {
            case 1:
                return roletola_numero_01;
            case 2:
                return roletola_numero_02;
            case 3:
                return roletola_numero_03;
            case 4:
                return roletola_numero_04;
            case 5:
                return roletola_numero_05;
            case 6:
                return roletola_numero_06;
            case 7:
                return roletola_numero_07;
            case 8:
                return roletola_numero_08;
            case 9:
                return roletola_numero_09;
            case 10:
                return roletola_numero_10;
            default:
                throw new IllegalArgumentException("Número de roletola inválido: " + i);
        }
    }

    private int geraNumeroRandom() {
        return (360 * sectors.length) + sectorDegress[index];
    }

    private void generateSectorDegress() {
        int sectorDegree = 360 / sectors.length;

        for (int i = 0; i < sectors.length; i++) {
            sectorDegress[i] = (i + 1) * sectorDegree;
        }
    }
}