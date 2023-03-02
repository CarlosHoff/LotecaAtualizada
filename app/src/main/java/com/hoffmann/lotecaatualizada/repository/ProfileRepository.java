package com.hoffmann.lotecaatualizada.repository;

import static com.hoffmann.lotecaatualizada.utilitario.Constantes.USER_URL;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.hoffmann.lotecaatualizada.client.UserService;
import com.hoffmann.lotecaatualizada.domain.response.ProfileResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProfileRepository {

    private final UserService userService;

    public ProfileRepository(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(USER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        userService = retrofit.create(UserService.class);
    }

    public LiveData<ProfileResponse> loadProfile(String token, String email) {
        MutableLiveData<ProfileResponse> profileResponseLiveData = new MutableLiveData<>();
        Call<ProfileResponse> profileRequest = userService.getUser(token, email);
        profileRequest.enqueue(new Callback<ProfileResponse>() {
            @Override
            public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {
                if (response.isSuccessful()) {
                    profileResponseLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ProfileResponse> call, Throwable t) {
                profileResponseLiveData.setValue(null);
            }
        });
        return profileResponseLiveData;
    }
}
