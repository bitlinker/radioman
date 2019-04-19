package com.github.bitlinker.radioman.di.ui;

import android.content.Context;

import com.github.bitlinker.radioman.di.Injector;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityModule {
    private final Context activityContext;

    public MainActivityModule(@Named(Injector.NAMED_ACTIVITY_CONTEXT) Context activityContext) {
        this.activityContext = activityContext;
    }

    @Provides
    @UiScope
    public @Named(Injector.NAMED_ACTIVITY_CONTEXT) Context provideActivityContext() {
        return activityContext;
    }
}
