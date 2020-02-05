package com.blogspot.soyamr.exchangerate;

import com.blogspot.soyamr.exchangerate.model.Rates;

import java.lang.reflect.Field;

public class ConsAndUtils {
    //converting to the target currency using the the rub as the base instead of eur which is the
    //default in the api
    public static double convertToRequiredCurrency(double targetCurrency, double rub) {

        return rub / targetCurrency;
    }

    public static String concatenateSymbolsIntoSingleArray(String[] symbols) {
        StringBuilder stringBuilder = new StringBuilder("");
        for (String item : symbols) {
            stringBuilder.append(item).append(",");
        }

        return stringBuilder.deleteCharAt(stringBuilder.length() - 1).toString();
    }

    public static double round(double value) {
        int places = 2;
        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
    public static String[] makeArr() {
        Field field[] = Rates.class.getFields();
        String[] s = new String[field.length];
        int ctr = 0;
        for (Field f : field) {
            s[ctr++] = f.getName();
        }
        return s;
    }
    public static final int MAIN_ACTIVITY = 1;
    public static final int RUBLES_RATE_ACTIVITY = 0;

    public static final String USD = "USD";
//    public static final String EUR = "EUR";
//    public static final String JPY = "JPY";
//    public static final String GBP = "GBP";
//    public static final String AUD = "AUD";
//    public static final String EGP = "EGP";
//    public static final String CAD = "CAD";
//    public static final String CNF = "CNF";
//    public static final String CNY = "CNY";
//    public static final String HKD = "HKD";
//    public static final String NZD = "NZD";
//    public static final String SEK = "SEK";
//    public static final String KRW = "KRW";
//    public static final String SGD = "SGD";
//    public static final String NOK = "NOK";
//    public static final String MXN = "MXN";
//    public static final String INR = "INR";
//    public static final String ZAR = "ZAR";
//    public static final String TRY = "TRY";
//    public static final String BRL = "BRL";
    public static final String RUB = "RUB";
}
