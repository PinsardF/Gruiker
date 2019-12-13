package com.example.gruiker.Model;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;

import com.example.gruiker.View.LanguagesFragment;
import com.google.gson.Gson;

import java.util.List;

import kotlin.Suppress;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetDataController {

    private LanguagesFragment activity;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public GetDataController(LanguagesFragment activity){
        this.activity = activity;
    }

    public void onCreate(){

        sharedPreferences = activity.getContext().getSharedPreferences("", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        Call<ListAnimal> call = RestAlwaysData.get().getData("Obligatoire");
        call.enqueue(new Callback<ListAnimal>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(@NonNull Call<ListAnimal> call, @NonNull Response<ListAnimal> response){
                if(response.isSuccessful()){
                    final ListAnimal animal = response.body();
                    assert animal != null;
                    String animals_list_json = new Gson().toJson(animal);
                    editor.putString("animals_list",animals_list_json);
                    editor.apply();

                } else{
                    System.out.println("La response a échoué");
                }
            }

            @Override
            public void onFailure(Call<ListAnimal> call, Throwable t) {
                System.out.println("Problème : onFailure");
            }

        });
    }
}
