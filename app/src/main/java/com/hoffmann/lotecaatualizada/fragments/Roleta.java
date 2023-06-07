package com.hoffmann.lotecaatualizada.fragments;

import static android.content.Context.MODE_PRIVATE;
import static com.hoffmann.lotecaatualizada.utilitario.Constantes.OK;
import static com.hoffmann.lotecaatualizada.utilitario.Constantes.ROLETOLA_EXPLICACAO;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.content.res.AppCompatResources;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hoffmann.lotecaatualizada.R;
import com.hoffmann.lotecaatualizada.adapters.RoletolaAdapter;
import com.hoffmann.lotecaatualizada.domain.dto.RoletolaStatusDto;
import com.hoffmann.lotecaatualizada.utilitario.Utils;
import com.hoffmann.lotecaatualizada.viewmodel.RoletolaViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class Roleta extends Fragment {

    private Button spinButton, roletola_numero_01, roletola_numero_02, roletola_numero_03, roletola_numero_04, roletola_numero_05,
            roletola_numero_06, roletola_numero_07, roletola_numero_08, value10, value30, value50;
    final int[] sectors = {1, 4, 7, 2, 8, 5, 3, 6};
    final int[] sectorDegress = new int[sectors.length];
    int index = 0;
    private ImageView roulette, spinRoulette;
    Random random = new Random();
    List<RoletolaStatusDto> bets = new ArrayList<>();
    private RoletolaAdapter adapter;
    private RecyclerView recyclerView;
    private TextView balance;
    private final Utils utils = new Utils();
    SharedPreferences sharedPreferences;
    private RoletolaViewModel viewModel;

    public Roleta() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_roleta, container, false);

        initComponents(view);

        viewModel = new ViewModelProvider(this).get(RoletolaViewModel.class);

        sharedPreferences = requireActivity().getSharedPreferences("ROLETOLA", MODE_PRIVATE);
        if (!sharedPreferences.contains("hasVisitedActivity")) {
            Dialog dialog = utils.createAlertDialog(requireActivity(), ROLETOLA_EXPLICACAO, "", OK);
            dialog.show();
            TextView positiveButton = dialog.findViewById(R.id.botao_positive);
            positiveButton.setOnClickListener(v -> dialog.dismiss());
        }
        sharedPreferences.edit().putBoolean("hasVisitedActivity", true).apply();

        generateSectorDegress();

        roletola_numero_01.setOnClickListener(v -> {
            rulesValidation(roletola_numero_01);
        });

        roletola_numero_02.setOnClickListener(v -> {
            rulesValidation(roletola_numero_02);
        });

        roletola_numero_03.setOnClickListener(v -> {
            rulesValidation(roletola_numero_03);
        });

        roletola_numero_04.setOnClickListener(v -> {
            rulesValidation(roletola_numero_04);
        });

        roletola_numero_05.setOnClickListener(v -> {
            rulesValidation(roletola_numero_05);
        });

        roletola_numero_06.setOnClickListener(v -> {
            rulesValidation(roletola_numero_06);
        });

        roletola_numero_07.setOnClickListener(v -> {
            rulesValidation(roletola_numero_07);
        });

        roletola_numero_08.setOnClickListener(v -> {
            rulesValidation(roletola_numero_08);
        });

        spinButton.setOnClickListener(v -> {
            viewModel.setSpinning(false);
            if (Boolean.FALSE.equals(viewModel.getSpinning().getValue())) {
                spin();
                viewModel.setSpinning(true);
            }
        });

        spinRoulette.setOnClickListener(v -> spinButton.performClick());

        value10.setOnClickListener(v -> eventButtonsValues(value10));
        value30.setOnClickListener(v -> eventButtonsValues(value30));
        value50.setOnClickListener(v -> eventButtonsValues(value50));

        return view;
    }

    private void initComponents(View view) {
        roulette = view.findViewById(R.id.roleta);
        spinButton = view.findViewById(R.id.girar);
        spinRoulette = view.findViewById(R.id.rodar_roleta);
        recyclerView = view.findViewById(R.id.roletolaRecicleViewId);
        roletola_numero_01 = view.findViewById(R.id.roletola_01);
        roletola_numero_02 = view.findViewById(R.id.roletola_02);
        roletola_numero_03 = view.findViewById(R.id.roletola_03);
        roletola_numero_04 = view.findViewById(R.id.roletola_04);
        roletola_numero_05 = view.findViewById(R.id.roletola_05);
        roletola_numero_06 = view.findViewById(R.id.roletola_06);
        roletola_numero_07 = view.findViewById(R.id.roletola_07);
        roletola_numero_08 = view.findViewById(R.id.roletola_08);
        value10 = view.findViewById(R.id.botao_10_roletola);
        value30 = view.findViewById(R.id.botao_30_roletola);
        value50 = view.findViewById(R.id.botao_50_roletola);
        balance = view.findViewById(R.id.saldoRoletola);
    }

    private void spin() {
        index = random.nextInt(sectors.length);

        int randomValor = generateRandomNumber();

        RotateAnimation rotate = new RotateAnimation(0, randomValor, RotateAnimation.RELATIVE_TO_SELF,
                0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);

        rotate.setDuration(3600);
        rotate.setFillAfter(true);
        rotate.setInterpolator(new DecelerateInterpolator());
        rotate.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onAnimationEnd(Animation animation) {
                int value = sectors[sectors.length - (index + 1)];

                int actualBetNumber = getActualBetNumber();
                validateBet(value, actualBetNumber);

                RoletolaStatusDto dto = new RoletolaStatusDto();
                dto.setNumeroSorteado(String.valueOf(value));
                dto.setNumeroApostado(String.valueOf(viewModel.getActualNumberBet().getValue()));
                bets.add(dto);

                adapter = new RoletolaAdapter(getContext(), bets);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(),
                        RecyclerView.HORIZONTAL, false);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();

                resetButtons();

                viewModel.setSpinning(false);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });

        roulette.startAnimation(rotate);
    }

    private int getActualBetNumber() {
        return viewModel.getActualNumberBet().getValue() != null ? viewModel.getActualNumberBet().getValue() : 0;
    }

    private void validateBet(int valueRoulette, int numberBet) {
        try {
            double actualValue = Double.parseDouble(String.valueOf(viewModel.getSelectedValue().getValue()));
            double actualBalance = Double.parseDouble(balance.getText().toString());
            int betValue = 5;
            double winOrLost = (valueRoulette == numberBet) ? (betValue * actualValue) : -actualValue;
            actualBalance += winOrLost;
            balance.setText(String.format(Locale.getDefault(), "%.2f", actualBalance));
        } catch (NumberFormatException e) {
            Log.d("validaAPosta() ", e.getMessage());
        }
    }

    private void eventButtonsValues(Button botao) {
        List<Button> valueButtons = Arrays.asList(value10, value30, value50);
        Button[] buttons = {roletola_numero_01, roletola_numero_02, roletola_numero_03, roletola_numero_04, roletola_numero_05,
                roletola_numero_06, roletola_numero_07, roletola_numero_08};

        for (Button iterationButton : valueButtons) {
            if (botao.equals(iterationButton)) {
                iterationButton.setBackground((AppCompatResources.getDrawable(requireContext(), R.drawable.shape_botao_redondo_selecionado)));
                iterationButton.setTextColor(requireActivity().getApplication().getColor(R.color.roxo));
                iterationButton.setClickable(false);
                viewModel.setSelectedValue(iterationButton.getText().toString());

                for (Button betNumberButton : buttons) {
                    int betButton = Integer.parseInt(betNumberButton.getText().toString());
                    int actualBetNumber = getActualBetNumber();
                    if (betButton == actualBetNumber) {
                        spinButton.setEnabled(true);
                        spinButton.setBackground(AppCompatResources.getDrawable(requireContext(), R.drawable.botao_desativado_aposta));
                        spinButton.setTextColor(requireActivity().getApplication().getColor(R.color.roxo));
                        spinRoulette.setEnabled(true);
                    }
                }
            } else {
                iterationButton.setClickable(false);
            }
        }
    }

    private int generateRandomNumber() {
        return (360 * sectors.length) + sectorDegress[index];
    }

    private void generateSectorDegress() {
        int sectorDegree = 360 / sectors.length;

        for (int i = 0; i < sectors.length; i++) {
            sectorDegress[i] = (i + 1) * sectorDegree;
        }
    }

    private void rulesValidation(Button button) {

        Button[] buttons = {roletola_numero_01, roletola_numero_02, roletola_numero_03, roletola_numero_04, roletola_numero_05,
                roletola_numero_06, roletola_numero_07, roletola_numero_08};

        List<Button> valueButtons = Arrays.asList(value10, value30, value50);

        for (Button iterationButton : buttons) {
            if (button.getText().equals(iterationButton.getText())) {
                iterationButton.setBackground((AppCompatResources.getDrawable(requireContext(), R.drawable.shape_botao_redondo_selecionado)));
                iterationButton.setTextColor(requireActivity().getApplication().getColor(R.color.roxo));
                iterationButton.setClickable(false);

                viewModel.setActualBetNumber(Integer.parseInt((String) iterationButton.getText()));

                for (Button iterationValueButton : valueButtons) {
                    if (iterationValueButton.getText().equals(viewModel.getSelectedValue().getValue())) {
                        spinButton.setEnabled(true);
                        spinButton.setBackground(AppCompatResources.getDrawable(requireContext(), R.drawable.botao_desativado_aposta));
                        spinButton.setTextColor(requireActivity().getApplication().getColor(R.color.roxo));
                        spinRoulette.setEnabled(true);
                    }
                }
            } else {
                iterationButton.setClickable(false);
            }
        }
    }

    private void resetButtons() {
        List<Button> buttons = Arrays.asList(
                roletola_numero_01,
                roletola_numero_02,
                roletola_numero_03,
                roletola_numero_04,
                roletola_numero_05,
                roletola_numero_06,
                roletola_numero_07,
                roletola_numero_08);

        List<Button> valueButtons = Arrays.asList(value10, value30, value50);

        for (Button button : buttons) {
            if (!button.isClickable()) {
                button.setBackground((AppCompatResources.getDrawable(requireContext(), R.drawable.shape_botao_redondo_normal)));
                button.setTextColor(requireActivity().getApplication().getColor(R.color.white));
                button.setClickable(true);
                spinButton.setTextColor(requireActivity().getApplication().getColor(R.color.white));
                spinButton.setEnabled(false);
                spinRoulette.setEnabled(false);
            }

            for (Button valueButton : valueButtons) {
                if (valueButton.getText().equals(viewModel.getSelectedValue().getValue())) {
                    valueButton.setBackground((AppCompatResources.getDrawable(requireContext(), R.drawable.shape_botao_normal)));
                    valueButton.setTextColor(requireActivity().getApplication().getColor(R.color.white));
                    valueButton.setClickable(true);
                } else {
                    valueButton.setClickable(true);
                }
            }
            viewModel.setSelectedValue("");
            viewModel.setActualBetNumber(0);
        }
    }
}