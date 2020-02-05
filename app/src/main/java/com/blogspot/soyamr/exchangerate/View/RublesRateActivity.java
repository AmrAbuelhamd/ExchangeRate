package com.blogspot.soyamr.exchangerate.View;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.blogspot.soyamr.exchangerate.Controler.Controler;
import com.blogspot.soyamr.exchangerate.R;
import com.blogspot.soyamr.exchangerate.model.Rates;

import java.lang.reflect.Field;

import static com.blogspot.soyamr.exchangerate.ConsAndUtils.RUBLES_RATE_ACTIVITY;
import static com.blogspot.soyamr.exchangerate.ConsAndUtils.makeArr;

public class RublesRateActivity extends ViewParent {

    Controler controler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rubles_rate);

        controler = new Controler(this);

        String[] arrOfCurenciesCode = makeArr();
        controler.getDataFromServer(RUBLES_RATE_ACTIVITY,
                findViewById(R.id.error_message), arrOfCurenciesCode);

    }
}
