package com.hoffmann.lotecaatualizada.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.hoffmann.lotecaatualizada.Login;
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
        new Handler().postDelayed(this::replaceActivity, 5000);
        return view;
    }

    private void replaceActivity() {
        Intent intent = new Intent(getActivity(), Login.class);
        startActivity(intent);
    }
}