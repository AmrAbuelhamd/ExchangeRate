package com.blogspot.soyamr.exchangerate.model;

import com.blogspot.soyamr.exchangerate.ConsAndUtils;

import static com.blogspot.soyamr.exchangerate.ConsAndUtils.*;

public class MoneyRate {
    String currencyName;

    //since eur is the base currency in the api i need to make my conversion here
    double curencyRateWithEURtoday;
    double curencyRateWithEURyesterday;
    double curencyRateWithRUByesterday;
    double curencyRateWithRUBtoday;

    double convertedRateWithRUBtoday;
    double convertedRateWithRUByesterday;

    boolean isIncreasing;

    public MoneyRate(String currencyName, double curencyRateWithEURtoday, double curencyRateWithEURyesterday) {
        this.currencyName = currencyName;
        this.curencyRateWithEURtoday = curencyRateWithEURtoday;
        this.curencyRateWithEURyesterday = curencyRateWithEURyesterday;

        convertedRateWithRUBtoday = convertToRequiredCurrency(
                curencyRateWithEURtoday,curencyRateWithRUBtoday);

        convertedRateWithRUByesterday = convertToRequiredCurrency(
                curencyRateWithEURyesterday,curencyRateWithRUByesterday);

        isIncreasing = (convertedRateWithRUBtoday - convertedRateWithRUByesterday) > 0;
    }
}
