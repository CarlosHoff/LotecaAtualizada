package com.hoffmann.lotecaatualizada.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.hoffmann.lotecaatualizada.domain.response.AllBetsResponse;
import com.hoffmann.lotecaatualizada.repository.AllBetsRepository;

import java.util.ArrayList;
import java.util.List;

public class ListAllBetsViewModel extends ViewModel {

    private AllBetsRepository allBetsRepository;
    public ListAllBetsViewModel() {
        allBetsRepository = new AllBetsRepository();
    }

    public List<AllBetsResponse> searchBets(String token, String query) {
        List<AllBetsResponse> newAllBetsResponse = new ArrayList<>();
        List<AllBetsResponse> allBets = getBets(token).getValue();

        if (allBets == null) {
            return newAllBetsResponse;
        }

        for (AllBetsResponse item : allBets) {
            if (item.getUsuario().getApelido().equalsIgnoreCase(query)) {
                newAllBetsResponse.add(item);
            }
        }
        return newAllBetsResponse;
    }

    public LiveData<List<AllBetsResponse>> getBets(String token) {
        return allBetsRepository.loadAllBets(token);
    }


}
