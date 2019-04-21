package com.github.bitlinker.radioman.ui.player;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.github.bitlinker.radioman.business.PlayerInteractor;
import com.github.bitlinker.radioman.business.schedulers.SchedulersProvider;
import com.github.bitlinker.radioman.ui.error.UIErrorBus;

@InjectViewState
public class PlayerPresenter extends MvpPresenter<PlayerView> {
    // TODO: interactor
    //private final RadioRepo repo = new RadioRepo();

    private final PlayerInteractor playerInteractor;
    private final SchedulersProvider schedulersProvider;
    private final UIErrorBus UIErrorBus;

    public PlayerPresenter(PlayerInteractor playerInteractor, SchedulersProvider schedulersProvider, UIErrorBus UIErrorBus) {
        this.playerInteractor = playerInteractor;
        this.schedulersProvider = schedulersProvider;
        this.UIErrorBus = UIErrorBus;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().setIsPlaying(true);
        getViewState().setInProgress(true);
        getViewState().setName("Long name - loooong name loooooooooooooooooooooooooooooooooooooooooooooooooooooong");
        getViewState().setTitle("Long title loooong name loooooooooooooooooooooooooooooooooooooooooooooooooooooong");
        getViewState().setStatus("Long status loooong name loooooooooooooooooooooooooooooooooooooooooooooooooooooong");
    }

    public void onPlayPauseClicked() {
        // TODO
    }

    public void onHistoryClicked() {
        getViewState().navigateToHistory();
    }

    public void onChooseStreamClicked() {

    }

    public void onMinimizeClicked() {
        getViewState().navigateBack();
    }

    public void onSettingsClicked() {
        getViewState().navigateToSettings();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
