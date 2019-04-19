package com.github.bitlinker.radioman.di.ui;

import com.github.bitlinker.radioman.navigation.MainNavigator;

import dagger.Module;
import dagger.Provides;

@Module
public class NavigationModule {
    @Provides
    @UiScope
    public MainNavigator provideMainNavigator() {
        return new MainNavigator();
    }
}
