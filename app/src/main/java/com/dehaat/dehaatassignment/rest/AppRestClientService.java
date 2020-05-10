package com.dehaat.dehaatassignment.rest;

import com.dehaat.dehaatassignment.model.Data;
import com.dehaat.dehaatassignment.model.MyResponse;
import com.google.gson.JsonElement;

import retrofit2.Call;
import retrofit2.http.GET;

public interface AppRestClientService {

    @GET("/7c16277b18694b026821")
    Call<MyResponse> login();

    @GET("/dehaat/authors")
    Call<JsonElement> getListOfAuthors();

    @GET("/d88e632f6ba5c0060d35")
    Call<Data> getAuthors();

}
