package com.blogspot.soyamr.exchangerate.model.RecyclerViewCompenent;

import com.blogspot.soyamr.exchangerate.Currency;

import static com.blogspot.soyamr.exchangerate.Utils.convertToRequiredCurrency;
import static com.blogspot.soyamr.exchangerate.Utils.round;

//since eur is the base currency in the api i need to make my conversion here
public class MoneyRate {
    public static double RUBrateToday;
    public static double RUBrateYesterday;

    private String currencyName;
    private int imageRecourseId;
    private String currencyCode;
    
    private boolean isIncreasing;
    private double convertedRateToday;

    public MoneyRate(Currency currency,
                     double rateToday, double rateYesterday) {

        this.imageRecourseId = currency.getPhotoId();
        this.currencyName = currency.getName();
        this.currencyCode = currency.toString();


        convertedRateToday = convertToRequiredCurrency(
                rateToday, RUBrateToday);

        double convertedRateYesterday = convertToRequiredCurrency(
                rateYesterday, RUBrateYesterday);

        isIncreasing = (convertedRateToday - convertedRateYesterday) > 0;

    }

    public MoneyRate(double rate, double rateWithRub) {
        this.convertedRateToday = convertToRequiredCurrency(rate, rateWithRub);
    }

    //in case of eur there's no need to make any conversion
    public MoneyRate(double rub) {
        convertedRateToday= rub;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public int getImageRecourseId() {
        return imageRecourseId;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public String getConvertedRateToday() {
        return String.valueOf(round(convertedRateToday));
    }

    public boolean isIncreasing() {
        return isIncreasing;
    }

}
