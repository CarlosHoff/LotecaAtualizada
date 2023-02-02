package com.hoffmann.lotecaatualizada;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;


import com.hoffmann.lotecaatualizada.databinding.ActivityMenuBinding;
import com.hoffmann.lotecaatualizada.utilitario.UserArgs;

import java.util.Objects;

public class Menu extends AppCompatActivity {

    private ActivityMenuBinding binding;
    private String email, token;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMenuBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        token = getIntent().getExtras().getString("token");
        email = getIntent().getExtras().getString("email");

        initNavigation();
    }


//    private void initNavigation(){
//
//        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.navigation_fragment);
//        NavController navController = Objects.requireNonNull(navHostFragment).getNavController();
//        NavigationUI.setupWithNavController(binding.bottomNavigation, navController);
//    }

    private void initNavigation(){
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.navigation_fragment);
        NavController navController = Objects.requireNonNull(navHostFragment).getNavController();

        Bundle args = new Bundle();
        args.putString("email", email);
        args.putString("token", token);
        navController.setGraph(navController.getGraph(), args);

        navController.navigate(R.id.nav_main, args);

        NavigationUI.setupWithNavController(binding.bottomNavigation, navController);
    }

}