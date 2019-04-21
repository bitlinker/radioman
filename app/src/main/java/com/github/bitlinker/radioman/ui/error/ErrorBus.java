package com.github.bitlinker.radioman.ui.error;

import com.github.bitlinker.radioman.business.schedulers.SchedulersProvider;

import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.Subject;

public class ErrorBus {
    // TODO: try rxrelay?
    private final Subject<UiError> errorsSubject = BehaviorSubject.<UiError>create().toSerialized();
    private final SchedulersProvider schedulersProvider;

    public ErrorBus(SchedulersProvider schedulersProvider) {
        this.schedulersProvider = schedulersProvider;
    }

    public void add(UiError error) {
        errorsSubject.onNext(error);
    }

    public Observable<UiError> observable() {
        return errorsSubject
                .subscribeOn(schedulersProvider.ui());
        // TODO: check if need to serialize
        // .serialize();
    }
}
