package com.blogspot.soyamr.exchangerate;

public enum Currency {
    USD("United States dollar", R.drawable.usd),
    JPY("Japanese yen", R.drawable.jpy),
    GBP("Pound sterling", R.drawable.gbp),
    AUD("Australian dollar", R.drawable.aud),
    EGP("The Great Egyptian Pound", R.drawable.egp),
    CAD("Canadian dollar", R.drawable.cad),
    CNY("Renminbi", R.drawable.cny),
    HKD("Hong Kong dollar", R.drawable.hkd),
    NZD("New Zealand dollar", R.drawable.nzd),
    SEK("Swedish krona", R.drawable.sek),
    KRW("South Korean won", R.drawable.krw),
    SGD("Singapore dollar", R.drawable.sgd),
    NOK("Norwegian krone", R.drawable.nok),
    MXN("Mexican peso", R.drawable.mxn),
    INR("Indian rupee", R.drawable.inr),
    ZAR("South African rand", R.drawable.zar),
    TRY("Turkish lira", R.drawable._try),
    BRL("Brazilian real", R.drawable.brl),
    RUB("Russian Ruble", R.drawable.rub),
    COP("Colombian Peso^^", R.drawable.cop),
    CVE("Cape Verdean Escudo^^", R.drawable.cve),
    ECU("Ecuadorian Dollar^^", R.drawable.ecu);


    String name;
    int photoId;

    Currency(String name, int photoId) {
        this.name = name;
        this.photoId = photoId;
    }


    public int getPhotoId() {
        return photoId;
    }

    public String getName() {
        return name;
    }

    public static String[] getArray() {
        Currency[] currencies = values();
        String[] codes = new String[currencies.length];

        for (int i = 0; i < currencies.length; i++) {
            codes[i] = currencies[i].name();
        }

        return codes;
    }

}
