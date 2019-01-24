package com.nacho.tame.frases;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface QuoteAPI {

    @GET("posts?filter[orderby]=rand&filter[posts_per_page]=10")
    Call<List<Quote>> loadQuotes();

}