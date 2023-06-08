package com.hoffmann.lotecaatualizada.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionMode;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.hoffmann.lotecaatualizada.R;
import com.hoffmann.lotecaatualizada.adapters.BetAdapter;
import com.hoffmann.lotecaatualizada.domain.dto.BetUserDto;
import com.hoffmann.lotecaatualizada.viewmodel.BetsListViewModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ListBets extends Fragment {

    private String email, token, nome, celular;
    private Button paymentButton;
    private BetAdapter adapter;
    private ActionMode actionMode;
    private BetsListViewModel viewModel;
    List<BetUserDto> betUserList;
    RecyclerView recyclerView;

    public ListBets() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            betUserList = getArguments().getParcelableArrayList("cartelaDeApostasFinal");
            token = getArguments().getString("token");
            email = getArguments().getString("email");
            nome = getArguments().getString("nome");
            celular = getArguments().getString("celular");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_bets, container, false);

        startComponents(view);

        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        adapter = new BetAdapter(requireContext(), betUserList);
        recyclerView.setAdapter(adapter);

        viewModel = new ViewModelProvider(this).get(BetsListViewModel.class);
        viewModel.getBettingCards().observe(getViewLifecycleOwner(), betList -> {
            adapter.setUserBets(betList);
            adapter.notifyDataSetChanged();
        });

        ItemTouchHelper helper = new ItemTouchHelper(new ListBets.ItemTouchHandler(0, ItemTouchHelper.LEFT));
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

        paymentButton.setOnClickListener(v -> {
            Fragment payment = new Pagamento();
            FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            Bundle args = new Bundle();
            args.putString("email", email);
            args.putString("token", token);
            args.putString("nome", nome);
            args.putString("celular", celular);
            args.putParcelableArrayList("cartelaDeApostasFinal", new ArrayList<>(betUserList));
            payment.setArguments(args);
            fragmentTransaction.replace(R.id.fragmennt_list_bets, payment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        });

        return view;
    }

    private void enableActionMode(int position) {
        AppCompatActivity activity = (AppCompatActivity) requireActivity();
        if (actionMode == null) {
            actionMode = activity.startSupportActionMode(new ActionMode.Callback() {
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
                    if (item.getItemId() == R.id.action_delete) {
                        adapter.deleteBets();
                        mode.finish();
                        return true;
                    }
                    return false;
                }

                @Override
                public void onDestroyActionMode(ActionMode mode) {
                    adapter.getSelectedItems().clear();
                    List<BetUserDto> betUserDtos = adapter.getUserBets();
                    for (BetUserDto betUser : betUserDtos) {
                        if (betUser.isSelected()) {
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
        if (size == 0) {
            actionMode.finish();
        } else {
            actionMode.setTitle(String.valueOf(size));
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


    private void startComponents(View view) {
        paymentButton = view.findViewById(R.id.botaoIrPagamento);
        recyclerView = view.findViewById(R.id.recicleViewId);

    }
}