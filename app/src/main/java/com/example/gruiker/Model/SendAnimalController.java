package com.example.gruiker.Model;

import com.example.gruiker.View.LanguagesFragment;

public class SendAnimalController {

    private LanguagesFragment activity;
    public Animal newanimal;

    public SendAnimalController(LanguagesFragment activity, Animal animal){
        this.activity = activity;
        this.newanimal = animal;
    }

    public void onCreate(){
        RestAlwaysData.get().sendData(newanimal.getName(),newanimal.getPrimary_color(),newanimal.getPrimary_color_dark(),
                newanimal.getBeginning(), newanimal.getMiddle(), newanimal.getEnding());
    }
}
