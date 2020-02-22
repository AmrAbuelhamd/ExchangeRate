package com.blogspot.soyamr.exchangerate.model.RetrofitComponents;


import com.blogspot.soyamr.exchangerate.model.Pojo.ATM;
import com.blogspot.soyamr.exchangerate.model.Pojo.JsonResponseBody;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GetApiData {
    @GET("/api/latest?access_key=e6b3b244a61ced8d75fc6e001e37eaa0&format=1")
    Call<JsonResponseBody> getCurrencyData(@Query("symbols") String currenciesCodes);

    //2013-03-16?
    //2020-02-37
    @GET("/api/{date}?access_key=e6b3b244a61ced8d75fc6e001e37eaa0&format=1")
    Call<JsonResponseBody> getHistoricalData(@Path("date") String date , @Query("symbols") String currenciesCodes);

    @GET("/api/bankomats")
    Call<ArrayList<ATM>> getBankomat();
}
