package com.nicolas.vwarwj2.volkswagenagenda.pojo;

import android.support.annotation.NonNull;

public class SimpleModel {
    private String simpleText;

    public SimpleModel(@NonNull final String simpleText) {
        setSimpleText(simpleText);
    }

    @NonNull
    public String getSimpleText() {
        return simpleText;
    }

    public void setSimpleText(@NonNull final String simpleText) {
        this.simpleText = simpleText;
    }
}