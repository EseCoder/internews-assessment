package com.internews.assmt;

import android.content.Context;

import androidx.multidex.MultiDex;

import com.orm.SugarApp;

public class InternewsAssessmentApp extends SugarApp {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
