package com.eternity.bystro;

import android.content.Context;

public class BystroDBManager {

    private BystroDatabase bystroHelper;

    public BystroDBManager(Context context) {
        bystroHelper = new BystroDatabase(context);
    }
}
