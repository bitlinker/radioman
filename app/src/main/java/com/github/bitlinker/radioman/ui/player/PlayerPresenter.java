package com.github.bitlinker.radioman.ui.player;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.github.bitlinker.radioman.business.radios.RadioRepo;
import com.github.bitlinker.radioman.service.PlayerService;

@InjectViewState
public class PlayerPresenter extends MvpPresenter<PlayerView> {
    // TODO: interactor
    //private final RadioRepo repo = new RadioRepo();

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
//        if (PlayerService.INSTANCE.isPlaying()) {
//            PlayerService.INSTANCE.stop();
//        } else {
//            PlayerService.INSTANCE.play(repo.getAll().get(0).getStreams().get(1));
//        }
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
