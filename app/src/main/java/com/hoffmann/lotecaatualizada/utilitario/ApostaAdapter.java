package com.hoffmann.lotecaatualizada.utilitario;

import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hoffmann.lotecaatualizada.R;
import com.hoffmann.lotecaatualizada.response.ApostasUsuario;

import java.util.ArrayList;
import java.util.List;

public class ApostaAdapter extends RecyclerView.Adapter<ApostaAdapter.ApostaViewHolder> {

    private Context context;
    private List<ApostasUsuario> apostasUsuarios;
    private ApostaAdapterListener listener;
    SparseBooleanArray selectedItems = new SparseBooleanArray();
    private int currentPosition;

    public ApostaAdapter(Context context, List<ApostasUsuario> apostasUsuarios) {
        this.context = context;
        this.apostasUsuarios = apostasUsuarios;
    }

    @NonNull
    @Override
    public ApostaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLista = LayoutInflater.from(context).inflate(R.layout.listagem_apostas, parent, false);
        return new ApostaViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull ApostaViewHolder holder, int position) {
        ApostasUsuario aposta = apostasUsuarios.get(position);
            holder.dezenaUm.setText(String.valueOf(aposta.getDezenas()[0]));
            holder.dezenaDois.setText(String.valueOf(aposta.getDezenas()[1]));
            holder.dezenaTres.setText(String.valueOf(aposta.getDezenas()[2]));
            holder.dezenaQuatro.setText(String.valueOf(aposta.getDezenas()[3]));
            holder.dezenaCinco.setText(String.valueOf(aposta.getDezenas()[4]));
            holder.dezenaSeis.setText(String.valueOf(aposta.getDezenas()[5]));
            holder.dezenaSete.setText(String.valueOf(aposta.getDezenas()[6]));
            holder.dezenaOito.setText(String.valueOf(aposta.getDezenas()[7]));
            holder.dezenaNove.setText(String.valueOf(aposta.getDezenas()[8]));
            holder.dezenaDez.setText(String.valueOf(aposta.getDezenas()[9]));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedItems.size() > 0 && listener != null)
                    listener.onItemClick(holder.getAdapterPosition());
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (listener != null)
                    listener.onItemLongClick(holder.getAdapterPosition());
                return true;
            }
        });

        if (currentPosition == position) {
            currentPosition = -1;
        }
    }

    @Override
    public int getItemCount() {
        return apostasUsuarios.size();
    }

    public void toggleSelection(int position) {
        currentPosition = position;
        if (selectedItems.get(position)){
            selectedItems.delete(position);
            apostasUsuarios.get(position).setSelected(true);
        } else {
            selectedItems.put(position, true);
            apostasUsuarios.get(position).setSelected(true);
        }
        notifyItemChanged(position);
    }

    public void deletaApostas() {
        List<ApostasUsuario> apostasDeletadas = new ArrayList<>();
        for (ApostasUsuario aposta : this.apostasUsuarios) {
            if (aposta.isSelected()){
                apostasDeletadas.add(aposta);
            }
        }
        this.apostasUsuarios.removeAll(apostasDeletadas);
        notifyDataSetChanged();
        currentPosition = -1;
    }

    public class ApostaViewHolder extends RecyclerView.ViewHolder {

        TextView dezenaUm, dezenaDois, dezenaTres, dezenaQuatro, dezenaCinco, dezenaSeis, dezenaSete, dezenaOito, dezenaNove, dezenaDez;

        public ApostaViewHolder(@NonNull View itemView) {
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

    public interface ApostaAdapterListener {
        void onItemClick(int position);
        void onItemLongClick(int position);
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public List<ApostasUsuario> getApostasUsuarios() {
        return apostasUsuarios;
    }

    public void setApostasUsuarios(List<ApostasUsuario> apostasUsuarios) {
        this.apostasUsuarios = apostasUsuarios;
    }

    public ApostaAdapterListener getListener() {
        return listener;
    }

    public void setListener(ApostaAdapterListener listener) {
        this.listener = listener;
    }

    public SparseBooleanArray getSelectedItems() {
        return selectedItems;
    }

    public void setSelectedItems(SparseBooleanArray selectedItems) {
        this.selectedItems = selectedItems;
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
    }
}
