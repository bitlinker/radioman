package com.github.bitlinker.radioman.di;

import android.app.Activity;
import android.content.Context;

import com.github.bitlinker.radioman.di.app.AppComponent;
import com.github.bitlinker.radioman.di.app.AppModule;
import com.github.bitlinker.radioman.di.app.DaggerAppComponent;
import com.github.bitlinker.radioman.di.ui.DaggerUiComponent;
import com.github.bitlinker.radioman.di.ui.MainActivityModule;
import com.github.bitlinker.radioman.di.ui.UiComponent;

import javax.annotation.Nullable;

public class Injector {
    public static final String NAMED_APP_CONTEXT = "NAMED_APP_CONTEXT";
    public static final String NAMED_ACTIVITY_CONTEXT = "NAMED_ACTIVITY_CONTEXT";

    private static class InstanceHolder {
        private static Injector instance = new Injector();
    }

    public static Injector getInstance() {
        return InstanceHolder.instance;
    }

    private @Nullable
    AppComponent appComponent;

    private @Nullable
    UiComponent uiComponent;

    private Injector() {
    }

    public AppComponent getAppComponent(Context context) {
        synchronized (this) {
            if (appComponent == null) {
                appComponent = DaggerAppComponent.builder()
                        .appModule(new AppModule(context))
                        .build();
            }
        }
        return appComponent;
    }

    public UiComponent getUIComponent(Activity activity) {
        synchronized (this) {
            if (appComponent == null) {
                throw new IllegalStateException("Application component is missing");
            }
            if (uiComponent == null) {
                uiComponent = DaggerUiComponent.builder()
                        .appComponent(appComponent)
                        .mainActivityModule(new MainActivityModule(activity))
                        .build();
            }
        }
        return uiComponent;
    }

    public void destroyUIComponent(Activity activity) {
        // TODO: map by activity?
        synchronized (this) {
            uiComponent = null;
        }
    }
}
