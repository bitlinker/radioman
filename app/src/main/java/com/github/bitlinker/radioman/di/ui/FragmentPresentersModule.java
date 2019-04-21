package com.github.bitlinker.radioman.di.ui;

import com.github.bitlinker.radioman.business.HistoryInteractor;
import com.github.bitlinker.radioman.business.RadioInteractor;
import com.github.bitlinker.radioman.business.schedulers.SchedulersProvider;
import com.github.bitlinker.radioman.ui.error.ErrorBus;
import com.github.bitlinker.radioman.ui.history.HistoryPresenter;
import com.github.bitlinker.radioman.ui.main.radiolist.RadioListPresenter;
import com.github.bitlinker.radioman.ui.player.PlayerPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class FragmentPresentersModule {
    @Provides
    @UiScope
    public HistoryPresenter provideHistoryPresenter(
            HistoryInteractor historyInteractor,
            SchedulersProvider schedulersProvider,
            ErrorBus errorBus
    ) {
        return new HistoryPresenter(
                historyInteractor,
                schedulersProvider,
                errorBus
        );
    }

    @Provides
    @UiScope
    RadioListPresenter provideRadioListPresenter(
            RadioInteractor radioInteractor,
            SchedulersProvider schedulersProvider,
            ErrorBus errorBus) {
        return new RadioListPresenter(radioInteractor, schedulersProvider, errorBus);
    }

    @Provides
    @UiScope
    PlayerPresenter providePlayerPresenter(
            RadioInteractor radioInteractor,
            SchedulersProvider schedulersProvider,
            ErrorBus errorBus) {
        // TODO: more dependencies
        return new PlayerPresenter();
    }
}
