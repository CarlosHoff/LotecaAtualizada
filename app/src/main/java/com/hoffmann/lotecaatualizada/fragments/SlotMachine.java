package com.hoffmann.lotecaatualizada.fragments;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.appcompat.content.res.AppCompatResources;
import androidx.fragment.app.Fragment;

import com.hoffmann.lotecaatualizada.R;

import java.util.Random;

public class SlotMachine extends Fragment {

    private Button slot1, slot2, slot3, botaoApostar;
    Random random = new Random();

    public SlotMachine() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_slot_machine, container, false);

        iniciaComponentes(view);

        botaoApostar.setOnClickListener(v -> {
            int valor1 = random.nextInt(7);
            int valor2 = random.nextInt(7);
            int valor3 = random.nextInt(7);

            animateSlot(slot1, valor1);
            animateSlot(slot2, valor2);
            animateSlot(slot3, valor3);
        });

        return view;
    }

    private void animateSlot(View slot, int value) {
        slot.setBackground(getImage(value));
        AnimatorSet animacao = (AnimatorSet) AnimatorInflater.loadAnimator(requireContext(), R.animator.fade_in);
        animacao.setTarget(slot);
        animacao.start();
    }

    private Drawable getImage(int valorImagem) {

        Drawable fundo;

        switch (valorImagem) {
            case 1:
                fundo = AppCompatResources.getDrawable(requireContext(), R.drawable.n1);
                break;
            case 2:
                fundo = AppCompatResources.getDrawable(requireContext(), R.drawable.n2);
                break;
            case 3:
                fundo = AppCompatResources.getDrawable(requireContext(), R.drawable.n3);
                break;
            case 4:
                fundo = AppCompatResources.getDrawable(requireContext(), R.drawable.n4);
                break;
            case 5:
                fundo = AppCompatResources.getDrawable(requireContext(), R.drawable.n5);
                break;
            case 6:
                fundo = AppCompatResources.getDrawable(requireContext(), R.drawable.n6);
                break;
            case 7:
                fundo = AppCompatResources.getDrawable(requireContext(), R.drawable.n7);
                break;
            default:
                fundo = AppCompatResources.getDrawable(requireContext(), R.drawable.n0);
                break;
        }
        return fundo;
    }

    private void iniciaComponentes(View view) {
        slot1 = view.findViewById(R.id.slot1);
        slot2 = view.findViewById(R.id.slot2);
        slot3 = view.findViewById(R.id.slot3);
        botaoApostar = view.findViewById(R.id.botao_apostar);
    }
}