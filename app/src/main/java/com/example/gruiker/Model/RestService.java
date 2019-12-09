package com.example.gruiker.Model;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;

public interface RestService {

    @FormUrlEncoded
    @GET("getData.php")
    Call<Tweet> getData();

    @FormUrlEncoded
    @GET("sendData.php")
    Call<Tweet> sendData(@Field("name") String name, @Field("primary_color") String primary_color,
                        @Field("primary_color_dark") String primary_color_dark, @Field("beginning") String beginning,
                        @Field("middle") String middle, @Field("ending") String ending);
}
