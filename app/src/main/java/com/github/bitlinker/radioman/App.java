package com.github.bitlinker.radioman;

import android.app.Application;

import com.github.bitlinker.radioman.di.Injector;

import timber.log.Timber;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initLog();
        initDI();
    }

    private void initLog() {
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }

    private void initDI() {
        Injector.getInstance().getAppComponent(this);
    }
}


