package com.github.bitlinker.radioman.di.app;

import com.github.bitlinker.radioman.business.HistoryInteractor;
import com.github.bitlinker.radioman.business.PlayerInteractor;
import com.github.bitlinker.radioman.business.RadioInteractor;
import com.github.bitlinker.radioman.business.schedulers.SchedulersProvider;

import dagger.Component;

@AppScope
@Component(modules = {AppModule.class, PlayerModule.class})
public interface AppComponent {
    SchedulersProvider provideSchedulersProvider();

    HistoryInteractor provideHistoryInteractor();

    RadioInteractor provideRadioInteractor();

    PlayerInteractor providePlayerInteractor();
}
