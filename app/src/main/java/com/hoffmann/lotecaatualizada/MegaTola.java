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

import com.hoffmann.lotecaatualizada.domain.dto.BetUserDto;
import com.hoffmann.lotecaatualizada.utilitario.Utils;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class MegaTola extends AppCompatActivity {

    private TextView betsTotalValues;
    private Button finalizeBets, betButton, um, dois, tres, quatro, cinco, seis, sete,
            oito, nove, dez, onze, doze, treze, quatorze,
            quinze, dezessis, dezessete, dezoito, dezenove, vinte, vinte1,
            vinte2, vinte3, vinte4, vinte5, vinte6, vinte7, vinte8,
            vinte9, trinta, trinta1, trinta2, trinta3, trinta4, trinta5,
            trinta6, trinta7, trinta8, trinta9, quarenta, quarenta1, quarenta2,
            quarenta3, quarenta4, quarenta5, quarenta6, quarenta7, quarenta8, quarenta9,
            cinquenta, cinquenta1, cinquenta2, cinquenta3, cinquenta4, cinquenta5, cinquenta6,
            cinquenta7, cinquenta8, cinquenta9, sessenta;


    List<Long> cardsBets = new ArrayList<>();
    List<BetUserDto> cardsBetsFinal;
    private final Utils utils = new Utils();
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mega_tola);

        initComponents();

        sharedPreferences = getSharedPreferences("MEGATOLA", MODE_PRIVATE);
        if (!sharedPreferences.contains("hasVisitedActivity")) {
            Dialog dialog = utils.createAlertDialog(this, MEGATOLA_EXPLICACAO, "", OK);
            dialog.show();
            TextView positiveButton = dialog.findViewById(R.id.botao_positive);
            positiveButton.setOnClickListener(v -> dialog.dismiss());
        }
        sharedPreferences.edit().putBoolean("hasVisitedActivity", true).apply();

        startComponents();

        betButton.setOnClickListener(v -> {
            addBet();
            refreshScreen();
        });

        finalizeBets.setOnClickListener(view -> {
            long[] mapper = createMapper(cardsBets);
            cardsBetsFinal.add(new BetUserDto(mapper));

            Intent intent = new Intent(MegaTola.this, ListBets.class);
            intent.putExtra("email", getIntent().getStringExtra("email"));
            intent.putExtra("token", getIntent().getStringExtra("token"));

            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList("cartelaDeApostasFinal", new ArrayList<>(cardsBetsFinal));
            intent.putExtras(bundle);

            startActivity(intent);

            });
        }

    private void validateBetNumbers(long betNumber, Button button) {
        if (cardsBets.size() == 10) {
            button.setClickable(false);
        } else if (cardsBets.contains(betNumber)) {
            applyEventButtons(button, R.drawable.shape_botao_redondo_normal, R.color.white, 12);
            cardsBets.remove(betNumber);
        } else {
            applyEventButtons(button, R.drawable.shape_botao_redondo_selecionado, R.color.roxo, 12);
            cardsBets.add(betNumber);
            if (cardsBets.size() == 10){
                applyEventButtons(betButton, R.drawable.botao_desativado_aposta, R.color.roxo, null);
                applyEventButtons(finalizeBets, R.drawable.botao_desativado_aposta, R.color.roxo, null);
                betButton.setEnabled(true);
                finalizeBets.setEnabled(true);
            }
        }
    }

    private void applyEventButtons(Button button, int backgroundDrawable, int textColor, Integer textSize) {
        button.setBackground(AppCompatResources.getDrawable(MegaTola.this, backgroundDrawable));
        button.setTextColor(getApplication().getColor(textColor));
        if (textSize != null) {
            button.setTextSize(textSize);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        cardsBetsFinal = getIntent().getParcelableArrayListExtra("cartelaDeApostasFinal");

        if (cardsBetsFinal == null) {
            cardsBetsFinal = new ArrayList<>();
        } else {
            finalizeBets.setEnabled(true);
            finalizeBets.setBackground(AppCompatResources.getDrawable(MegaTola.this, R.drawable.botao_desativado_aposta));
            finalizeBets.setTextColor(getApplication().getColor(R.color.roxo));
        }
        betsTotalValues.setText(NumberFormat.getCurrencyInstance().format(VALOR_APOSTA_MEGA_TOLA * cardsBetsFinal.size()));
    }

    private long[] createMapper(List<Long> cardBets) {
        long[] mapper = new long[10];
        for (int i = 0; i < 10; i++) {
            mapper[i] = cardBets.get(i);
        }
        return mapper;
    }


    private void startComponents(){
        um.setOnClickListener(view -> validateBetNumbers(1L, um));

        dois.setOnClickListener(view -> validateBetNumbers(2L, dois));

        tres.setOnClickListener(view -> validateBetNumbers(3L, tres));

        quatro.setOnClickListener(view -> validateBetNumbers(4L, quatro));

        cinco.setOnClickListener(view -> validateBetNumbers(5L, cinco));

        seis.setOnClickListener(view -> validateBetNumbers(6L, seis));

        sete.setOnClickListener(view -> validateBetNumbers(7L, sete));

        oito.setOnClickListener(view -> validateBetNumbers(8L, oito));

        nove.setOnClickListener(view -> validateBetNumbers(9L, nove));

        dez.setOnClickListener(view -> validateBetNumbers(10L, dez));

        onze.setOnClickListener(view -> validateBetNumbers(11L, onze));

        doze.setOnClickListener(view -> validateBetNumbers(12L, doze));

        treze.setOnClickListener(view -> validateBetNumbers(13L, treze));

        quatorze.setOnClickListener(view -> validateBetNumbers(14L, quatorze));

        quinze.setOnClickListener(view -> validateBetNumbers(15L, quinze));

        dezessis.setOnClickListener(view -> validateBetNumbers(16L, dezessis));

        dezessete.setOnClickListener(view -> validateBetNumbers(17L, dezessete));

        dezoito.setOnClickListener(view -> validateBetNumbers(18L, dezoito));

        dezenove.setOnClickListener(view -> validateBetNumbers(19L, dezenove));

        vinte.setOnClickListener(view -> validateBetNumbers(20L, vinte));

        vinte1.setOnClickListener(view -> validateBetNumbers(21L, vinte1));

        vinte2.setOnClickListener(view -> validateBetNumbers(22L, vinte2));

        vinte3.setOnClickListener(view -> validateBetNumbers(23L, vinte3));

        vinte4.setOnClickListener(view -> validateBetNumbers(24L, vinte4));

        vinte5.setOnClickListener(view -> validateBetNumbers(25L, vinte5));

        vinte6.setOnClickListener(view -> validateBetNumbers(26L, vinte6));

        vinte7.setOnClickListener(view -> validateBetNumbers(27L, vinte7));

        vinte8.setOnClickListener(view -> validateBetNumbers(28L, vinte8));

        vinte9.setOnClickListener(view -> validateBetNumbers(29L, vinte9));

        trinta.setOnClickListener(view -> validateBetNumbers(30L, trinta));

        trinta1.setOnClickListener(view -> validateBetNumbers(31L, trinta1));

        trinta2.setOnClickListener(view -> validateBetNumbers(32L, trinta2));

        trinta3.setOnClickListener(view -> validateBetNumbers(33L, trinta3));

        trinta4.setOnClickListener(view -> validateBetNumbers(34L, trinta4));

        trinta5.setOnClickListener(view -> validateBetNumbers(35L, trinta5));

        trinta6.setOnClickListener(view -> validateBetNumbers(36L, trinta6));

        trinta7.setOnClickListener(view -> validateBetNumbers(37L, trinta7));

        trinta8.setOnClickListener(view -> validateBetNumbers(38L, trinta8));

        trinta9.setOnClickListener(view -> validateBetNumbers(39L, trinta9));

        quarenta.setOnClickListener(view -> validateBetNumbers(40L, quarenta));

        quarenta1.setOnClickListener(view -> validateBetNumbers(41L, quarenta1));

        quarenta2.setOnClickListener(view -> validateBetNumbers(42L, quarenta2));

        quarenta3.setOnClickListener(view -> validateBetNumbers(43L, quarenta3));

        quarenta4.setOnClickListener(view -> validateBetNumbers(44L, quarenta4));

        quarenta5.setOnClickListener(view -> validateBetNumbers(45L, quarenta5));

        quarenta6.setOnClickListener(view -> validateBetNumbers(46L, quarenta6));

        quarenta7.setOnClickListener(view -> validateBetNumbers(47L, quarenta7));

        quarenta8.setOnClickListener(view -> validateBetNumbers(48L, quarenta8));

        quarenta9.setOnClickListener(view -> validateBetNumbers(49L, quarenta9));

        cinquenta.setOnClickListener(view -> validateBetNumbers(50L, cinquenta));

        cinquenta1.setOnClickListener(view -> validateBetNumbers(51L, cinquenta1));

        cinquenta2.setOnClickListener(view -> validateBetNumbers(52L, cinquenta2));

        cinquenta3.setOnClickListener(view -> validateBetNumbers(53L, cinquenta3));

        cinquenta4.setOnClickListener(view -> validateBetNumbers(54L, cinquenta4));

        cinquenta5.setOnClickListener(view -> validateBetNumbers(55L, cinquenta5));

        cinquenta6.setOnClickListener(view -> validateBetNumbers(56L, cinquenta6));

        cinquenta7.setOnClickListener(view -> validateBetNumbers(57L, cinquenta7));

        cinquenta8.setOnClickListener(view -> validateBetNumbers(58L, cinquenta8));

        cinquenta9.setOnClickListener(view -> validateBetNumbers(59L, cinquenta9));

        sessenta.setOnClickListener(view -> validateBetNumbers(60L, sessenta));
    }

    private void addBet() {
        long[] mapper = {
                cardsBets.get(0),
                cardsBets.get(1),
                cardsBets.get(2),
                cardsBets.get(3),
                cardsBets.get(4),
                cardsBets.get(5),
                cardsBets.get(6),
                cardsBets.get(7),
                cardsBets.get(8),
                cardsBets.get(9),
        };
        cardsBetsFinal.add(new BetUserDto(mapper));
    }

    private void refreshScreen() {
        Intent refresh = new Intent(MegaTola.this, MegaTola.class);
        refresh.putExtra("email", getIntent().getExtras().getString("email"));
        refresh.putExtra("token", getIntent().getExtras().getString("token"));

        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("cartelaDeApostasFinal", new ArrayList<>(cardsBetsFinal));
        refresh.putExtras(bundle);

        finish();
        overridePendingTransition(0, 0);
        startActivity(refresh);
        overridePendingTransition(0, 0);
    }

    private void initComponents() {
        betsTotalValues = findViewById(R.id.valor_total_aposta);
        betButton = findViewById(R.id.botao_apostar);
        finalizeBets = findViewById(R.id.botao_finalizar_apostas);
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