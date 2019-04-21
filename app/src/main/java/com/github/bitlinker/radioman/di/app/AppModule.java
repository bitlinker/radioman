package com.github.bitlinker.radioman.di.app;

import android.content.Context;

import com.github.bitlinker.radioman.business.HistoryInteractor;
import com.github.bitlinker.radioman.business.PlayerInteractor;
import com.github.bitlinker.radioman.business.RadioInteractor;
import com.github.bitlinker.radioman.business.history.HistoryRepo;
import com.github.bitlinker.radioman.business.history.StubHistoryRepo;
import com.github.bitlinker.radioman.business.radios.RadioRepo;
import com.github.bitlinker.radioman.business.radios.StubRadioRepo;
import com.github.bitlinker.radioman.business.schedulers.AndroidSchedulersProvider;
import com.github.bitlinker.radioman.business.schedulers.SchedulersProvider;
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
    public @Named(Injector.NAMED_APP_CONTEXT)
    Context provideAppContext() {
        return context;
    }

    @AppScope
    @Provides
    public SchedulersProvider provideSchedulersProvider() {
        return new AndroidSchedulersProvider();
    }

    // TODO: separate business?
    @AppScope
    @Provides
    public HistoryRepo provideHistoryRepo() {
        return new StubHistoryRepo();
    }

    @AppScope
    @Provides
    public RadioRepo provideRadioRepo() {
        return new StubRadioRepo();
    }

    @AppScope
    @Provides
    public HistoryInteractor provideHistoryInteractor(HistoryRepo historyRepo) {
        return new HistoryInteractor(historyRepo);
    }

    @AppScope
    @Provides
    public RadioInteractor provideRadioInteractor(RadioRepo radioRepo) {
        return new RadioInteractor(radioRepo);
    }

    @AppScope
    @Provides
    public PlayerInteractor providePlayerInteractor() {
        return new PlayerInteractor();
    }
}
