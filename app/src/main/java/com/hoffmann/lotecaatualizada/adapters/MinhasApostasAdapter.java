package com.hoffmann.lotecaatualizada.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hoffmann.lotecaatualizada.R;
import com.hoffmann.lotecaatualizada.domain.response.TodasApostasResponse;

import java.util.List;

public class MinhasApostasAdapter extends RecyclerView.Adapter<MinhasApostasAdapter.MinhasApostaViewHolder>{

    private Context context;
    private List<TodasApostasResponse> minhasApostas;

    public MinhasApostasAdapter(Context context, List<TodasApostasResponse> minhasApostas) {
        this.context = context;
        this.minhasApostas = minhasApostas;
    }

    @NonNull
    @Override
    public MinhasApostasAdapter.MinhasApostaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLista = LayoutInflater.from(context).inflate(R.layout.listagem_apostas, parent, false);
        return new MinhasApostasAdapter.MinhasApostaViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MinhasApostasAdapter.MinhasApostaViewHolder holder, int position) {
        TodasApostasResponse aposta = minhasApostas.get(position);

        holder.dezenaUm.setText(addZerosCasoPrecise(aposta.getDezenaUm()));
        holder.dezenaDois.setText(addZerosCasoPrecise(aposta.getDezenaDois()));
        holder.dezenaTres.setText(addZerosCasoPrecise(aposta.getDezenaTres()));
        holder.dezenaQuatro.setText(addZerosCasoPrecise(aposta.getDezenaQuatro()));
        holder.dezenaCinco.setText(addZerosCasoPrecise(aposta.getDezenaCinco()));
        holder.dezenaSeis.setText(addZerosCasoPrecise(aposta.getDezenaSeis()));
        holder.dezenaSete.setText(addZerosCasoPrecise(aposta.getDezenaSete()));
        holder.dezenaOito.setText(addZerosCasoPrecise(aposta.getDezenaOito()));
        holder.dezenaNove.setText(addZerosCasoPrecise(aposta.getDezenaNove()));
        holder.dezenaDez.setText(addZerosCasoPrecise(aposta.getDezenaDez()));
    }

    private String addZerosCasoPrecise(Long dezena) {
        String numeroFormatado;
        if (dezena < 10) {
            numeroFormatado = "0" + dezena;
        } else {
            numeroFormatado =  String.valueOf(dezena);
        }
        return numeroFormatado;
    }

    @Override
    public int getItemCount() {
        return minhasApostas.size();
    }

    public static class MinhasApostaViewHolder extends RecyclerView.ViewHolder {

        TextView dezenaUm, dezenaDois, dezenaTres, dezenaQuatro, dezenaCinco,
                dezenaSeis, dezenaSete, dezenaOito, dezenaNove, dezenaDez;

        public MinhasApostaViewHolder(@NonNull View itemView) {
            super(itemView);

            dezenaUm = itemView.findViewById(R.id.dezena1);
            dezenaDois = itemView.findViewById(R.id.dezena2);
            dezenaTres = itemView.findViewById(R.id.dezena3);
            dezenaQuatro = itemView.findViewById(R.id.dezena4);
            dezenaCinco = itemView.findViewById(R.id.dezena5);
            dezenaSeis = itemView.findViewById(R.id.dezena6);
            dezenaSete = itemView.findViewById(R.id.dezena7);
            dezenaOito = itemView.findViewById(R.id.dezena8);
            dezenaNove = itemView.findViewById(R.id.dezena9);
            dezenaDez = itemView.findViewById(R.id.dezena10);
        }
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
