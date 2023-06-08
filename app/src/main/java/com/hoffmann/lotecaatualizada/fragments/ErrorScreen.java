package com.hoffmann.lotecaatualizada.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.hoffmann.lotecaatualizada.R;

public class ErrorScreen extends Fragment {

    public ErrorScreen() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_error_screen, container, false);
        new Handler().postDelayed(() -> replaceFragment(new ErrorScreen()), 5000);
        return view;
    }

    private void replaceFragment(ErrorScreen errorScreen) {
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_error_screen, errorScreen);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}