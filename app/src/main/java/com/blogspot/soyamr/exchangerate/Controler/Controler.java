package com.blogspot.soyamr.exchangerate.Controler;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blogspot.soyamr.exchangerate.ConsAndUtils;
import com.blogspot.soyamr.exchangerate.R;
import com.blogspot.soyamr.exchangerate.View.RublesRateActivity;
import com.blogspot.soyamr.exchangerate.View.ViewParent;
import com.blogspot.soyamr.exchangerate.model.GetApiData;
import com.blogspot.soyamr.exchangerate.model.JsonResponseBody;
import com.blogspot.soyamr.exchangerate.model.MoneyRate;
import com.blogspot.soyamr.exchangerate.model.MyAdapter;
import com.blogspot.soyamr.exchangerate.model.Rates;
import com.blogspot.soyamr.exchangerate.model.RetrofitClientInstance;
import com.jakewharton.threetenabp.AndroidThreeTen;

import org.threeten.bp.LocalDate;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.blogspot.soyamr.exchangerate.ConsAndUtils.FIND_YESTERDAY_DATA;
import static com.blogspot.soyamr.exchangerate.ConsAndUtils.MAIN_ACTIVITY;
import static com.blogspot.soyamr.exchangerate.ConsAndUtils.RUBLES_RATE_ACTIVITY;
import static com.blogspot.soyamr.exchangerate.ConsAndUtils.concatenateSymbolsIntoSingleArray;
import static com.blogspot.soyamr.exchangerate.ConsAndUtils.convertToRequiredCurrency;
import static com.blogspot.soyamr.exchangerate.ConsAndUtils.round;

public class Controler {
    ViewParent view;
    Rates todaysRates;
    Rates yesterdaysRates;
    String Date;

    public Controler(ViewParent view) {
        this.view = view;
    }

    int ctr = 0;

    public void getDataFromServer(String date, int callFrom, TextView textView, String... symboles) {
        Log.e("Amor", symboles[0]);
        Log.e("Amor ", ++ctr + "");

        String currencies = concatenateSymbolsIntoSingleArray(symboles);
        Log.e("Amor ", currencies);

        GetApiData getApiData = RetrofitClientInstance.getRetrofitInstance().create(GetApiData.class);
        Call<JsonResponseBody> call;

        if (callFrom == FIND_YESTERDAY_DATA)
            call = getApiData.getCurrencyDataYesterday(date, currencies);
        else
            call = getApiData.getCurrencyData(currencies);
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

                if (callFrom == MAIN_ACTIVITY) {
                    Date = jsonResponseBody.getDate();
                    TextView dateTextView =view.findViewById(R.id.MainActivityDateTextView);
                    dateTextView.setText(Date);
                    fillMainScreenButtonData(rates);//DONE
                } else if (callFrom == RUBLES_RATE_ACTIVITY) {
                    setDateONTheRecycclerView(jsonResponseBody.getDate());
                    getDataForYesterday(rates, textView, symboles);
                }
                else {
                    ProgressBar progressBar = view.findViewById(R.id.progressBar);
                    progressBar.setVisibility(View.GONE);
                    populateDataToRecyclerView(rates, textView);
                }

            }

            @Override
            public void onFailure(Call<JsonResponseBody> call, Throwable t) {
                textView.setVisibility(View.VISIBLE);
                textView.setText("error: " + t.toString());
            }
        });
    }

    private void setDateONTheRecycclerView(String date) {
        TextView tv = view.findViewById(R.id.textView7);
        tv.setText(date);
    }

    private void populateDataToRecyclerView(Rates rates, TextView textView) {
        yesterdaysRates = rates;
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
        List<MoneyRate> myDataset = makeListOfcurrentAndyesterdaysData();

        mAdapter = new MyAdapter(myDataset);
        recyclerView.setAdapter(mAdapter);
    }

    private List<MoneyRate> makeListOfcurrentAndyesterdaysData() {
        List<MoneyRate> listOfData = new ArrayList<>();

        listOfData.add(new MoneyRate(ConsAndUtils.AUD,ConsAndUtils.AUDname,R.drawable.aud, todaysRates.getAUD(),
                yesterdaysRates.getAUD(),todaysRates.getRUB(), yesterdaysRates.getRUB()));
        listOfData.add(new MoneyRate(ConsAndUtils.BRL,ConsAndUtils.BRLname,R.drawable.brl, todaysRates.getBRL(),
                yesterdaysRates.getBRL(),todaysRates.getRUB(), yesterdaysRates.getRUB()));
        listOfData.add(new MoneyRate(ConsAndUtils.CAD,ConsAndUtils.CADname,R.drawable.cad, todaysRates.getCAD(),
                yesterdaysRates.getCAD(),todaysRates.getRUB(), yesterdaysRates.getRUB()));
        //listOfData.add(new MoneyRate(ConsAndUtils.CNF,ConsAndUtils.CNFname,R.drawable.cnf, todaysRates.getCNF(),
               // yesterdaysRates.getCNF(),todaysRates.getRUB(),yesterdaysRates.getRUB()));
        listOfData.add(new MoneyRate(ConsAndUtils.CNY,ConsAndUtils.CNYname,R.drawable.cny, todaysRates.getCNY(),
                yesterdaysRates.getCNY(),todaysRates.getRUB(), yesterdaysRates.getRUB()));
        listOfData.add(new MoneyRate(ConsAndUtils.EGP,ConsAndUtils.EGPname,R.drawable.egp, todaysRates.getEGP(),
                yesterdaysRates.getEGP(),todaysRates.getRUB(), yesterdaysRates.getRUB()));
        listOfData.add(new MoneyRate(ConsAndUtils.GBP,ConsAndUtils.GBPname,R.drawable.gbp, todaysRates.getGBP(),
                yesterdaysRates.getGBP(),todaysRates.getRUB(), yesterdaysRates.getRUB()));
        listOfData.add(new MoneyRate(ConsAndUtils.HKD,ConsAndUtils.HKDname,R.drawable.hkd, todaysRates.getHKD(),
                yesterdaysRates.getHKD(),todaysRates.getRUB(), yesterdaysRates.getRUB()));
        listOfData.add(new MoneyRate(ConsAndUtils.INR,ConsAndUtils.INRname,R.drawable.inr, todaysRates.getINR(),
                yesterdaysRates.getINR(),todaysRates.getRUB(), yesterdaysRates.getRUB()));
        listOfData.add(new MoneyRate(ConsAndUtils.JPY,ConsAndUtils.JPYname,R.drawable.jpy, todaysRates.getJPY(),
                yesterdaysRates.getJPY(),todaysRates.getRUB(), yesterdaysRates.getRUB()));
        listOfData.add(new MoneyRate(ConsAndUtils.KRW,ConsAndUtils.KRWname,R.drawable.krw, todaysRates.getKRW(),
                yesterdaysRates.getKRW(),todaysRates.getRUB(), yesterdaysRates.getRUB()));
        listOfData.add(new MoneyRate(ConsAndUtils.MXN,ConsAndUtils.MXNname,R.drawable.mxn, todaysRates.getMXN(),
                yesterdaysRates.getMXN(),todaysRates.getRUB(), yesterdaysRates.getRUB()));
        listOfData.add(new MoneyRate(ConsAndUtils.NOK,ConsAndUtils.NOKname,R.drawable.nok, todaysRates.getNOK(),
                yesterdaysRates.getNOK(),todaysRates.getRUB(), yesterdaysRates.getRUB()));
        listOfData.add(new MoneyRate(ConsAndUtils.NZD,ConsAndUtils.NZDname,R.drawable.nzd, todaysRates.getNZD(),
                yesterdaysRates.getNZD(),todaysRates.getRUB(), yesterdaysRates.getRUB()));
        listOfData.add(new MoneyRate(ConsAndUtils.RUB,ConsAndUtils.RUBname,R.drawable.rub, todaysRates.getRUB(),
                yesterdaysRates.getRUB(),todaysRates.getRUB(), yesterdaysRates.getRUB()));
        listOfData.add(new MoneyRate(ConsAndUtils.SEK,ConsAndUtils.SEKname,R.drawable.sek, todaysRates.getSEK(),
                yesterdaysRates.getSEK(),todaysRates.getRUB(), yesterdaysRates.getRUB()));
        listOfData.add(new MoneyRate(ConsAndUtils.SGD,ConsAndUtils.SGDname,R.drawable.sgd, todaysRates.getSGD(),
                yesterdaysRates.getSGD(),todaysRates.getRUB(), yesterdaysRates.getRUB()));
        listOfData.add(new MoneyRate(ConsAndUtils.TRY,ConsAndUtils.TRYname,R.drawable._try, todaysRates.getTRY(),
                yesterdaysRates.getTRY(),todaysRates.getRUB(), yesterdaysRates.getRUB()));
        listOfData.add(new MoneyRate(ConsAndUtils.USD,ConsAndUtils.USDName,R.drawable.usd, todaysRates.getUSD(),
                yesterdaysRates.getUSD(),todaysRates.getRUB(), yesterdaysRates.getRUB()));
        listOfData.add(new MoneyRate(ConsAndUtils.ZAR,ConsAndUtils.ZARname,R.drawable.zar, todaysRates.getZAR(),
                yesterdaysRates.getZAR(),todaysRates.getRUB(), yesterdaysRates.getRUB()));
        return listOfData;
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

    private void getDataForYesterday(Rates rates, TextView textView, String... symboles) {

        todaysRates = rates;

        LocalDate today = LocalDate.now();
        LocalDate yesterday = today.minusDays(1);

        getDataFromServer(yesterday.toString(), FIND_YESTERDAY_DATA, textView, symboles);
    }


    /*
       opens RUBLES_RATE_ACTIVITY
        */
    public void onShowRublesRateActivityButtonClicked() {
        Intent intent = new Intent(view, RublesRateActivity.class);
        view.startActivity(intent);
    }
}
