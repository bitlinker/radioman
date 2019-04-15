package com.github.bitlinker.radioman.di;

import android.app.Activity;
import android.content.Context;

public class Injector {
    private static class InstanceHolder {
        private static Injector instance = new Injector();
    }

    public static Injector getInstance() {
        return InstanceHolder.instance;
    }

    private Injector() {
    }

    public void createAppComponent(Context context) {
        // TODO
    }

    public void createUIComponent(Activity activity) {
        // TODO
    }

    public void destroyUIComponent() {

    }
}
