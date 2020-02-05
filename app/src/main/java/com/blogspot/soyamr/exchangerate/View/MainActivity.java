package com.blogspot.soyamr.exchangerate.View;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.blogspot.soyamr.exchangerate.Controler.Controler;
import com.blogspot.soyamr.exchangerate.R;

import static com.blogspot.soyamr.exchangerate.ConsAndUtils.MAIN_ACTIVITY;
import static com.blogspot.soyamr.exchangerate.ConsAndUtils.RUB;
import static com.blogspot.soyamr.exchangerate.ConsAndUtils.USD;

public class MainActivity extends ViewParent {
    Controler controler;
    TextView usdRate, eurRate;
    private TextView errorTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        controler = new Controler(this);

        controler.getDataFromServer(
                MAIN_ACTIVITY, findViewById(R.id.mainlayout_error_message), USD, RUB);
    }

    public void showCourse(View view) {
        controler.onShowRublesRateActivityButtonClicked();
    }
}
