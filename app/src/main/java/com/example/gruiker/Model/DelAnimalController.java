package com.example.gruiker.Model;

import com.example.gruiker.View.LanguagesFragment;

public class DelAnimalController {

    private LanguagesFragment activity;
    public Animal delanimal;

    public DelAnimalController(LanguagesFragment activity, Animal animal){
        this.activity = activity;
        this.delanimal = animal;
    }

    public void onCreate(){
        RestAlwaysData.get().delData(delanimal.getName());
    }
}
