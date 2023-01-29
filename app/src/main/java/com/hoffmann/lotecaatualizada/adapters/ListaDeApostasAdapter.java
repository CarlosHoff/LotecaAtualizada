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
import java.util.Locale;

public class ListaDeApostasAdapter extends RecyclerView.Adapter<ListaDeApostasAdapter.ListaDeApostaViewHolder>{

    private List<TodasApostasResponse> listaTodasApostas;
    private Context context;

    public ListaDeApostasAdapter(Context context, List<TodasApostasResponse> listaTodasApostas) {
        this.listaTodasApostas = listaTodasApostas;
        this.context = context;
    }

    @NonNull
    @Override
    public ListaDeApostaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLista = LayoutInflater.from(context).inflate(R.layout.todas_apostas_2, parent, false);
        return new ListaDeApostaViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull ListaDeApostaViewHolder holder, int position) {
        TodasApostasResponse aposta = listaTodasApostas.get(position);

        holder.nome.setText(formataNome(aposta));
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
        holder.qtdAcertos.setText(addZerosCasoPrecise(aposta.getQtdAcertos()));
    }

    private String formataNome(TodasApostasResponse aposta) {
        String nome = aposta.getUsuario().getNome().toUpperCase(Locale.ROOT);
        String apelido = aposta.getUsuario().getApelido();
        return String.format("%s (%s)", nome, apelido);
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
        return listaTodasApostas.size();
    }


    public static class ListaDeApostaViewHolder extends RecyclerView.ViewHolder {

        TextView nome, qtdAcertos, dezenaUm, dezenaDois, dezenaTres, dezenaQuatro, dezenaCinco,
                dezenaSeis, dezenaSete, dezenaOito, dezenaNove, dezenaDez;

        public ListaDeApostaViewHolder(@NonNull View itemView) {
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

    public List<TodasApostasResponse> getListaTodasApostas() {
        return listaTodasApostas;
    }

    public void setListaTodasApostas(List<TodasApostasResponse> listaTodasApostas) {
        this.listaTodasApostas = listaTodasApostas;
    }

}
