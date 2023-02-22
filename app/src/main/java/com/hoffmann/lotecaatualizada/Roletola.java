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
            roletola_numero_06, roletola_numero_07, roletola_numero_08, valor10, valor30, valor50;
    final int[] sectors = {1, 4, 7, 2, 8, 5, 3, 6};
    final int[] sectorDegress = new int[sectors.length];
    int index = 0;
    boolean spinning = false;
    private ImageView roleta, rodarRoleta;
    Random random = new Random();
    List<RoletolaStatusDto> apostas = new ArrayList<>();
    private RoletolaAdapter adapter;
    private RecyclerView recyclerView;
    private int numeroApostaAtual;
    private String valorSelecionado;

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

        botaoGirar.setOnClickListener(v -> {
            if (!spinning) {
                spin();
                spinning = true;
            }
        });

        rodarRoleta.setOnClickListener(v -> botaoGirar.performClick());

        valor10.setOnClickListener(v -> eventoBotoesValoresAposta(valor10));
        valor30.setOnClickListener(v -> eventoBotoesValoresAposta(valor30));
        valor50.setOnClickListener(v -> eventoBotoesValoresAposta(valor50));
    }

    private void eventoBotoesValoresAposta(Button botao) {
        List<Button> botoesValores = Arrays.asList(valor10, valor30, valor50);
        Button[] botoes = {roletola_numero_01, roletola_numero_02, roletola_numero_03, roletola_numero_04, roletola_numero_05,
                roletola_numero_06, roletola_numero_07, roletola_numero_08};

        for (Button botaoIteracao : botoesValores) {
            if (botao.equals(botaoIteracao)){
                botaoIteracao.setBackground((getApplication().getDrawable(R.drawable.shape_botao_redondo_selecionado)));
                botaoIteracao.setTextColor(getApplication().getColor(R.color.roxo));
                botaoIteracao.setClickable(false);
                valorSelecionado = botaoIteracao.getText().toString();

                for (Button botaoNumeroAposta : botoes) {
                    int teste = Integer.parseInt(botaoNumeroAposta.getText().toString());
                    if (teste == numeroApostaAtual) {
                        botaoGirar.setEnabled(true);
                        botaoGirar.setBackground(getApplication().getDrawable(R.drawable.botao_desativado_aposta));
                        botaoGirar.setTextColor(getApplication().getColor(R.color.roxo));
                        rodarRoleta.setEnabled(true);
                    }
                }
            } else {
                botaoIteracao.setClickable(false);
            }
        }
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
                roletola_numero_08);

        List<Button> botoesValores = Arrays.asList(valor10, valor30, valor50);

        for (Button botao : botoes) {
            if (!botao.isClickable()) {
                botao.setBackground((getApplication().getDrawable(R.drawable.shape_botao_redondo_normal)));
                botao.setTextColor(getApplication().getColor(R.color.white));
                botao.setClickable(true);
                botaoGirar.setTextColor(getApplication().getColor(R.color.white));
                botaoGirar.setEnabled(false);
                rodarRoleta.setEnabled(false);
            }

            for (Button botaoValor : botoesValores) {
                if (botaoValor.getText().equals(valorSelecionado)) {
                    botaoValor.setBackground((getApplication().getDrawable(R.drawable.shape_botao_normal)));
                    botaoValor.setTextColor(getApplication().getColor(R.color.white));
                    botaoValor.setClickable(true);
                } else {
                    botaoValor.setClickable(true);
                }
            }
            valorSelecionado = "";
            numeroApostaAtual = 0;
        }
    }


    private void regrasDeValidacao(Button botao) {

        Button[] botoes = {roletola_numero_01, roletola_numero_02, roletola_numero_03, roletola_numero_04, roletola_numero_05,
                roletola_numero_06, roletola_numero_07, roletola_numero_08};

        List<Button> botoesValores = Arrays.asList(valor10, valor30, valor50);

        for (Button botaoIteracao : botoes) {
            if (botao.getText().equals(botaoIteracao.getText())) {
                botaoIteracao.setBackground((getApplication().getDrawable(R.drawable.shape_botao_redondo_selecionado)));
                botaoIteracao.setTextColor(getApplication().getColor(R.color.roxo));
                botaoIteracao.setClickable(false);

                numeroApostaAtual = Integer.parseInt((String) botaoIteracao.getText());

                for (Button botaoValoresIteracao : botoesValores) {
                    if (botaoValoresIteracao.getText().equals(valorSelecionado)){
                        botaoGirar.setEnabled(true);
                        botaoGirar.setBackground(getApplication().getDrawable(R.drawable.botao_desativado_aposta));
                        botaoGirar.setTextColor(getApplication().getColor(R.color.roxo));
                        rodarRoleta.setEnabled(true);
                    }
                }
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
        valor10 = findViewById(R.id.botao_10_roletola);
        valor30 = findViewById(R.id.botao_30_roletola);
        valor50 = findViewById(R.id.botao_50_roletola);
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

                validaAPosta(valorDaRoleta, numeroApostaAtual);

                RoletolaStatusDto dto = new RoletolaStatusDto();
                dto.setNumeroSorteado(String.valueOf(valorDaRoleta));
                dto.setNumeroApostado(String.valueOf(numeroApostaAtual));
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