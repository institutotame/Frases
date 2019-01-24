package com.nacho.tame.frases;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Controller implements Callback<List<Quote>> {

    ResponseListener listener;

    public Controller(ResponseListener listener){
        this.listener = listener;
    }

    static final String BASE_URL = "https://quotesondesign.com/wp-json/";

    public void start() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        QuoteAPI api = retrofit.create(QuoteAPI.class);

        Call<List<Quote>> call = api.loadQuotes();
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<List<Quote>> call, Response<List<Quote>> response) {
        if(response.isSuccessful()) {
            List<Quote> quoteList = response.body();
            if(quoteList.size()!= 0){
                Log.d("Controller", quoteList.get(0).getTitle());
                listener.onResponse(quoteList);
            }
        } else {
            Log.d("Controller",response.errorBody().toString());
            listener.onResponseFailed(response.errorBody().toString());
        }
    }

    @Override
    public void onFailure(Call<List<Quote>> call, Throwable t) {
        Log.d("Controller", t.getMessage());
        listener.onFailure(t.getMessage());
    }

    interface ResponseListener {
        void onResponse(List<Quote> list);
        void onResponseFailed(String response);
        void onFailure(String message);
    }
}
