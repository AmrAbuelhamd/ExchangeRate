package com.blogspot.soyamr.exchangerate.View;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blogspot.soyamr.exchangerate.ConstAndUtils;
import com.blogspot.soyamr.exchangerate.Controller.Controller;
import com.blogspot.soyamr.exchangerate.R;
import com.blogspot.soyamr.exchangerate.model.RecyclerViewCompenent.MoneyRate;
import com.blogspot.soyamr.exchangerate.model.RecyclerViewCompenent.MyAdapter;
import com.jakewharton.threetenabp.AndroidThreeTen;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;



public class RublesRateActivity extends ViewParent {
    RecyclerView recyclerView;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager layoutManager;
    ProgressBar progressBar;
    List<MoneyRate> dataList = new ArrayList<>();

    Controller controller;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rubles_rate);

        controller = new Controller(this);
        AndroidThreeTen.init(this);
        progressBar = findViewById(R.id.progressBar);
        controller.getDataForYesterday(ConstAndUtils.CURRENCIES_ARRAY );

        initializeTheRecyclerView();

    }

    private void initializeTheRecyclerView() {
        recyclerView = findViewById(R.id.my_recycler_view);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new MyAdapter(dataList);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void showError(String errorMessage) {
        super.showError(errorMessage);
    }

    @Override
    public void populateDateTextView(String date) {
        super.populateDateTextView(date);
    }

    @Override
    public <T> void updateRecyclerViewData(List<T> dataList) {

        progressBar.setVisibility(View.GONE);
        this.dataList.clear();
        this.dataList.addAll((Collection<? extends MoneyRate>) dataList);
        mAdapter.notifyDataSetChanged();
    }
}
