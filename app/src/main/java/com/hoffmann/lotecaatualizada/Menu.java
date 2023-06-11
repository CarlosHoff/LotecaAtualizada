package com.hoffmann.lotecaatualizada;

import android.content.pm.ActivityInfo;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.hoffmann.lotecaatualizada.databinding.ActivityMenuBinding;
import com.hoffmann.lotecaatualizada.utilitario.SharedViewModel;

import java.util.Objects;

public class Menu extends AppCompatActivity {

    private ActivityMenuBinding binding;
    private String email, token;
    private SharedViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMenuBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        token = getIntent().getExtras().getString("token");
        email = getIntent().getExtras().getString("email");

        model = new ViewModelProvider(this).get(SharedViewModel.class);

        initNavigation();
    }

    private void initNavigation(){
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.navigation_fragment);
        NavController navController = Objects.requireNonNull(navHostFragment).getNavController();

        model.setEmail(email);
        model.setToken(token);

        navController.navigate(R.id.nav_main);
        NavigationUI.setupWithNavController(binding.bottomNavigation, navController);
    }

}