package com.blogspot.soyamr.exchangerate;

import android.util.Log;

public class ConsAndUtils {
    //converting to the target currency using the the rub as the base instead of eur which is the
    //default in the api
    public static double convertToRequiredCurrency(double targetCurrency, double rub) {

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
    public static String[] makeArr() {

        String[] s = {"USD",
                "EUR",
                "JPY",
                "GBP",
                "AUD",
                "EGP",
                "CAD",
                "CNF",
                "CNY",
                "HKD",
                "NZD",
                "SEK",
                "KRW",
                "SGD",
                "NOK",
                "MXN",
                "INR",
                "ZAR",
                "TRY",
                "BRL",
                "RUB"};

        Log.e("Amor", s[0]);
        return s;
    }
    public static final int MAIN_ACTIVITY = 1;
    public static final int RUBLES_RATE_ACTIVITY = 0;
    public static final int FIND_YESTERDAY_DATA = 2;

    public static final String USD = "USD";
    public static final String USDName = "United States dollar";
    public static final String EUR = "EUR";
    public static final String JPY = "JPY";
    public static final String GBP = "GBP";
    public static final String AUD = "AUD";
    public static final String EGP = "EGP";
    public static final String CAD = "CAD";
    public static final String CNF = "CNF";
    public static final String CNY = "CNY";
    public static final String HKD = "HKD";
    public static final String NZD = "NZD";
    public static final String SEK = "SEK";
    public static final String KRW = "KRW";
    public static final String SGD = "SGD";
    public static final String NOK = "NOK";
    public static final String MXN = "MXN";
    public static final String INR = "INR";
    public static final String ZAR = "ZAR";
    public static final String TRY = "TRY";
    public static final String BRL = "BRL";
    public static final String RUB = "RUB";
    public static final String JPYname = "Japanese yen";
    public static final String GBPname = "Pound sterling";
    public static final String AUDname = "Australian dollar";
    public static final String EGPname = "The Great Egyptian Pound";
    public static final String CADname = "Canadian dollar";
    public static final String CNFname = "Swiss franc";
    public static final String CNYname = "Renminbi";
    public static final String HKDname = "Hong Kong dollar";
    public static final String NZDname = "New Zealand dollar";
    public static final String SEKname = "Swedish krona";
    public static final String KRWname = "South Korean won";
    public static final String SGDname = "Singapore dollar";
    public static final String NOKname = "Norwegian krone";
    public static final String MXNname = "Mexican peso";
    public static final String INRname = "Indian rupee";
    public static final String ZARname = "South African rand";
    public static final String TRYname = "Turkish lira";
    public static final String BRLname = "Brazilian real";
    public static final String RUBname = "Russian ruble";
}
