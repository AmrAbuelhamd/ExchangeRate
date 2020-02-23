package com.blogspot.soyamr.exchangerate.View;

import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.blogspot.soyamr.exchangerate.R;
import com.blogspot.soyamr.exchangerate.model.RecyclerViewCompenent.MoneyRate;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public abstract class ViewParent extends AppCompatActivity {

    public void showError(String errorMessage){
        Snackbar snackbar = Snackbar
                .make(findViewById(android.R.id.content), errorMessage, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    public void populateDateTextView(String date){
        TextView dateTextView = findViewById(R.id.dateTextView);
        dateTextView.setText(date);
    }

    public abstract <T>void updateRecyclerViewData(List<T> dataList);
}
