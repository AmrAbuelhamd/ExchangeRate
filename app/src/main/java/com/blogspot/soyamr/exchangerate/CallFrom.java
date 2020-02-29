package com.blogspot.soyamr.exchangerate;

public enum CallFrom {
    RATES_URL(0),
    ATMS_URL(1),
    TODAY,
    YESTERDAY;

    int index;

    CallFrom(int i) {
        index = i;
    }
    CallFrom() {
    }

    public int getIndex() {
        return index;
    }
}
