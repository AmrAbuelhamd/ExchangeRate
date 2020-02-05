package com.blogspot.soyamr.exchangerate.model;

public class JsonResponseBody {
    boolean success;
    String date;
    Rates rates;
    Error error;

    public Error getError() {
        return error;
    }

    public boolean getSuccess() {
        return success;
    }

    public String getDate() {
        return date;
    }

    public Rates getRates() {
        return rates;
    }
}
