package com.blogspot.soyamr.exchangerate.model;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetApiData {
    @GET("/api/latest?access_key=e6b3b244a61ced8d75fc6e001e37eaa0&format=1")
    Call<JsonResponseBody> getCurrencyData(@Query("symbols") String currenciesCodes);
}
