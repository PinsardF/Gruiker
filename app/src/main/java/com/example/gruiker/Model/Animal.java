package com.example.gruiker.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Animal {

    @SerializedName("name")
    @Expose
    private String name = "";

    @SerializedName("primary_color")
    @Expose
    private String primary_color = "";

    @SerializedName("primary_color_dark")
    @Expose
    private String primary_color_dark = "";

    @SerializedName("beginning")
    @Expose
    private String beginning = "";

    @SerializedName("middle")
    @Expose
    private String middle = "";

    @SerializedName("ending")
    @Expose
    private String ending = "";

    public String getName(){
        return name;
    }

    public String getPrimary_color(){
        return primary_color;
    }

    public String getPrimary_color_dark(){
        return primary_color_dark;
    }

    public String getBeginning(){
        return beginning;
    }

    public String getMiddle(){
        return middle;
    }

    public String getEnding(){
        return ending;
    }
}
