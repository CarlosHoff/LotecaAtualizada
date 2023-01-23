package com.hoffmann.lotecaatualizada;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.hoffmann.lotecaatualizada.response.ApostasUsuario;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Jogo extends AppCompatActivity {

    private final Long VALOR_APOSTA_UNICA = 50L;
    private TextView valorTotalAposta;
    private Button finalizarApostas, botaoApostar, um, dois, tres, quatro, cinco, seis, sete,
            oito, nove, dez, onze, doze, treze, quatorze,
            quinze, dezessis, dezessete, dezoito, dezenove, vinte, vinte1,
            vinte2, vinte3, vinte4, vinte5, vinte6, vinte7, vinte8,
            vinte9, trinta, trinta1, trinta2, trinta3, trinta4, trinta5,
            trinta6, trinta7, trinta8, trinta9, quarenta, quarenta1, quarenta2,
            quarenta3, quarenta4, quarenta5, quarenta6, quarenta7, quarenta8, quarenta9,
            cinquenta, cinquenta1, cinquenta2, cinquenta3, cinquenta4, cinquenta5, cinquenta6,
            cinquenta7, cinquenta8, cinquenta9, sessenta;


    List<Long> cartelaDeApostas = new ArrayList<>();
    List<ApostasUsuario> cartelaDeApostasFinal;
    private RequestQueue queue;
    private String email, token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogo);

        iniciarComponentes();
        CarregaOsComponentes();

        botaoApostar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Long[] mapper = {
                        cartelaDeApostas.get(0),
                        cartelaDeApostas.get(1),
                        cartelaDeApostas.get(2),
                        cartelaDeApostas.get(3),
                        cartelaDeApostas.get(4),
                        cartelaDeApostas.get(5),
                        cartelaDeApostas.get(6),
                        cartelaDeApostas.get(7),
                        cartelaDeApostas.get(8),
                        cartelaDeApostas.get(9),
                };
                cartelaDeApostasFinal.add(new ApostasUsuario(mapper));

                Intent refresh = new Intent(Jogo.this, Jogo.class);
                refresh.putExtra("email", getIntent().getExtras().getString("email"));
                refresh.putExtra("token", getIntent().getExtras().getString("token"));
                refresh.putExtra("cartelaDeApostasFinal", (Serializable) cartelaDeApostasFinal);
                finish();
                overridePendingTransition(0, 0);
                startActivity(refresh);
                overridePendingTransition(0, 0);
            }
        });

        finalizarApostas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cartelaDeApostas == null) {
                    Long[] mapper = {
                            cartelaDeApostas.get(0),
                            cartelaDeApostas.get(1),
                            cartelaDeApostas.get(2),
                            cartelaDeApostas.get(3),
                            cartelaDeApostas.get(4),
                            cartelaDeApostas.get(5),
                            cartelaDeApostas.get(6),
                            cartelaDeApostas.get(7),
                            cartelaDeApostas.get(8),
                            cartelaDeApostas.get(9),
                    };
                    cartelaDeApostasFinal.add(new ApostasUsuario(mapper));
                }

                Intent intent = new Intent(Jogo.this, ListaDeApostas.class);
                intent.putExtra("email", getIntent().getExtras().getString("email"));
                intent.putExtra("token", getIntent().getExtras().getString("token"));
                Bundle bundle = new Bundle();
                bundle.putSerializable("cartelaDeApostasFinal", (Serializable) cartelaDeApostasFinal);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }

    private void validaNumerosApostados(long l, Button button) {
        if (cartelaDeApostas.size() == 10L) {
            button.setClickable(false);
        } else if (cartelaDeApostas.contains(l)) {
                button.setBackground(getApplication().getDrawable(R.drawable.shape_botao_redondo_normal));
                button.setTextColor(getApplication().getColor(R.color.white));
                button.setTextSize(12);
                cartelaDeApostas.remove(l);
            } else {
                button.setBackground(getApplication().getDrawable(R.drawable.shape_botao_redondo_selecionado));
                button.setTextColor(getApplication().getColor(R.color.roxo));
                button.setTextSize(12);
                cartelaDeApostas.add(l);
                if (cartelaDeApostas.size() == 10L){
                    botaoApostar.setEnabled(true);
                    botaoApostar.setBackground(getApplication().getDrawable(R.drawable.botao_desativado_aposta));
                    botaoApostar.setTextColor(getApplication().getColor(R.color.roxo));
                    finalizarApostas.setEnabled(true);
                    finalizarApostas.setBackground(getApplication().getDrawable(R.drawable.botao_desativado_aposta));
                    finalizarApostas.setTextColor(getApplication().getColor(R.color.roxo));
                }
            }
        }

    @Override
    protected void onStart() {
        super.onStart();
        token = getIntent().getExtras().getString("token");
        email = getIntent().getExtras().getString("email");
        cartelaDeApostasFinal = (List<ApostasUsuario>) getIntent().getExtras().getSerializable("cartelaDeApostasFinal");

        if (cartelaDeApostasFinal == null) {
            cartelaDeApostasFinal = new ArrayList<>();
        } else {
            finalizarApostas.setEnabled(true);
            finalizarApostas.setBackground(getApplication().getDrawable(R.drawable.botao_desativado_aposta));
            finalizarApostas.setTextColor(getApplication().getColor(R.color.roxo));
        }

        long total = VALOR_APOSTA_UNICA * cartelaDeApostasFinal.size();
        valorTotalAposta.setText("R$ " + total + ",00");
    }


    private void CarregaOsComponentes(){
        um.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                validaNumerosApostados(1L, um);}});

        dois.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                validaNumerosApostados(2L, dois);}});

        tres.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                validaNumerosApostados(3L, tres);}});

        quatro.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                validaNumerosApostados(4L, quatro);}});

        cinco.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                validaNumerosApostados(5L, cinco);}});

        seis.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                validaNumerosApostados(6L, seis);}});

        sete.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                validaNumerosApostados(7L, sete);}});

        oito.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                validaNumerosApostados(8L, oito);}});

        nove.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                validaNumerosApostados(9L, nove);}});

        dez.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                validaNumerosApostados(10L, dez);}});

        onze.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                validaNumerosApostados(11L, onze);}});

        doze.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                validaNumerosApostados(12L, doze);}});

        treze.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                validaNumerosApostados(13L, treze);}});

        quatorze.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                validaNumerosApostados(14L, quatorze);}});

        quinze.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                validaNumerosApostados(15L, quinze);}});

        dezessis.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                validaNumerosApostados(16L, dezessis);}});

        dezessete.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                validaNumerosApostados(17L, dezessete);}});

        dezoito.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                validaNumerosApostados(18L, dezoito);}});

        dezenove.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                validaNumerosApostados(19L, dezenove);}});

        vinte.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                validaNumerosApostados(20L, vinte);}});

        vinte1.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                validaNumerosApostados(21L, vinte1);}});

        vinte2.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                validaNumerosApostados(22L, vinte2);}});

        vinte3.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                validaNumerosApostados(23L, vinte3);}});

        vinte4.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                validaNumerosApostados(24L, vinte4);}});

        vinte5.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                validaNumerosApostados(25L, vinte5);}});

        vinte6.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                validaNumerosApostados(26L, vinte6);}});

        vinte7.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                validaNumerosApostados(27L, vinte7);}});

        vinte8.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                validaNumerosApostados(28L, vinte8);}});

        vinte9.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                validaNumerosApostados(29L, vinte9);}});

        trinta.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                validaNumerosApostados(30L, trinta);}});

        trinta1.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                validaNumerosApostados(31L, trinta1);}});

        trinta2.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                validaNumerosApostados(32L, trinta2);}});

        trinta3.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                validaNumerosApostados(33L, trinta3);}});

        trinta4.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                validaNumerosApostados(34L, trinta4);}});

        trinta5.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                validaNumerosApostados(35L, trinta5);}});

        trinta6.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                validaNumerosApostados(36L, trinta6);}});

        trinta7.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                validaNumerosApostados(37L, trinta7);}});

        trinta8.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                validaNumerosApostados(38L, trinta8);}});

        trinta9.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                validaNumerosApostados(39L, trinta9);}});

        quarenta.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                validaNumerosApostados(40L, quarenta);}});

        quarenta1.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                validaNumerosApostados(41L, quarenta1);}});

        quarenta2.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                validaNumerosApostados(42L, quarenta2);}});

        quarenta3.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                validaNumerosApostados(43L, quarenta3);}});

        quarenta4.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                validaNumerosApostados(44L, quarenta4);}});

        quarenta5.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                validaNumerosApostados(45L, quarenta5);}});

        quarenta6.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                validaNumerosApostados(46L, quarenta6);}});

        quarenta7.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                validaNumerosApostados(47L, quarenta7);}});

        quarenta8.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                validaNumerosApostados(48L, quarenta8);}});

        quarenta9.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                validaNumerosApostados(49L, quarenta9);}});

        cinquenta.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                validaNumerosApostados(50L, cinquenta);}});

        cinquenta1.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                validaNumerosApostados(51L, cinquenta1);}});

        cinquenta2.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                validaNumerosApostados(52L, cinquenta2);}});

        cinquenta3.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                validaNumerosApostados(53L, cinquenta3);}});

        cinquenta4.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                validaNumerosApostados(54L, cinquenta4);}});

        cinquenta5.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                validaNumerosApostados(55L, cinquenta5);}});

        cinquenta6.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                validaNumerosApostados(56L, cinquenta6);}});

        cinquenta7.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                validaNumerosApostados(57L, cinquenta7);}});

        cinquenta8.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                validaNumerosApostados(58L, cinquenta8);}});

        cinquenta9.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                validaNumerosApostados(59L, cinquenta9);}});

        sessenta.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                validaNumerosApostados(60L, sessenta);}});
    }

    private void iniciarComponentes() {
        valorTotalAposta = findViewById(R.id.valor_total_aposta);
        botaoApostar = findViewById(R.id.botao_apostar);
        finalizarApostas = findViewById(R.id.botao_finalizar_apostas);
        queue = Volley.newRequestQueue(Jogo.this);
        um = findViewById(R.id.button1);
        dois = findViewById(R.id.button2);
        tres = findViewById(R.id.button3);
        quatro = findViewById(R.id.button4);
        cinco = findViewById(R.id.button5);
        seis = findViewById(R.id.button6);
        sete = findViewById(R.id.button7);
        oito = findViewById(R.id.button8);
        nove = findViewById(R.id.button9);
        dez = findViewById(R.id.button10);
        onze = findViewById(R.id.button11);
        doze = findViewById(R.id.button12);
        treze = findViewById(R.id.button13);
        quatorze = findViewById(R.id.button14);
        quinze = findViewById(R.id.button15);
        dezessis = findViewById(R.id.button16);
        dezessete = findViewById(R.id.button17);
        dezoito = findViewById(R.id.button18);
        dezenove = findViewById(R.id.button19);
        vinte = findViewById(R.id.button20);
        vinte1 = findViewById(R.id.button21);
        vinte2 = findViewById(R.id.button22);
        vinte3 = findViewById(R.id.button23);
        vinte4 = findViewById(R.id.button24);
        vinte5 = findViewById(R.id.button25);
        vinte6 = findViewById(R.id.button26);
        vinte7 = findViewById(R.id.button27);
        vinte8 = findViewById(R.id.button28);
        vinte9 = findViewById(R.id.button29);
        trinta = findViewById(R.id.button30);
        trinta1 = findViewById(R.id.button31);
        trinta2 = findViewById(R.id.button32);
        trinta3 = findViewById(R.id.button33);
        trinta4 = findViewById(R.id.button34);
        trinta5 = findViewById(R.id.button35);
        trinta6 = findViewById(R.id.button36);
        trinta7 = findViewById(R.id.button37);
        trinta8 = findViewById(R.id.button38);
        trinta9 = findViewById(R.id.button39);
        quarenta = findViewById(R.id.button40);
        quarenta1 = findViewById(R.id.button41);
        quarenta2 = findViewById(R.id.button42);
        quarenta3 = findViewById(R.id.button43);
        quarenta4 = findViewById(R.id.button44);
        quarenta5 = findViewById(R.id.button45);
        quarenta6 = findViewById(R.id.button46);
        quarenta7 = findViewById(R.id.button47);
        quarenta8 = findViewById(R.id.button48);
        quarenta9 = findViewById(R.id.button49);
        cinquenta = findViewById(R.id.button50);
        cinquenta1 = findViewById(R.id.button51);
        cinquenta2 = findViewById(R.id.button52);
        cinquenta3 = findViewById(R.id.button53);
        cinquenta4 = findViewById(R.id.button54);
        cinquenta5 = findViewById(R.id.button55);
        cinquenta6 = findViewById(R.id.button56);
        cinquenta7 = findViewById(R.id.button57);
        cinquenta8 = findViewById(R.id.button58);
        cinquenta9 = findViewById(R.id.button59);
        sessenta = findViewById(R.id.button60);
    }
}