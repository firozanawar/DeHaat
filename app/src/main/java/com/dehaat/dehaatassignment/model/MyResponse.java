package com.dehaat.dehaatassignment.model;

public class MyResponse {
    public int code;
    public String status;
    public String message;

    public MyResponse(int code, String status, String message) {
        this.code = code;
        this.status = status;
        this.message = message;
    }
}
