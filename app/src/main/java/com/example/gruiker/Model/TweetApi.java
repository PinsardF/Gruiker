package com.example.gruiker.Model;//A SUPPRIMER

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TweetApi {
    @GET("1.1/search/tweets.json?q=Gumby&count=3")
    Call<List<Tweet>> loadChanges();
}
