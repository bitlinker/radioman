package com.github.bitlinker.radioman.ui.main.radiolist;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.github.bitlinker.radioman.business.RadioInteractor;
import com.github.bitlinker.radioman.business.schedulers.SchedulersProvider;
import com.github.bitlinker.radioman.ui.error.UIErrorBus;
import com.github.bitlinker.radioman.ui.error.UiError;

import io.reactivex.disposables.CompositeDisposable;
import timber.log.Timber;

@InjectViewState
public class RadioListPresenter extends MvpPresenter<RadioListView> {
    private final CompositeDisposable disposable = new CompositeDisposable();
    private final RadioInteractor radioInteractor;
    private final SchedulersProvider schedulersProvider;
    private final UIErrorBus UIErrorBus;

    public RadioListPresenter(RadioInteractor radioInteractor,
                              SchedulersProvider schedulersProvider,
                              UIErrorBus UIErrorBus) {
        this.radioInteractor = radioInteractor;
        this.schedulersProvider = schedulersProvider;
        this.UIErrorBus = UIErrorBus;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        disposable.add(
                radioInteractor.getRadios()
                        .subscribeOn(schedulersProvider.ui())
                        .subscribe(
                                list -> getViewState().setList(list),
                                this::showError
                        )
        );
    }

    // TODO: common
    private void showError(Throwable throwable) {
        Timber.e(throwable);
        UIErrorBus.add(UiError.builder()
                .type(UiError.Type.IO)
                .base(throwable)
                .build()
        );
    }

    public void onAddClicked() {
        // TODO
    }

    @Override
    public void onDestroy() {
        disposable.dispose();
        super.onDestroy();
    }
}
