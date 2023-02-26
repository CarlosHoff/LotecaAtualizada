package com.hoffmann.lotecaatualizada.utilitario;

import static com.hoffmann.lotecaatualizada.utilitario.Constantes.ATENCAO;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;

import com.hoffmann.lotecaatualizada.R;

public class Utils {

    public Dialog createAlertDialog(FragmentActivity activity, String mensagem, String negative, String positive) {
        Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setTitle(ATENCAO);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.alert_dialog);
        dialog.setCancelable(false);

        TextView alerta = dialog.findViewById(R.id.mensagem_alert_dialog);
        alerta.setText(mensagem);

        TextView negativeText = dialog.findViewById(R.id.botao_negative);
        negativeText.setText(negative);

        TextView positiveText = dialog.findViewById(R.id.botao_positive);
        positiveText.setText(positive);

        dialog.create();
        return dialog;

    }
}
