package com.blogspot.soyamr.exchangerate.model;

import static com.blogspot.soyamr.exchangerate.ConsAndUtils.convertToRequiredCurrency;

public class MoneyRate {
    public String currencyName;
    public int getImageRecourseId;
    String currencyCode;

    //since eur is the base currency in the api i need to make my conversion here
    private double curencyRateWithEURtoday;
    private double curencyRateWithEURyesterday;

    private double curencyRateWithRUByesterday;
    private double curencyRateWithRUBtoday;

    double convertedRateWithRUBtoday;
    private double convertedRateWithRUByesterday;

    boolean isIncreasing;

    public MoneyRate(String currencyCode, String currencyName, int iconeId,
                     double curencyRateWithEURtoday, double curencyRateWithEURyesterday
            , double curencyRateWithRUBtoday, double curencyRateWithRUByesterday) {
        getImageRecourseId = iconeId;
        this.currencyName = currencyName;
        this.currencyCode = currencyCode;
        this.curencyRateWithEURtoday = curencyRateWithEURtoday;
        this.curencyRateWithEURyesterday = curencyRateWithEURyesterday;

        convertedRateWithRUBtoday = convertToRequiredCurrency(
                curencyRateWithEURtoday,curencyRateWithRUBtoday);

        convertedRateWithRUByesterday = convertToRequiredCurrency(
                curencyRateWithEURyesterday,curencyRateWithRUByesterday);

        isIncreasing = (convertedRateWithRUBtoday - convertedRateWithRUByesterday) > 0;
    }
}
