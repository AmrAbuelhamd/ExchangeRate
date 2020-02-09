package com.blogspot.soyamr.exchangerate.Controller;

import android.content.Intent;

import com.blogspot.soyamr.exchangerate.ConstAndUtils;
import com.blogspot.soyamr.exchangerate.R;
import com.blogspot.soyamr.exchangerate.View.RublesRateActivity;
import com.blogspot.soyamr.exchangerate.View.ViewParent;
import com.blogspot.soyamr.exchangerate.model.Pojo.JsonResponseBody;
import com.blogspot.soyamr.exchangerate.model.Pojo.Rates;
import com.blogspot.soyamr.exchangerate.model.RecyclerViewCompenent.MoneyRate;
import com.blogspot.soyamr.exchangerate.model.RetrofitComponents.GetApiData;
import com.blogspot.soyamr.exchangerate.model.RetrofitComponents.RetrofitClientInstance;

import org.threeten.bp.LocalDate;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.blogspot.soyamr.exchangerate.ConstAndUtils.concatenateSymbolsIntoSingleArray;

public class Controller {
    private ViewParent view;
    private static Rates todayRates;
    private static Rates yesterdayRates;
    private static String todayDate;

    public Controller(ViewParent view) {
        this.view = view;
    }


    public void FetchRates(int callFrom, String date, String... symbols) {

        String currencies = concatenateSymbolsIntoSingleArray(symbols);

        GetApiData getApiData = RetrofitClientInstance.getRetrofitInstance().create(GetApiData.class);
        Call<JsonResponseBody> call;
        if (callFrom == ConstAndUtils.YESTERDAY)
            call = getApiData.getHistoricalData(date, currencies);
        else
            call = getApiData.getCurrencyData(currencies);

        call.enqueue(new Callback<JsonResponseBody>() {
            @Override
            public void onResponse(Call<JsonResponseBody> call, Response<JsonResponseBody> response) {
                if (!response.isSuccessful()) {
                    view.showError(String.valueOf(response.code()));
                    return;
                }
                JsonResponseBody jsonResponseBody = response.body();

                if (!jsonResponseBody.getSuccess()) {
                    view.showError("error: " + jsonResponseBody.getError().getInfo());
                    return;
                }

                if (callFrom == ConstAndUtils.TODAY) {
                    todayDate = jsonResponseBody.getDate();
                    todayRates = jsonResponseBody.getRates();
                    view.populateDateTextView(todayDate);
                    List<MoneyRate> dataList = new ArrayList<>();
                    dataList.add(new MoneyRate(todayRates.getUSD(), todayRates.getRUB()));
                    dataList.add(new MoneyRate(todayRates.getRUB()));
                    view.populateData(dataList);
                } else if (callFrom == ConstAndUtils.YESTERDAY) {
                    view.populateDateTextView(todayDate);
                    yesterdayRates = jsonResponseBody.getRates();
                    List<MoneyRate> myDataset = makeListOfcurrentAndyesterdaysData();
                    view.populateData(myDataset);
                }
            }

            @Override
            public void onFailure(Call<JsonResponseBody> call, Throwable t) {
                view.showError("error: " + t.toString());
            }
        });
    }


    public void getDataForYesterday(String... symboles) {
        LocalDate today = LocalDate.now();
        LocalDate yesterday = today.minusDays(2);
        FetchRates(ConstAndUtils.YESTERDAY, yesterday.toString(), symboles);
    }


    /*
       opens RUBLES_RATE_ACTIVITY
        */
    public void onShowRublesRateActivityButtonClicked() {
        Intent intent = new Intent(view, RublesRateActivity.class);
        view.startActivity(intent);
    }

    private List<MoneyRate> makeListOfcurrentAndyesterdaysData() {
        MoneyRate.RUBrateToday = todayRates.getRUB();
        MoneyRate.RUBrateYesterday = yesterdayRates.getRUB();
        List<MoneyRate> listOfData = new ArrayList<>();

        listOfData.add(new MoneyRate(ConstAndUtils.AUD, ConstAndUtils.AUDname, R.drawable.aud,
                todayRates.getAUD(), yesterdayRates.getAUD()));
        listOfData.add(new MoneyRate(ConstAndUtils.BRL, ConstAndUtils.BRLname, R.drawable.brl, todayRates.getBRL(),
                yesterdayRates.getBRL()));
        listOfData.add(new MoneyRate(ConstAndUtils.CAD, ConstAndUtils.CADname, R.drawable.cad, todayRates.getCAD(),
                yesterdayRates.getCAD()));
        listOfData.add(new MoneyRate(ConstAndUtils.CNY, ConstAndUtils.CNYname, R.drawable.cny, todayRates.getCNY(),
                yesterdayRates.getCNY()));
        listOfData.add(new MoneyRate(ConstAndUtils.EGP, ConstAndUtils.EGPname, R.drawable.egp, todayRates.getEGP(),
                yesterdayRates.getEGP()));
        listOfData.add(new MoneyRate(ConstAndUtils.GBP, ConstAndUtils.GBPname, R.drawable.gbp, todayRates.getGBP(),
                yesterdayRates.getGBP()));
        listOfData.add(new MoneyRate(ConstAndUtils.HKD, ConstAndUtils.HKDname, R.drawable.hkd, todayRates.getHKD(),
                yesterdayRates.getHKD()));
        listOfData.add(new MoneyRate(ConstAndUtils.INR, ConstAndUtils.INRname, R.drawable.inr, todayRates.getINR(),
                yesterdayRates.getINR()));
        listOfData.add(new MoneyRate(ConstAndUtils.JPY, ConstAndUtils.JPYname, R.drawable.jpy, todayRates.getJPY(),
                yesterdayRates.getJPY()));
        listOfData.add(new MoneyRate(ConstAndUtils.KRW, ConstAndUtils.KRWname, R.drawable.krw, todayRates.getKRW(),
                yesterdayRates.getKRW()));
        listOfData.add(new MoneyRate(ConstAndUtils.MXN, ConstAndUtils.MXNname, R.drawable.mxn, todayRates.getMXN(),
                yesterdayRates.getMXN()));
        listOfData.add(new MoneyRate(ConstAndUtils.NOK, ConstAndUtils.NOKname, R.drawable.nok, todayRates.getNOK(),
                yesterdayRates.getNOK()));
        listOfData.add(new MoneyRate(ConstAndUtils.NZD, ConstAndUtils.NZDname, R.drawable.nzd, todayRates.getNZD(),
                yesterdayRates.getNZD()));
        listOfData.add(new MoneyRate(ConstAndUtils.SEK, ConstAndUtils.SEKname, R.drawable.sek, todayRates.getSEK(),
                yesterdayRates.getSEK()));
        listOfData.add(new MoneyRate(ConstAndUtils.SGD, ConstAndUtils.SGDname, R.drawable.sgd, todayRates.getSGD(),
                yesterdayRates.getSGD()));
        listOfData.add(new MoneyRate(ConstAndUtils.TRY, ConstAndUtils.TRYname, R.drawable._try, todayRates.getTRY(),
                yesterdayRates.getTRY()));
        listOfData.add(new MoneyRate(ConstAndUtils.USD, ConstAndUtils.USDName, R.drawable.usd, todayRates.getUSD(),
                yesterdayRates.getUSD()));
        listOfData.add(new MoneyRate(ConstAndUtils.ZAR, ConstAndUtils.ZARname, R.drawable.zar, todayRates.getZAR(),
                yesterdayRates.getZAR()));
        listOfData.add(new MoneyRate(ConstAndUtils.COP, ConstAndUtils.COPName, R.drawable.cop, todayRates.getCOP(),
                yesterdayRates.getCOP()));
        listOfData.add(new MoneyRate(ConstAndUtils.CVE, ConstAndUtils.CVEname, R.drawable.cve, todayRates.getCVE(),
                yesterdayRates.getCVE()));
        listOfData.add(new MoneyRate(ConstAndUtils.USD, ConstAndUtils.EcuadorianUSDName, R.drawable.usd_ecuador,
                todayRates.getUSD(), yesterdayRates.getUSD()));
        return listOfData;
    }
}
