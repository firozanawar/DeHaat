package com.dehaat.dehaatassignment.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Data {

    public int code;
    public String status;
    public String message;


    public Data(int code, String status, String message) {
        this.code = code;
        this.status = status;
        this.message = message;
    }

    @SerializedName("data")
    public ArrayList<Author> authors = new ArrayList<>();
}
