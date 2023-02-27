package com.hoffmann.lotecaatualizada.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.hoffmann.lotecaatualizada.domain.response.ProfileResponse;
import com.hoffmann.lotecaatualizada.repository.ProfileRepository;

public class PerfilViewModel extends ViewModel {

    private ProfileRepository profileRepository;

    public PerfilViewModel() {
        profileRepository = new ProfileRepository();
    }

    public LiveData<ProfileResponse> loadProfile(String token, String email) {
        return profileRepository.loadProfile(token, email);
    }
}
