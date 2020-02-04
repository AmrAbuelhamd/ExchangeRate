package com.blogspot.soyamr.exchangerate.model;

public class JsonResponseBody {
    boolean success;
    String date;
    Rates rates;

    public boolean isSuccess() {
        return success;
    }

    public String getDate() {
        return date;
    }

    public Rates getRates() {
        return rates;
    }
}
