package com.dehaat.dehaatassignment.rest;

import com.dehaat.dehaatassignment.model.Author;
import com.dehaat.dehaatassignment.model.MyResponse;
import com.google.gson.JsonElement;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface AppRestClientService {

    @GET("/7c16277b18694b026821")
    Call<MyResponse> login();

//    @POST("/dehaat/login")
//    Call<JsonElement> login();

    @GET("/dehaat/authors")
    Call<JsonElement> getListOfAuthors();

}
