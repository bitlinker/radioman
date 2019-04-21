package com.github.bitlinker.radioman.di.ui;

import com.github.bitlinker.radioman.business.HistoryInteractor;
import com.github.bitlinker.radioman.business.PlayerInteractor;
import com.github.bitlinker.radioman.business.RadioInteractor;
import com.github.bitlinker.radioman.business.schedulers.SchedulersProvider;
import com.github.bitlinker.radioman.ui.error.UIErrorBus;
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
            UIErrorBus UIErrorBus
    ) {
        return new HistoryPresenter(
                historyInteractor,
                schedulersProvider,
                UIErrorBus
        );
    }

    @Provides
    @UiScope
    RadioListPresenter provideRadioListPresenter(
            RadioInteractor radioInteractor,
            SchedulersProvider schedulersProvider,
            UIErrorBus UIErrorBus) {
        return new RadioListPresenter(radioInteractor, schedulersProvider, UIErrorBus);
    }

    @Provides
    @UiScope
    PlayerPresenter providePlayerPresenter(
            PlayerInteractor playerInteractor,
            SchedulersProvider schedulersProvider,
            UIErrorBus UIErrorBus) {
        return new PlayerPresenter(playerInteractor, schedulersProvider, UIErrorBus);
    }
}
