package com.hoffmann.lotecaatualizada.fragments;

import static com.hoffmann.lotecaatualizada.utilitario.Constantes.SCORE;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.content.res.AppCompatResources;
import androidx.fragment.app.Fragment;

import com.hoffmann.lotecaatualizada.R;
import com.hoffmann.lotecaatualizada.imageViewScrolling.IEventEnd;
import com.hoffmann.lotecaatualizada.imageViewScrolling.ImageViewScrolling;
import com.hoffmann.lotecaatualizada.utilitario.Constantes;

import java.util.Random;

public class SlotMachine extends Fragment implements IEventEnd{

    ImageView btn_up, btn_down;
    ImageViewScrolling image1, image2, image3;
    TextView txt_score;

    int count_done = 0;

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


        image1.setEventEnd(SlotMachine.this);
        image2.setEventEnd(SlotMachine.this);
        image3.setEventEnd(SlotMachine.this);

        btn_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SCORE >= 50) {
                    btn_up.setVisibility(View.GONE);
                    btn_down.setVisibility(View.VISIBLE);

                    image1.setValueRandom(new Random().nextInt(6), new Random().nextInt((15-5)+1)+5);
                    image2.setValueRandom(new Random().nextInt(6), new Random().nextInt((15-5)+1)+5);
                    image3.setValueRandom(new Random().nextInt(6), new Random().nextInt((15-5)+1)+5);

                    SCORE -= 50;

                    txt_score.setText(String.valueOf(SCORE));
                } else {
                    Toast.makeText(getContext(), "Você não tem dinheiro o suficiente.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }


    private void iniciaComponentes(View view) {
        btn_up = view.findViewById(R.id.btn_up);
        btn_down = view.findViewById(R.id.btn_down);
        btn_up = view.findViewById(R.id.btn_up);
        image1 = view.findViewById(R.id.image1);
        image2 = view.findViewById(R.id.image2);
        image3 = view.findViewById(R.id.image3);
        txt_score = view.findViewById(R.id.txt_score);
    }

    @Override
    public void eventEnd(int result, int count) {
        if(count_done < 2){
            count_done++;
        } else {
            btn_down.setVisibility(View.GONE);
            btn_up.setVisibility(View.VISIBLE);

            count_done = 0;

            if(image1.getValue() == image2.getValue() && image2.getValue() == image3.getValue()) {
                Toast.makeText(getContext(), "Vocē ganhou Bastante!!", Toast.LENGTH_SHORT).show();
                SCORE += 300;
                txt_score.setText(String.valueOf(SCORE));
            } else if (image1.getValue() == image2.getValue() || image2.getValue() == image3.getValue() || image1.getValue() == image3.getValue()) {
                Toast.makeText(getContext(), "Vocē ganhou Pouco!!", Toast.LENGTH_SHORT).show();
                SCORE += 100;
                txt_score.setText(String.valueOf(SCORE));
            } else {
                Toast.makeText(getContext(), "Vocē Perdeu!!", Toast.LENGTH_SHORT).show();
                SCORE -= 50;
                txt_score.setText(String.valueOf(SCORE));
            }
        }
    }
}