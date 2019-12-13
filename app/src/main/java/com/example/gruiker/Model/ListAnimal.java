package com.example.gruiker.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

public class ListAnimal implements Serializable {

    @SerializedName("animals_list")
    @Expose
    private List<Animal> animals_list = Collections.emptyList();

    public List<Animal> getAnimals_list(){
        return animals_list;
    }
}
