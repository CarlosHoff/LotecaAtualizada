package com.hoffmann.lotecaatualizada;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionMode;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hoffmann.lotecaatualizada.adapters.BetAdapter;
import com.hoffmann.lotecaatualizada.domain.dto.BetUserDto;
import com.hoffmann.lotecaatualizada.viewmodel.BetsListViewModel;

import java.io.Serializable;
import java.util.List;

public class ListBets extends AppCompatActivity {
    private String email, token;
    private Button paymentButton;
    private BetAdapter adapter;
    private ActionMode actionMode;
    private BetsListViewModel viewModel;
    List<BetUserDto> betUserList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_de_apostas);

        startComponents();

        RecyclerView recyclerView = findViewById(R.id.recicleViewId);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new BetAdapter(this, betUserList);
        recyclerView.setAdapter(adapter);

        viewModel = new ViewModelProvider(this).get(BetsListViewModel.class);
        viewModel.getBettingCards().observe(this, betList -> {
            adapter.setUserBets(betList);
            adapter.notifyDataSetChanged();
        });

        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHandler(0, ItemTouchHelper.LEFT));
        helper.attachToRecyclerView(recyclerView);

        adapter.setListener(new BetAdapter.BetAdapterListener() {
            @Override
            public void onItemClick(int position) {
                enableActionMode(position);
            }

            @Override
            public void onItemLongClick(int position) {
                enableActionMode(position);
            }
        });

        paymentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListBets.this, Pagamento.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("cartelaDeApostasFinal", (Serializable) betUserList);
                bundle.putString("email", email);
                bundle.putString("token", token);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        token = getIntent().getExtras().getString("token");
        email = getIntent().getExtras().getString("email");
    }

    private void enableActionMode(int position) {
        if (actionMode == null){
            actionMode = startSupportActionMode(new ActionMode.Callback() {
                @Override
                public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                    mode.getMenuInflater().inflate(R.menu.menu_delete, menu);
                    return true;
                }

                @Override
                public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                    return false;
                }

                @Override
                public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                    if (item.getItemId() == R.id.action_delete){
                        adapter.deleteBets();
                        mode.finish();
                        return true;
                    }
                    return false;
                }

                public void onDestroyActionMode(ActionMode mode) {
                    adapter.getSelectedItems().clear();
                    List<BetUserDto> betUserDtos = adapter.getUserBets();
                    for (BetUserDto betUser : betUserDtos){
                        if (betUser.isSelected()){
                            betUser.setSelected(false);
                        }
                    }
                    adapter.notifyDataSetChanged();
                    actionMode = null;
                }
            });
        }
        adapter.toggleSelection(position);
        final int size = adapter.getSelectedItems().size();
        if (size == 0){
            actionMode.finish();

        } else {
            actionMode.setTitle(size + "");
            actionMode.invalidate();
        }
    }

    private  class ItemTouchHandler extends ItemTouchHelper.SimpleCallback {
        public ItemTouchHandler(int dragDirs, int swipeDirs) {
            super(dragDirs, swipeDirs);
        }

        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            int from = viewHolder.getPosition();
            int to = viewHolder.getAdapterPosition();
            viewModel.swapItems(from, to);
            adapter.notifyItemMoved(from, to);

            return true;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            adapter.getUserBets().remove(viewHolder.getAdapterPosition());
            adapter.notifyItemRemoved(viewHolder.getAdapterPosition());
        }
    }


    private void startComponents() {
        paymentButton = findViewById(R.id.botaoIrPagamento);
        Bundle bundle = getIntent().getExtras();
        betUserList = bundle.getParcelableArrayList("cartelaDeApostasFinal");

    }
}