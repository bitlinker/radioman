package com.github.bitlinker.radioman.di.app;

import dagger.Component;

@AppScope
@Component(modules = {AppModule.class, PlayerModule.class})
public interface AppComponent {
    // TODO
    //void inject();
}
