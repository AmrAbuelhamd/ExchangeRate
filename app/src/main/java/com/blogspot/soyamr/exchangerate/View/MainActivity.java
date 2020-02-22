package com.blogspot.soyamr.exchangerate.View;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.blogspot.soyamr.exchangerate.ConstAndUtils;
import com.blogspot.soyamr.exchangerate.Controller.Controller;
import com.blogspot.soyamr.exchangerate.R;
import com.blogspot.soyamr.exchangerate.model.RecyclerViewCompenent.MoneyRate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MainActivity extends ViewParent {
    Controller controller;
    TextView usdRate, eurRate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        controller = new Controller(this);
        usdRate = findViewById(R.id.usd_rate);
        eurRate = findViewById(R.id.eur_rate);
        //ConstAndUtils.USD, ConstAndUtils.RUB, i could have used these instead but i will fetch all the data
        controller.FetchRates(ConstAndUtils.TODAY,"", ConstAndUtils.CURRENCIES_ARRAY);

    }

    /*
    shows error messages from server in case the failure of server
     */
    public void showError(String errorMessage) {
        super.showError(errorMessage);
    }

    /*
    opens rubles rate activity
     */
    public void onClickListener(View view) {
        if (view.getId() == R.id.atms)
            controller.onShowAtmsActivityButtonClicked();

        else
            controller.onShowRublesRateActivityButtonClicked();

    }

    /*
    shows the eur and usd rates on the main screen buttons pri server asnwer
     */
    @Override
    public <T> void updateRecyclerViewData(List<T> dataList) {

        ArrayList<MoneyRate> data = (ArrayList) dataList;
        usdRate.setText(data.get(0).getConvertedRateToday());
        eurRate.setText(data.get(1).getConvertedRateToday());
    }

    @Override
    public void populateDateTextView(String date) {
        super.populateDateTextView(date);
    }



}
