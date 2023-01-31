package com.hoffmann.lotecaatualizada;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.hoffmann.lotecaatualizada.databinding.ActivityPerfilMenuBinding;

import java.util.Objects;

public class PerfilMenu extends AppCompatActivity {

    private ActivityPerfilMenuBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPerfilMenuBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        inicitNavigation();
    }


    private void inicitNavigation(){
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.navigation_fragment);
        NavController navController = Objects.requireNonNull(navHostFragment).getNavController();
        NavigationUI.setupWithNavController(binding.bottomNavigation, navController);
    }
}