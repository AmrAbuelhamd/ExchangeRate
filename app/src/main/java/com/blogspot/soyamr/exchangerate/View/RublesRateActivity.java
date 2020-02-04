package com.blogspot.soyamr.exchangerate.View;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.blogspot.soyamr.exchangerate.Controler.Controler;
import com.blogspot.soyamr.exchangerate.R;

public class RublesRateActivity extends ViewParent {

    Controler controler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rubles_rate);

        controler = new Controler(this);

        controler.populateDataToRecyclerView(findViewById(R.id.error_message));

    }
}
