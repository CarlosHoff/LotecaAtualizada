package com.hoffmann.lotecaatualizada;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionMode;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hoffmann.lotecaatualizada.adapters.ApostaAdapter;
import com.hoffmann.lotecaatualizada.domain.dto.ApostasUsuarioDto;
import com.hoffmann.lotecaatualizada.viewmodel.ListaDeApostasViewModel;

import java.util.List;

public class ListaDeApostas extends AppCompatActivity {

    private ApostaAdapter adapter;
    private RecyclerView recyclerView;
    private ActionMode actionMode;
    private ListaDeApostasViewModel viewModel;
    List<ApostasUsuarioDto> apostasList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_de_apostas);

        startComponents();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ListaDeApostas.this,
                RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new ApostaAdapter(getBaseContext(), apostasList);
        recyclerView.setAdapter(adapter);

        viewModel = new ViewModelProvider(this).get(ListaDeApostasViewModel.class);

        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHandler(0, ItemTouchHelper.LEFT));
        helper.attachToRecyclerView(recyclerView);

        adapter.setListener(new ApostaAdapter.ApostaAdapterListener() {
            @Override
            public void onItemClick(int position) {
                enableActionMode(position);
            }

            @Override
            public void onItemLongClick(int position) {
                enableActionMode(position);
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {
        return super.onCreateView(name, context, attrs);
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
                        adapter.deletaApostas();
                        mode.finish();
                        return true;
                    }
                    return false;
                }

                public void onDestroyActionMode(ActionMode mode) {
                    adapter.getSelectedItems().clear();
                    List<ApostasUsuarioDto> apostasUsuarioDtos = adapter.getApostasUsuarios();
                    for (ApostasUsuarioDto aposta : apostasUsuarioDtos){
                        if (aposta.isSelected()){
                            aposta.setSelected(false);
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
            adapter.getApostasUsuarios().remove(viewHolder.getAdapterPosition());
            adapter.notifyItemRemoved(viewHolder.getAdapterPosition());
        }
    }


    private void startComponents() {
        recyclerView = findViewById(R.id.recicleViewId);
        //botaoIrPagamento = findViewById(R.id.botaoIrPagamento);
        Bundle bundle = getIntent().getExtras();
        apostasList = bundle.getParcelableArrayList("cartelaDeApostasFinal");

    }
}