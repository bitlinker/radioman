package com.github.bitlinker.radioman.ui.history;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.github.bitlinker.radioman.business.HistoryInteractor;
import com.github.bitlinker.radioman.business.schedulers.SchedulersProvider;
import com.github.bitlinker.radioman.ui.error.ErrorBus;
import com.github.bitlinker.radioman.ui.error.UiError;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.internal.functions.Functions;
import timber.log.Timber;

@InjectViewState
public class HistoryPresenter extends MvpPresenter<HistoryView> {
    private final CompositeDisposable disposable = new CompositeDisposable();

    private final HistoryInteractor historyInteractor;
    private final SchedulersProvider schedulersProvider;
    private final ErrorBus errorBus;

    public HistoryPresenter(HistoryInteractor historyInteractor,
                            SchedulersProvider schedulersProvider,
                            ErrorBus errorBus) {
        this.historyInteractor = historyInteractor;
        this.schedulersProvider = schedulersProvider;
        this.errorBus = errorBus;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        disposable.add(
                historyInteractor.getAll()
                        .subscribeOn(schedulersProvider.ui())
                        .subscribe(
                                list -> getViewState().setList(list),
                                this::showError
                        )
        );
    }

    private void showError(Throwable throwable) {
        Timber.e(throwable);
        errorBus.add(UiError.builder()
                .type(UiError.Type.IO)
                .base(throwable)
                .build()
        );
    }

    public void onToolbarBackClicked() {
        getViewState().navigateBack();
    }

    public void onToolbarClearAllClicked() {
        disposable.add(
                historyInteractor.deleteAll()
                        .subscribeOn(schedulersProvider.ui())
                        .subscribe(
                                Functions.EMPTY_ACTION,
                                this::showError
                        )
        );
    }

    @Override
    public void onDestroy() {
        disposable.dispose();
        super.onDestroy();
    }
}
