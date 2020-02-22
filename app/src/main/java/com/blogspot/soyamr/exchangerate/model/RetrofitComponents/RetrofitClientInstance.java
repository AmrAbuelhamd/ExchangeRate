package com.blogspot.soyamr.exchangerate.model.RetrofitComponents;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance {
    private static Retrofit[] retrofit = new Retrofit[2];
    private static final String BASE_URL[] = new String[]{"http://data.fixer.io", "http://92.63.64.193:10010"};
    // i liked my idea here (^
    public static Retrofit getRetrofitInstance(int callFrom){

        if(retrofit[callFrom]==null){
            retrofit[callFrom] = new Retrofit.Builder()
                    .baseUrl(BASE_URL[callFrom])
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit[callFrom];
    }
}
