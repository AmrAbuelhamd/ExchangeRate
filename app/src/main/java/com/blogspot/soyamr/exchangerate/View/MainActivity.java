package com.blogspot.soyamr.exchangerate.View;

import android.Manifest;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.blogspot.soyamr.exchangerate.CallFrom;
import com.blogspot.soyamr.exchangerate.Controller.Controller;
import com.blogspot.soyamr.exchangerate.Currency;
import com.blogspot.soyamr.exchangerate.R;
import com.blogspot.soyamr.exchangerate.model.RecyclerViewCompenent.MoneyRate;

import java.util.ArrayList;
import java.util.List;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

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

        requestLocationPermission();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //ConstAndUtils.USD, ConstAndUtils.RUB, i could have used these instead but i will fetch all the data
        controller.FetchRates(CallFrom.TODAY,"", Currency.getArray());
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



    //region ask the user to garant the location once he opens the app

    private final int REQUEST_LOCATION_PERMISSION = 1;

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @AfterPermissionGranted(REQUEST_LOCATION_PERMISSION)
    public void requestLocationPermission() {
        String[] perms = {Manifest.permission.ACCESS_FINE_LOCATION};
        if(EasyPermissions.hasPermissions(this, perms)) {
//            Toast.makeText(this, "Permission already granted", Toast.LENGTH_SHORT).show();
        }
        else {
            EasyPermissions.requestPermissions(this, "Please grant the location permission", REQUEST_LOCATION_PERMISSION, perms);
        }
    }
    //endregion
}
