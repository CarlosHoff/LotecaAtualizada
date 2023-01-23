package com.hoffmann.lotecaatualizada;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionMode;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.hoffmann.lotecaatualizada.response.ApostasUsuario;
import com.hoffmann.lotecaatualizada.utilitario.ApostaAdapter;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

public class ListaDeApostas extends AppCompatActivity {

    private Button botaoIrPagamento;
    private List<ApostasUsuario> cartelaDeApostasFinal;
    private ApostaAdapter adapter;
    private RequestQueue queue;
    private String email, token;
    private RecyclerView recyclerView;
    private ActionMode actionMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_de_apostas);

        inciaComponente();

        adapter = new ApostaAdapter(ListaDeApostas.this, cartelaDeApostasFinal);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ListaDeApostas.this,
                RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        botaoIrPagamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListaDeApostas.this, Pagamento.class);
                intent.putExtra("email", getIntent().getExtras().getString("email"));
                intent.putExtra("token", getIntent().getExtras().getString("token"));
                Bundle bundle = new Bundle();
                bundle.putSerializable("cartelaDeApostasFinal", (Serializable) cartelaDeApostasFinal);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHandler(0, ItemTouchHelper.LEFT)){
        };

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
                @Override
                public void onDestroyActionMode(ActionMode mode) {
                    adapter.getSelectedItems().clear();
                    List<ApostasUsuario> apostasUsuarios = adapter.getApostasUsuarios();
                    for (ApostasUsuario aposta : apostasUsuarios){
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

    private void inciaComponente() {
        queue = Volley.newRequestQueue(this);
        recyclerView = findViewById(R.id.recicleViewId);
        botaoIrPagamento = findViewById(R.id.botaoIrPagamento);
        Bundle bundle = getIntent().getExtras();
        cartelaDeApostasFinal = (List<ApostasUsuario>) bundle.getSerializable("cartelaDeApostasFinal");
    }

    @Override
    protected void onStart() {
        super.onStart();
        token = getIntent().getExtras().getString("token");
        email = getIntent().getExtras().getString("email");
    }

    private  class ItemTouchHandler extends ItemTouchHelper.SimpleCallback {
        public ItemTouchHandler(int dragDirs, int swipeDirs) {
            super(dragDirs, swipeDirs);
        }

        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            int from = viewHolder.getPosition();
            int to = viewHolder.getAdapterPosition();
            Collections.swap(adapter.getApostasUsuarios(), from, to);
            adapter.notifyItemMoved(from, to);

            return true;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            adapter.getApostasUsuarios().remove(viewHolder.getAdapterPosition());
            adapter.notifyItemRemoved(viewHolder.getAdapterPosition());
        }
    }
}