package com.hoffmann.lotecaatualizada.adapters;

import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hoffmann.lotecaatualizada.R;
import com.hoffmann.lotecaatualizada.domain.dto.BetUserDto;

import java.util.ArrayList;
import java.util.List;

public class BetAdapter extends RecyclerView.Adapter<BetAdapter.BetViewHolder> {

    private Context context;
    private List<BetUserDto> betUserDtos;
    private BetAdapterListener listener;
    SparseBooleanArray selectedItems = new SparseBooleanArray();
    private int currentPosition;

    public BetAdapter(Context context, List<BetUserDto> betUserDtos) {
        this.context = context;
        this.betUserDtos = betUserDtos;
    }

    @NonNull
    @Override
    public BetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemList = LayoutInflater.from(context).inflate(R.layout.listagem_apostas, parent, false);
        return new BetViewHolder(itemList);
    }

    @Override
    public void onBindViewHolder(@NonNull BetViewHolder holder, int position) {
        BetUserDto bet = betUserDtos.get(position);
            holder.tenOne.setText(String.valueOf(bet.getDezenas()[0]));
            holder.tenTwo.setText(String.valueOf(bet.getDezenas()[1]));
            holder.tenThree.setText(String.valueOf(bet.getDezenas()[2]));
            holder.tenFour.setText(String.valueOf(bet.getDezenas()[3]));
            holder.tenFive.setText(String.valueOf(bet.getDezenas()[4]));
            holder.tenSix.setText(String.valueOf(bet.getDezenas()[5]));
            holder.tenSeven.setText(String.valueOf(bet.getDezenas()[6]));
            holder.tenEight.setText(String.valueOf(bet.getDezenas()[7]));
            holder.tenNine.setText(String.valueOf(bet.getDezenas()[8]));
            holder.tenTen.setText(String.valueOf(bet.getDezenas()[9]));

        holder.itemView.setOnClickListener(v -> {
            if (selectedItems.size() > 0 && listener != null)
                listener.onItemClick(holder.getAdapterPosition());
        });

        holder.itemView.setOnLongClickListener(v -> {
            if (listener != null)
                listener.onItemLongClick(holder.getAdapterPosition());
            return true;
        });

        if (currentPosition == position) {
            currentPosition = -1;
        }
    }

    @Override
    public int getItemCount() {
        return betUserDtos.size();
    }

    public void toggleSelection(int position) {
        currentPosition = position;
        if (selectedItems.get(position)){
            selectedItems.delete(position);
            betUserDtos.get(position).setSelected(true);
        } else {
            selectedItems.put(position, true);
            betUserDtos.get(position).setSelected(true);
        }
        notifyItemChanged(position);
    }

    public void deleteBets() {
        List<BetUserDto> deletedBet = new ArrayList<>();
        for (BetUserDto bet : this.betUserDtos) {
            if (bet.isSelected()){
                deletedBet.add(bet);
            }
        }
        this.betUserDtos.removeAll(deletedBet);
        notifyDataSetChanged();
        currentPosition = -1;
    }

    public static class BetViewHolder extends RecyclerView.ViewHolder {

        TextView tenOne, tenTwo, tenThree, tenFour, tenFive, tenSix, tenSeven, tenEight, tenNine, tenTen;

        public BetViewHolder(@NonNull View itemView) {
            super(itemView);

            tenOne = itemView.findViewById(R.id.dezena1);
            tenTwo = itemView.findViewById(R.id.dezena2);
            tenThree = itemView.findViewById(R.id.dezena3);
            tenFour = itemView.findViewById(R.id.dezena4);
            tenFive = itemView.findViewById(R.id.dezena5);
            tenSix = itemView.findViewById(R.id.dezena6);
            tenSeven = itemView.findViewById(R.id.dezena7);
            tenEight = itemView.findViewById(R.id.dezena8);
            tenNine = itemView.findViewById(R.id.dezena9);
            tenTen = itemView.findViewById(R.id.dezena10);
        }
    }

    public interface BetAdapterListener {
        void onItemClick(int position);
        void onItemLongClick(int position);
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public List<BetUserDto> getUserBets() {
        return betUserDtos;
    }

    public void setUserBets(List<BetUserDto> betUserDtos) {
        this.betUserDtos = betUserDtos;
    }

    public BetAdapterListener getListener() {
        return listener;
    }

    public void setListener(BetAdapterListener listener) {
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
