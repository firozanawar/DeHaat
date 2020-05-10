package com.dehaat.dehaatassignment.rest;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AppRestClient {

    private static AppRestClient mInstance;
    int cacheSize = 10 * 1024 * 1024; // 10 MB
    private AppRestClientService appRestClientService;

    private AppRestClient() {
        setRestClient();
    }

    public static AppRestClient getInstance() {
        if (mInstance == null) {
            synchronized (AppRestClient.class) {
                if (mInstance == null)
                    mInstance = new AppRestClient();
            }
        }
        return mInstance;
    }

    public static void setAppRestClientNull() {
        mInstance = null;
    }

    public AppRestClientService getRestClient() {
        return appRestClientService;
    }

    private void setRestClient() {
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("") //ToDo: enter base url
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        appRestClientService = retrofit.create(AppRestClientService.class);
    }
}
