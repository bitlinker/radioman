package com.github.bitlinker.radioman.ui.main.bottomplayer;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.github.bitlinker.radioman.ui.moxyx.MvpXAppCompatFragment;
import com.github.bitlinker.radioman.ui.player.PlayerPresenter;
import com.github.bitlinker.radioman.ui.player.PlayerView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class BottomPlayerFragment extends MvpXAppCompatFragment implements PlayerView {
    @InjectPresenter
    PlayerPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // TODO
        return super.onCreateView(inflater, container, savedInstanceState);

        // TODO
        //presenter.onPlayPauseClicked();
    }

    @Override
    public void setTitle(String title) {

    }

    @Override
    public void setImage(Bitmap image) {

    }

    @Override
    public void setName(String name) {

    }

    @Override
    public void setStatus(String status) {

    }

    @Override
    public void setIsPlaying(boolean isPlaying) {

    }

    @Override
    public void setInProgress(boolean isInProgress) {

    }
}
