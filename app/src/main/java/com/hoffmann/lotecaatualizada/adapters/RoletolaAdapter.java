package com.hoffmann.lotecaatualizada.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hoffmann.lotecaatualizada.R;
import com.hoffmann.lotecaatualizada.domain.dto.RoletolaStatusDto;

import java.util.List;

public class RoletolaAdapter extends RecyclerView.Adapter<RoletolaAdapter.RoletolaViewHolder>{

    private Context context;

    private List<RoletolaStatusDto> roletolaStatusDtos;

    public RoletolaAdapter(Context context, List<RoletolaStatusDto> roletolaStatusDtos) {
        this.context = context;
        this.roletolaStatusDtos = roletolaStatusDtos;
    }

    @NonNull
    @Override
    public RoletolaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLista = LayoutInflater.from(context).inflate(R.layout.listagem_apostas_roletola, parent, false);
        return new RoletolaViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull RoletolaViewHolder holder, int position) {
        RoletolaStatusDto roletola = roletolaStatusDtos.get(position);
        holder.numeroSorteado.setText(roletola.getNumeroSorteado());
        holder.numeroApostado.setText(roletola.getNumeroApostado());
    }

    @Override
    public int getItemCount() {
        return roletolaStatusDtos.size();
    }


    public static class RoletolaViewHolder extends RecyclerView.ViewHolder {

        TextView numeroSorteado, numeroApostado;

        public RoletolaViewHolder(@NonNull View itemView) {
            super(itemView);

            numeroSorteado = itemView.findViewById(R.id.numeroSorteado);
            numeroApostado = itemView.findViewById(R.id.numeroApostado);

        }
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
