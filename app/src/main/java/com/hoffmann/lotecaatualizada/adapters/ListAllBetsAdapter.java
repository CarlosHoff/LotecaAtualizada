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
import java.util.Locale;

public class ListAllBetsAdapter extends RecyclerView.Adapter<ListAllBetsAdapter.ListBetsViewHolder>{

    private List<AllBetsResponse> allBetsResponseList;
    private Context context;

    public ListAllBetsAdapter(Context context, List<AllBetsResponse> allBetsResponseList) {
        this.allBetsResponseList = allBetsResponseList;
        this.context = context;
    }

    @NonNull
    @Override
    public ListBetsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLista = LayoutInflater.from(context).inflate(R.layout.todas_apostas_2, parent, false);
        return new ListBetsViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull ListBetsViewHolder holder, int position) {
        AllBetsResponse bets = allBetsResponseList.get(position);

        holder.nome.setText(formatName(bets));
        holder.dezenaUm.setText(addZerosIfNecessary(bets.getDezenaUm()));
        holder.dezenaDois.setText(addZerosIfNecessary(bets.getDezenaDois()));
        holder.dezenaTres.setText(addZerosIfNecessary(bets.getDezenaTres()));
        holder.dezenaQuatro.setText(addZerosIfNecessary(bets.getDezenaQuatro()));
        holder.dezenaCinco.setText(addZerosIfNecessary(bets.getDezenaCinco()));
        holder.dezenaSeis.setText(addZerosIfNecessary(bets.getDezenaSeis()));
        holder.dezenaSete.setText(addZerosIfNecessary(bets.getDezenaSete()));
        holder.dezenaOito.setText(addZerosIfNecessary(bets.getDezenaOito()));
        holder.dezenaNove.setText(addZerosIfNecessary(bets.getDezenaNove()));
        holder.dezenaDez.setText(addZerosIfNecessary(bets.getDezenaDez()));
        holder.qtdAcertos.setText(addZerosIfNecessary(bets.getQtdAcertos()));
    }

    private String formatName(AllBetsResponse bet) {
        String name = bet.getUser().getNome().toUpperCase(Locale.ROOT);
        String surname = bet.getUser().getApelido();
        return String.format("%s (%s)", name, surname);
    }

    private String addZerosIfNecessary(Long dezena) {
        String numberFormatted;
        if (dezena < 10) {
            numberFormatted = "0" + dezena;
        } else {
            numberFormatted =  String.valueOf(dezena);
        }
        return numberFormatted;
    }

    @Override
    public int getItemCount() {
        return allBetsResponseList.size();
    }


    public static class ListBetsViewHolder extends RecyclerView.ViewHolder {

        TextView nome, qtdAcertos, dezenaUm, dezenaDois, dezenaTres, dezenaQuatro, dezenaCinco,
                dezenaSeis, dezenaSete, dezenaOito, dezenaNove, dezenaDez;

        public ListBetsViewHolder(@NonNull View itemView) {
            super(itemView);

            nome = itemView.findViewById(R.id.nome);
            dezenaUm = itemView.findViewById(R.id.numero01);
            dezenaDois = itemView.findViewById(R.id.numero02);
            dezenaTres = itemView.findViewById(R.id.numero03);
            dezenaQuatro = itemView.findViewById(R.id.numero04);
            dezenaCinco = itemView.findViewById(R.id.numero05);
            dezenaSeis = itemView.findViewById(R.id.numero06);
            dezenaSete = itemView.findViewById(R.id.numero07);
            dezenaOito = itemView.findViewById(R.id.numero08);
            dezenaNove = itemView.findViewById(R.id.numero09);
            dezenaDez = itemView.findViewById(R.id.numero10);
            qtdAcertos = itemView.findViewById(R.id.numero_acertos);
        }
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public List<AllBetsResponse> getAllBetsResponseList() {
        return allBetsResponseList;
    }

    public void setAllBetsResponseList(List<AllBetsResponse> allBetsResponseList) {
        this.allBetsResponseList = allBetsResponseList;
    }

}
