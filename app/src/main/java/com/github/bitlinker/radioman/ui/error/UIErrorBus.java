package com.github.bitlinker.radioman.ui.error;

import com.github.bitlinker.radioman.business.schedulers.SchedulersProvider;
import com.jakewharton.rxrelay2.BehaviorRelay;
import com.jakewharton.rxrelay2.Relay;

import io.reactivex.Observable;

// TODO: check about serialization
public class UIErrorBus {
    private final Relay<UiError> errorsRelay = BehaviorRelay.<UiError>create().toSerialized();
    private final SchedulersProvider schedulersProvider;

    public UIErrorBus(SchedulersProvider schedulersProvider) {
        this.schedulersProvider = schedulersProvider;
    }

    public void add(UiError error) {
        errorsRelay.accept(error);
    }

    public Observable<UiError> observable() {
        return errorsRelay
                .subscribeOn(schedulersProvider.ui());
    }
}
