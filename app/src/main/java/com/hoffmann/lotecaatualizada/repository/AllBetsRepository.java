package com.hoffmann.lotecaatualizada.repository;

import static com.hoffmann.lotecaatualizada.utilitario.Constantes.LOTECA_URL;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.hoffmann.lotecaatualizada.client.BetsService;
import com.hoffmann.lotecaatualizada.domain.response.AllBetsResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AllBetsRepository {

    private final BetsService betsService;

    public AllBetsRepository(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(LOTECA_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        betsService = retrofit.create(BetsService.class);
    }

    public LiveData<List<AllBetsResponse>> loadAllBets(String token) {
        MutableLiveData<List<AllBetsResponse>> allBetsResponseLiveData = new MutableLiveData<>();
        Call<List<AllBetsResponse>> profileRequest = betsService.getAllBets(token);
        profileRequest.enqueue(new Callback<List<AllBetsResponse>>() {
            @Override
            public void onResponse(Call<List<AllBetsResponse>> call, Response<List<AllBetsResponse>> response) {
                if (response.isSuccessful()) {
                    allBetsResponseLiveData.setValue(response.body());
                } else {
                    allBetsResponseLiveData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<List<AllBetsResponse>> call, Throwable t) {
                allBetsResponseLiveData.setValue(null);
            }
        });

        return allBetsResponseLiveData;
    }


}
