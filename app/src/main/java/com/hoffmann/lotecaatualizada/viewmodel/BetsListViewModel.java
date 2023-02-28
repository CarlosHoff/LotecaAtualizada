package com.hoffmann.lotecaatualizada.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.hoffmann.lotecaatualizada.domain.dto.BetUserDto;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class BetsListViewModel extends ViewModel {
    private final MutableLiveData<List<BetUserDto>> bettingCard = new MutableLiveData<>();

    public LiveData<List<BetUserDto>> getBettingCards() {
        return bettingCard;
    }

    public void swapItems(int from, int to) {
        List<BetUserDto> bets = bettingCard.getValue();
        Collections.swap(Objects.requireNonNull(bets), from, to);
        bettingCard.setValue(bets);
    }
}
