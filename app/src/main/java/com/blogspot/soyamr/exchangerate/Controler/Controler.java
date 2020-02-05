package com.blogspot.soyamr.exchangerate.Controler;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blogspot.soyamr.exchangerate.R;
import com.blogspot.soyamr.exchangerate.View.RublesRateActivity;
import com.blogspot.soyamr.exchangerate.View.ViewParent;
import com.blogspot.soyamr.exchangerate.model.GetApiData;
import com.blogspot.soyamr.exchangerate.model.JsonResponseBody;
import com.blogspot.soyamr.exchangerate.model.MyAdapter;
import com.blogspot.soyamr.exchangerate.model.Rates;
import com.blogspot.soyamr.exchangerate.model.RetrofitClientInstance;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.blogspot.soyamr.exchangerate.ConsAndUtils.MAIN_ACTIVITY;
import static com.blogspot.soyamr.exchangerate.ConsAndUtils.concatenateSymbolsIntoSingleArray;
import static com.blogspot.soyamr.exchangerate.ConsAndUtils.convertToRequiredCurrency;
import static com.blogspot.soyamr.exchangerate.ConsAndUtils.round;

public class Controler {
    ViewParent view;
    Rates todaysRates;

    public Controler(ViewParent view) {
        this.view = view;
    }


    public void getDataFromServer(int callFrom, TextView textView, String... symboles) {

        String currencies = concatenateSymbolsIntoSingleArray(symboles);

        GetApiData getApiData = RetrofitClientInstance.getRetrofitInstance().create(GetApiData.class);
        Call<JsonResponseBody> call = getApiData.getCurrencyData(currencies);
        call.enqueue(new Callback<JsonResponseBody>() {
            @Override
            public void onResponse(Call<JsonResponseBody> call, Response<JsonResponseBody> response) {
                if(!response.isSuccessful()){
                    textView.setVisibility(View.VISIBLE);
                    textView.setText("error: " + response.code());
                    return;
                }
                JsonResponseBody jsonResponseBody = response.body();

                if (jsonResponseBody == null || !jsonResponseBody.getSuccess()) {
                    textView.setVisibility(View.VISIBLE);
                    textView.setText("error: " + jsonResponseBody.getError().getInfo());
                    return;
                }

                Rates rates = jsonResponseBody.getRates();

                if (callFrom == MAIN_ACTIVITY)
                    fillMainScreenButtonData(rates);

                else populateDataToRecyclerView(rates);

            }

            @Override
            public void onFailure(Call<JsonResponseBody> call, Throwable t) {
                textView.setVisibility(View.VISIBLE);
                textView.setText("error: " + t.toString());
            }
        });
    }

    private void fillMainScreenButtonData(Rates rates) {

        if (rates == null)
            return;

        TextView usdRateTextView, eurRateTextView;
        usdRateTextView = view.findViewById(R.id.usd_rate);
        eurRateTextView = view.findViewById(R.id.eur_rate);

        double usdRateToRub = round(convertToRequiredCurrency(rates.getUSD(), rates.getRUB()));
        double eurRateToRub = round(rates.getRUB());

        usdRateTextView.setText(String.valueOf(usdRateToRub));
        eurRateTextView.setText(String.valueOf(eurRateToRub));
    }

    private void getDataForYesterday(Rates rates) {
        todaysRates = rates;

        DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        String yestradaysData = dateFormat.format(cal.getTime());


    }

    private void populateDataToRecyclerView(Rates rates) {

        RecyclerView recyclerView = view.findViewById(R.id.my_recycler_view);
        RecyclerView.Adapter mAdapter;
        RecyclerView.LayoutManager layoutManager;
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(view);
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        mAdapter = new MyAdapter(myDataset);
        recyclerView.setAdapter(mAdapter);

    }

    /*
       opens RUBLES_RATE_ACTIVITY
        */
    public void onShowRublesRateActivityButtonClicked() {
        Intent intent = new Intent(view, RublesRateActivity.class);
        view.startActivity(intent);
    }
}
