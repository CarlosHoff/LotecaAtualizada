package com.hoffmann.lotecaatualizada.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.hoffmann.lotecaatualizada.domain.response.AllBetsResponse;
import com.hoffmann.lotecaatualizada.repository.MyBetsRepository;

import java.util.List;

public class MyBetsViewModel extends ViewModel {

    private MyBetsRepository myBetsRepository;

    public MyBetsViewModel() {
        myBetsRepository = new MyBetsRepository();
    }

    public LiveData<List<AllBetsResponse>> getBets(String token, String email) {
        return myBetsRepository.loadMyBets(token, email);
    }
}
