package com.blogspot.soyamr.exchangerate;

import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Utils {


    //converting to the target currency using the the rub as the base instead of eur which is the
    //default in the api
    public static double convertToRequiredCurrency(double targetCurrency, double rub) {
        // ^^
        if (targetCurrency == 0)
            return 0;
        return rub / targetCurrency;
    }

    public static String concatenateSymbolsIntoSingleArray(String[] symbols) {
        StringBuilder stringBuilder = new StringBuilder("");
        for (String item : symbols) {
            stringBuilder.append(",").append(item);
        }

        return stringBuilder.deleteCharAt(0).toString();
    }

    public static double round(double value) {
        int places = 2;
        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    public static boolean isWorkingNow(String startTime, String endTime) {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        String currentTime = new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date());
        Date sta=null;
        Date end=null;
        Date curr=null;
        try {
            sta = dateFormat.parse(startTime);
            end = dateFormat.parse(endTime);
            curr = dateFormat.parse(currentTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Log.e("time",startTime+" : "+currentTime+" : "+endTime);
        return (curr.after(sta)&&curr.before(end));

    }

}
