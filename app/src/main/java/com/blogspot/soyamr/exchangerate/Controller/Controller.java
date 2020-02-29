package com.blogspot.soyamr.exchangerate.Controller;

import android.content.Intent;

import com.blogspot.soyamr.exchangerate.CallFrom;
import com.blogspot.soyamr.exchangerate.Currency;
import com.blogspot.soyamr.exchangerate.View.BranchesAndATMsActivity;
import com.blogspot.soyamr.exchangerate.View.RublesRateActivity;
import com.blogspot.soyamr.exchangerate.View.ViewParent;
import com.blogspot.soyamr.exchangerate.model.Pojo.ATM;
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

import static com.blogspot.soyamr.exchangerate.Utils.concatenateSymbolsIntoSingleArray;

public class Controller {
    private ViewParent view;
    private static Rates todayRates;
    private static Rates yesterdayRates;
    private static String todayDate;

    public Controller(ViewParent view) {
        this.view = view;
    }

    /*
    this method is responsible for getting the data from server to the main screen and to the rates screen
     */
    public void FetchRates(CallFrom callFrom, String date, String... symbols) {

        String currencies = concatenateSymbolsIntoSingleArray(symbols);

        GetApiData getApiData = RetrofitClientInstance.getRetrofitInstance(CallFrom.RATES_URL)
                .create(GetApiData.class);
        Call<JsonResponseBody> call;
        if (callFrom == CallFrom.YESTERDAY)
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

                if (callFrom == CallFrom.TODAY) {
                    todayDate = jsonResponseBody.getDate();
                    todayRates = jsonResponseBody.getRates();
                    view.populateDateTextView(todayDate);
                    List<MoneyRate> dataList = new ArrayList<>();
                    dataList.add(new MoneyRate(todayRates.getUSD(), todayRates.getRUB()));
                    dataList.add(new MoneyRate(todayRates.getRUB()));
                    view.updateRecyclerViewData(dataList);
                } else if (callFrom == CallFrom.YESTERDAY) {
                    view.populateDateTextView(todayDate);
                    yesterdayRates = jsonResponseBody.getRates();
                    List<MoneyRate> myDataset = makeListOfcurrentAndyesterdaysData();
                    view.updateRecyclerViewData(myDataset);
                }
            }

            @Override
            public void onFailure(Call<JsonResponseBody> call, Throwable t) {
                view.showError("error: " + t.toString());
            }
        });
    }


    public void getDataForYesterday(String... symbols) {
        LocalDate today = LocalDate.now();
        LocalDate yesterday = today.minusDays(1);
        FetchRates(CallFrom.YESTERDAY, yesterday.toString(), symbols);
    }

    //sorry for the messy function but it's considered an improvement over what it was before.
    private List<MoneyRate> makeListOfcurrentAndyesterdaysData() {

        if(todayRates.getRUB()==0.0)
            FetchRates(CallFrom.TODAY, "", Currency.getArray());
        MoneyRate.RUBrateToday = todayRates.getRUB();
        MoneyRate.RUBrateYesterday = yesterdayRates.getRUB();
        List<MoneyRate> listOfData = new ArrayList<>();

        listOfData.add(new MoneyRate(Currency.AUD, todayRates.getAUD(), yesterdayRates.getAUD()));
        listOfData.add(new MoneyRate(Currency.BRL, todayRates.getBRL(), yesterdayRates.getBRL()));
        listOfData.add(new MoneyRate(Currency.CAD, todayRates.getCAD(), yesterdayRates.getCAD()));
        listOfData.add(new MoneyRate(Currency.CNY, todayRates.getCNY(), yesterdayRates.getCNY()));
        listOfData.add(new MoneyRate(Currency.EGP, todayRates.getEGP(), yesterdayRates.getEGP()));
        listOfData.add(new MoneyRate(Currency.GBP, todayRates.getGBP(), yesterdayRates.getGBP()));
        listOfData.add(new MoneyRate(Currency.HKD, todayRates.getHKD(), yesterdayRates.getHKD()));
        listOfData.add(new MoneyRate(Currency.INR, todayRates.getINR(), yesterdayRates.getINR()));
        listOfData.add(new MoneyRate(Currency.JPY, todayRates.getJPY(), yesterdayRates.getJPY()));
        listOfData.add(new MoneyRate(Currency.KRW, todayRates.getKRW(), yesterdayRates.getKRW()));
        listOfData.add(new MoneyRate(Currency.MXN, todayRates.getMXN(), yesterdayRates.getMXN()));
        listOfData.add(new MoneyRate(Currency.NOK, todayRates.getNOK(), yesterdayRates.getNOK()));
        listOfData.add(new MoneyRate(Currency.NZD, todayRates.getNZD(), yesterdayRates.getNZD()));
        listOfData.add(new MoneyRate(Currency.SEK, todayRates.getSEK(), yesterdayRates.getSEK()));
        listOfData.add(new MoneyRate(Currency.SGD, todayRates.getSGD(), yesterdayRates.getSGD()));
        listOfData.add(new MoneyRate(Currency.TRY, todayRates.getTRY(), yesterdayRates.getTRY()));
        listOfData.add(new MoneyRate(Currency.USD, todayRates.getUSD(), yesterdayRates.getUSD()));
        listOfData.add(new MoneyRate(Currency.ZAR, todayRates.getZAR(), yesterdayRates.getZAR()));
        listOfData.add(new MoneyRate(Currency.COP, todayRates.getCOP(), yesterdayRates.getCOP()));
        listOfData.add(new MoneyRate(Currency.CVE, todayRates.getCVE(), yesterdayRates.getCVE()));
        listOfData.add(new MoneyRate(Currency.ECU, todayRates.getUSD(), yesterdayRates.getUSD()));
        return listOfData;
    }

    public void onShowAtmsActivityButtonClicked() {
        Intent intent = new Intent(view, BranchesAndATMsActivity.class);
        view.startActivity(intent);
    }

    /*
       opens RUBLES_RATE_ACTIVITY
        */
    public void onShowRublesRateActivityButtonClicked() {
        Intent intent = new Intent(view, RublesRateActivity.class);
        view.startActivity(intent);
    }

    public void getATMdata() {
        GetApiData getApiData = RetrofitClientInstance.getRetrofitInstance(CallFrom.ATMS_URL)
                .create(GetApiData.class);
        Call<ArrayList<ATM>> call;

        call = getApiData.getBankomat();

        call.enqueue(new Callback<ArrayList<ATM>>() {
            @Override
            public void onResponse(Call<ArrayList<ATM>> call, Response<ArrayList<ATM>> response) {
                view.updateRecyclerViewData(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<ATM>> call, Throwable t) {
                view.showError("error: " + t.toString());
            }
        });
    }
}
