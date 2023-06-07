package com.hoffmann.lotecaatualizada.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.hoffmann.lotecaatualizada.domain.dto.BetUserDto;

import java.util.ArrayList;
import java.util.List;

public class MegaTolaViewModel extends ViewModel {

    private MutableLiveData<List<Long>> cardsBets = new MutableLiveData<>();
    private MutableLiveData<List<BetUserDto>> cardsBetsFinal = new MutableLiveData<>();
    private MutableLiveData<Boolean> hasVisitedActivity = new MutableLiveData<>();

    public void addBet(long betNumber) {
        List<Long> currentBets = cardsBets.getValue();
        if (currentBets.size() == 10) {
            return;
        }
        if (currentBets.contains(betNumber)) {
            currentBets.remove(betNumber);
        } else {
            currentBets.add(betNumber);
        }
        cardsBets.setValue(currentBets);
    }

    public LiveData<List<Long>> getCardsBets() {
        if (cardsBets.getValue() == null) {
            cardsBets.setValue(new ArrayList<>());
        }
        return cardsBets;
    }

    public LiveData<List<BetUserDto>> getCardsBetsFinal() {
        if (cardsBetsFinal.getValue() == null) {
            cardsBetsFinal.setValue(new ArrayList<>());
        }
        return cardsBetsFinal;
    }

    public void finalizeBets() {
        long[] mapper = createMapper(cardsBets.getValue());
        List<BetUserDto> currentBetsFinal = cardsBetsFinal.getValue();
        currentBetsFinal.add(new BetUserDto(mapper));
        cardsBetsFinal.setValue(currentBetsFinal);
    }

    public void setHasVisitedActivity(boolean value) {
        hasVisitedActivity.setValue(value);
    }

    public LiveData<Boolean> getHasVisitedActivity() {
        if (hasVisitedActivity.getValue() == null) {
            hasVisitedActivity.setValue(false);
        }
        return hasVisitedActivity;
    }

    private long[] createMapper(List<Long> cardBets) {
        long[] mapper = new long[10];
        for (int i = 0; i < 10; i++) {
            mapper[i] = cardBets.get(i);
        }
        return mapper;
    }
}
