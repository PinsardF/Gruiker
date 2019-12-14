package com.example.gruiker.Model;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RestService {

    @FormUrlEncoded
    @POST("getData.php")
    Call<ListAnimal> getData(@Field("obligatoire") String obligatoire);

    @FormUrlEncoded
    @POST("sendData.php")
    Call<Animal> sendData(@Field("name") String name, @Field("primary_color") String primary_color,
                        @Field("primary_color_dark") String primary_color_dark, @Field("beginning") String beginning,
                        @Field("middle") String middle, @Field("ending") String ending);

    @FormUrlEncoded
    @POST("delData.php")
    Call<Animal> delData(@Field("name") String name);
}
