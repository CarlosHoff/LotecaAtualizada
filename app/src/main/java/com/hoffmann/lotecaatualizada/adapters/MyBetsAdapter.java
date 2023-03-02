package com.hoffmann.lotecaatualizada.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hoffmann.lotecaatualizada.R;
import com.hoffmann.lotecaatualizada.domain.response.AllBetsResponse;

import java.util.List;

public class MyBetsAdapter extends RecyclerView.Adapter<MyBetsAdapter.MyBetsViewHolder>{

    private Context context;
    private List<AllBetsResponse> myBets;

    public MyBetsAdapter(Context context, List<AllBetsResponse> myBets) {
        this.context = context;
        this.myBets = myBets;
    }

    @NonNull
    @Override
    public MyBetsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLista = LayoutInflater.from(context).inflate(R.layout.listagem_apostas, parent, false);
        return new MyBetsViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyBetsViewHolder holder, int position) {
        AllBetsResponse bet = myBets.get(position);

        holder.dezenaUm.setText(addZerosIfNecessary(bet.getDezenaUm()));
        holder.dezenaDois.setText(addZerosIfNecessary(bet.getDezenaDois()));
        holder.dezenaTres.setText(addZerosIfNecessary(bet.getDezenaTres()));
        holder.dezenaQuatro.setText(addZerosIfNecessary(bet.getDezenaQuatro()));
        holder.dezenaCinco.setText(addZerosIfNecessary(bet.getDezenaCinco()));
        holder.dezenaSeis.setText(addZerosIfNecessary(bet.getDezenaSeis()));
        holder.dezenaSete.setText(addZerosIfNecessary(bet.getDezenaSete()));
        holder.dezenaOito.setText(addZerosIfNecessary(bet.getDezenaOito()));
        holder.dezenaNove.setText(addZerosIfNecessary(bet.getDezenaNove()));
        holder.dezenaDez.setText(addZerosIfNecessary(bet.getDezenaDez()));
    }

    private String addZerosIfNecessary(Long dezena) {
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
        return myBets.size();
    }

    public static class MyBetsViewHolder extends RecyclerView.ViewHolder {

        TextView dezenaUm, dezenaDois, dezenaTres, dezenaQuatro, dezenaCinco,
                dezenaSeis, dezenaSete, dezenaOito, dezenaNove, dezenaDez;

        public MyBetsViewHolder(@NonNull View itemView) {
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

    public void setMyBets(List<AllBetsResponse> myBets) {
        this.myBets = myBets;
    }
}
