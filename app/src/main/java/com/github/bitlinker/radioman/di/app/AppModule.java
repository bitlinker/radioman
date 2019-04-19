package com.github.bitlinker.radioman.di.app;

import android.content.Context;

import com.github.bitlinker.radioman.di.Injector;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    private final Context context;

    public AppModule(@Named(Injector.NAMED_APP_CONTEXT) Context context) {
        this.context = context;
    }

    @AppScope
    @Provides
    public @Named(Injector.NAMED_APP_CONTEXT) Context provideAppContext() {
        return context;
    }
}
