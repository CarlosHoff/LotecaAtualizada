package com.hoffmann.lotecaatualizada;

import static com.hoffmann.lotecaatualizada.utilitario.Constantes.MEGATOLA_EXPLICACAO;
import static com.hoffmann.lotecaatualizada.utilitario.Constantes.OK;
import static com.hoffmann.lotecaatualizada.utilitario.Constantes.VALOR_APOSTA_MEGA_TOLA;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;

import com.hoffmann.lotecaatualizada.domain.dto.ApostasUsuarioDto;
import com.hoffmann.lotecaatualizada.utilitario.Utils;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class MegaTola extends AppCompatActivity {

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
    List<ApostasUsuarioDto> cartelaDeApostasFinal;
    private final Utils utils = new Utils();
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mega_tola);

        iniciarComponentes();

        sharedPreferences = getSharedPreferences("MEGATOLA", MODE_PRIVATE);
        if (!sharedPreferences.contains("hasVisitedActivity")) {
            Dialog dialog = utils.createAlertDialog(this, MEGATOLA_EXPLICACAO, "", OK);
            dialog.show();
            TextView botaoPositivo = dialog.findViewById(R.id.botao_positive);
            botaoPositivo.setOnClickListener(v -> dialog.dismiss());
        }
        sharedPreferences.edit().putBoolean("hasVisitedActivity", true).apply();

        CarregaOsComponentes();

        botaoApostar.setOnClickListener(v -> {
            adicionarApostasAoCarrinho();
            atualizarTela();
        });

        finalizarApostas.setOnClickListener(view -> {
            long[] mapper = createMapper(cartelaDeApostas);
            cartelaDeApostasFinal.add(new ApostasUsuarioDto(mapper));

            Intent intent = new Intent(MegaTola.this, ListaDeApostas.class);
            intent.putExtra("email", getIntent().getStringExtra("email"));
            intent.putExtra("token", getIntent().getStringExtra("token"));

            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList("cartelaDeApostasFinal", new ArrayList<>(cartelaDeApostasFinal));
            intent.putExtras(bundle);

            startActivity(intent);

            });
        }

    private void validaNumerosApostados(long numeroAposta, Button button) {
        if (cartelaDeApostas.size() == 10) {
            button.setClickable(false);
        } else if (cartelaDeApostas.contains(numeroAposta)) {
            eventoBotoes(button, R.drawable.shape_botao_redondo_normal, R.color.white, 12);
            cartelaDeApostas.remove(numeroAposta);
        } else {
            eventoBotoes(button, R.drawable.shape_botao_redondo_selecionado, R.color.roxo, 12);
            cartelaDeApostas.add(numeroAposta);
            if (cartelaDeApostas.size() == 10){
                eventoBotoes(botaoApostar, R.drawable.botao_desativado_aposta, R.color.roxo, null);
                eventoBotoes(finalizarApostas, R.drawable.botao_desativado_aposta, R.color.roxo, null);
                botaoApostar.setEnabled(true);
                finalizarApostas.setEnabled(true);
            }
        }
    }

    private void eventoBotoes(Button button, int backgroundDrawable, int textColor, Integer textSize) {
        button.setBackground(AppCompatResources.getDrawable(MegaTola.this, backgroundDrawable));
        button.setTextColor(getApplication().getColor(textColor));
        if (textSize != null) {
            button.setTextSize(textSize);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        cartelaDeApostasFinal = getIntent().getParcelableArrayListExtra("cartelaDeApostasFinal");

        if (cartelaDeApostasFinal == null) {
            cartelaDeApostasFinal = new ArrayList<>();
        } else {
            finalizarApostas.setEnabled(true);
            finalizarApostas.setBackground(AppCompatResources.getDrawable(MegaTola.this, R.drawable.botao_desativado_aposta));
            finalizarApostas.setTextColor(getApplication().getColor(R.color.roxo));
        }
        valorTotalAposta.setText(NumberFormat.getCurrencyInstance().format(VALOR_APOSTA_MEGA_TOLA * cartelaDeApostasFinal.size()));
    }

    private long[] createMapper(List<Long> cartelaDeApostas) {
        long[] mapper = new long[10];
        for (int i = 0; i < 10; i++) {
            mapper[i] = cartelaDeApostas.get(i);
        }
        return mapper;
    }


    private void CarregaOsComponentes(){
        um.setOnClickListener(view -> validaNumerosApostados(1L, um));

        dois.setOnClickListener(view -> validaNumerosApostados(2L, dois));

        tres.setOnClickListener(view -> validaNumerosApostados(3L, tres));

        quatro.setOnClickListener(view -> validaNumerosApostados(4L, quatro));

        cinco.setOnClickListener(view -> validaNumerosApostados(5L, cinco));

        seis.setOnClickListener(view -> validaNumerosApostados(6L, seis));

        sete.setOnClickListener(view -> validaNumerosApostados(7L, sete));

        oito.setOnClickListener(view -> validaNumerosApostados(8L, oito));

        nove.setOnClickListener(view -> validaNumerosApostados(9L, nove));

        dez.setOnClickListener(view -> validaNumerosApostados(10L, dez));

        onze.setOnClickListener(view -> validaNumerosApostados(11L, onze));

        doze.setOnClickListener(view -> validaNumerosApostados(12L, doze));

        treze.setOnClickListener(view -> validaNumerosApostados(13L, treze));

        quatorze.setOnClickListener(view -> validaNumerosApostados(14L, quatorze));

        quinze.setOnClickListener(view -> validaNumerosApostados(15L, quinze));

        dezessis.setOnClickListener(view -> validaNumerosApostados(16L, dezessis));

        dezessete.setOnClickListener(view -> validaNumerosApostados(17L, dezessete));

        dezoito.setOnClickListener(view -> validaNumerosApostados(18L, dezoito));

        dezenove.setOnClickListener(view -> validaNumerosApostados(19L, dezenove));

        vinte.setOnClickListener(view -> validaNumerosApostados(20L, vinte));

        vinte1.setOnClickListener(view -> validaNumerosApostados(21L, vinte1));

        vinte2.setOnClickListener(view -> validaNumerosApostados(22L, vinte2));

        vinte3.setOnClickListener(view -> validaNumerosApostados(23L, vinte3));

        vinte4.setOnClickListener(view -> validaNumerosApostados(24L, vinte4));

        vinte5.setOnClickListener(view -> validaNumerosApostados(25L, vinte5));

        vinte6.setOnClickListener(view -> validaNumerosApostados(26L, vinte6));

        vinte7.setOnClickListener(view -> validaNumerosApostados(27L, vinte7));

        vinte8.setOnClickListener(view -> validaNumerosApostados(28L, vinte8));

        vinte9.setOnClickListener(view -> validaNumerosApostados(29L, vinte9));

        trinta.setOnClickListener(view -> validaNumerosApostados(30L, trinta));

        trinta1.setOnClickListener(view -> validaNumerosApostados(31L, trinta1));

        trinta2.setOnClickListener(view -> validaNumerosApostados(32L, trinta2));

        trinta3.setOnClickListener(view -> validaNumerosApostados(33L, trinta3));

        trinta4.setOnClickListener(view -> validaNumerosApostados(34L, trinta4));

        trinta5.setOnClickListener(view -> validaNumerosApostados(35L, trinta5));

        trinta6.setOnClickListener(view -> validaNumerosApostados(36L, trinta6));

        trinta7.setOnClickListener(view -> validaNumerosApostados(37L, trinta7));

        trinta8.setOnClickListener(view -> validaNumerosApostados(38L, trinta8));

        trinta9.setOnClickListener(view -> validaNumerosApostados(39L, trinta9));

        quarenta.setOnClickListener(view -> validaNumerosApostados(40L, quarenta));

        quarenta1.setOnClickListener(view -> validaNumerosApostados(41L, quarenta1));

        quarenta2.setOnClickListener(view -> validaNumerosApostados(42L, quarenta2));

        quarenta3.setOnClickListener(view -> validaNumerosApostados(43L, quarenta3));

        quarenta4.setOnClickListener(view -> validaNumerosApostados(44L, quarenta4));

        quarenta5.setOnClickListener(view -> validaNumerosApostados(45L, quarenta5));

        quarenta6.setOnClickListener(view -> validaNumerosApostados(46L, quarenta6));

        quarenta7.setOnClickListener(view -> validaNumerosApostados(47L, quarenta7));

        quarenta8.setOnClickListener(view -> validaNumerosApostados(48L, quarenta8));

        quarenta9.setOnClickListener(view -> validaNumerosApostados(49L, quarenta9));

        cinquenta.setOnClickListener(view -> validaNumerosApostados(50L, cinquenta));

        cinquenta1.setOnClickListener(view -> validaNumerosApostados(51L, cinquenta1));

        cinquenta2.setOnClickListener(view -> validaNumerosApostados(52L, cinquenta2));

        cinquenta3.setOnClickListener(view -> validaNumerosApostados(53L, cinquenta3));

        cinquenta4.setOnClickListener(view -> validaNumerosApostados(54L, cinquenta4));

        cinquenta5.setOnClickListener(view -> validaNumerosApostados(55L, cinquenta5));

        cinquenta6.setOnClickListener(view -> validaNumerosApostados(56L, cinquenta6));

        cinquenta7.setOnClickListener(view -> validaNumerosApostados(57L, cinquenta7));

        cinquenta8.setOnClickListener(view -> validaNumerosApostados(58L, cinquenta8));

        cinquenta9.setOnClickListener(view -> validaNumerosApostados(59L, cinquenta9));

        sessenta.setOnClickListener(view -> validaNumerosApostados(60L, sessenta));
    }

    private void adicionarApostasAoCarrinho() {
        long[] mapper = {
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
        cartelaDeApostasFinal.add(new ApostasUsuarioDto(mapper));
    }

    private void atualizarTela() {
        Intent refresh = new Intent(MegaTola.this, MegaTola.class);
        refresh.putExtra("email", getIntent().getExtras().getString("email"));
        refresh.putExtra("token", getIntent().getExtras().getString("token"));

        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("cartelaDeApostasFinal", new ArrayList<>(cartelaDeApostasFinal));
        refresh.putExtras(bundle);

        finish();
        overridePendingTransition(0, 0);
        startActivity(refresh);
        overridePendingTransition(0, 0);
    }

    private void iniciarComponentes() {
        valorTotalAposta = findViewById(R.id.valor_total_aposta);
        botaoApostar = findViewById(R.id.botao_apostar);
        finalizarApostas = findViewById(R.id.botao_finalizar_apostas);
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