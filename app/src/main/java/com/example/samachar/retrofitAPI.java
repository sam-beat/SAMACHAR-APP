package com.example.samachar;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface retrofitAPI {
    @GET
    Call<NewsMmodal> getAllNews(@Url String url);

    @GET
    Call<NewsMmodal>getNewsByCategory(@Url String url);
}
