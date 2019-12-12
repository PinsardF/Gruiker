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
        Call<Animal> call = RestAlwaysData.get().getData("Obligatoire");
        call.enqueue(new Callback<Animal>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(@NonNull Call<Animal> call, @NonNull Response<Animal> response){
                if(response.isSuccessful()){
                    final Animal animal = response.body();
                    assert animal != null;
                    //System.out.println(animal.getName());
                    System.out.println("La response fonctionne !");
                    System.out.println("animals :"+animal.getName());
                } else{
                    System.out.println("La response a échoué");
                }
                //Faire quoi ?
            }

            @Override
            public void onFailure(Call<Animal> call, Throwable t) {
                //DO NOTHING
                System.out.println("Problème !");
            }

        });
    }
}
