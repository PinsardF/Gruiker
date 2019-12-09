package com.example.gruiker.Model;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;

import com.example.gruiker.View.LanguagesFragment;

import kotlin.Suppress;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetDataController {

    private LanguagesFragment activity;

    public GetDataController(LanguagesFragment activity){
        this.activity = activity;
    }

    public void onCreate(){
        Call<Tweet> call = RestAlwaysData.get().getData();
        call.enqueue(new Callback<Tweet>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(@NonNull Call<Tweet> call, @NonNull Response<Tweet> response){
                if(response.isSuccessful()){//?
                    final Tweet tweet = response.body();//?
                    assert tweet != null;//?
                }//?
                //Faire quoi ?
            }

            @Override
            public void onFailure(Call<Tweet> call, Throwable t) {
                //DO NOTHING
                System.out.println("Problème !");
            }

        });
    }
}
