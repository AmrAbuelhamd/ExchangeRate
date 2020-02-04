package com.blogspot.soyamr.exchangerate.Controler;

import android.content.Intent;
import android.widget.TextView;

import com.blogspot.soyamr.exchangerate.View.MainActivity;
import com.blogspot.soyamr.exchangerate.View.RublesRateActivity;
import com.blogspot.soyamr.exchangerate.View.ViewParent;
import com.blogspot.soyamr.exchangerate.model.GetApiData;
import com.blogspot.soyamr.exchangerate.model.JsonResponseBody;
import com.blogspot.soyamr.exchangerate.model.Rates;
import com.blogspot.soyamr.exchangerate.model.RetrofitClientInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Controler {
    ViewParent view;


    public Controler(ViewParent view) {
        this.view = view;
    }

    /*
    opens the activity where the all rates are presented
     */
    public void onShowRublesRateActivityButtonClicked() {
        Intent intent = new Intent(view, RublesRateActivity.class);
        view.startActivity(intent);
    }

    public void populateDataToRecyclerView(TextView textView) {
        Rates rates = getDataFromServer(textView);
    }

    private Rates getDataFromServer(TextView textView) {
        GetApiData getApiData = RetrofitClientInstance.getRetrofitInstance().create(GetApiData.class);
        Call<JsonResponseBody> call = getApiData.getCurrencyData("RUB,USD,JPY,GBP,AUD,EGP");
        call.enqueue(new Callback<JsonResponseBody>() {
            @Override
            public void onResponse(Call<JsonResponseBody> call, Response<JsonResponseBody> response) {
                if(!response.isSuccessful()){
                    //todo do something
                    return;
                }
                JsonResponseBody jsonResponseBody = response.body();
                if(jsonResponseBody==null && !jsonResponseBody.isSuccess()){
                    //todo show error message
                }

                Rates rates = jsonResponseBody.getRates();
                //todo continue from here
                textView.setText(rates.getCNF()+" ");

            }

            @Override
            public void onFailure(Call<JsonResponseBody> call, Throwable t) {

            }
        });
        return null;
    }
}
