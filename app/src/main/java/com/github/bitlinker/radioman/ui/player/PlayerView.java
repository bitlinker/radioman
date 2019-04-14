package com.github.bitlinker.radioman.ui.player;

import android.graphics.Bitmap;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface PlayerView extends MvpView {
    void setTitle(String title);

    void setImage(Bitmap image);

    void setName(String name);

    void setStatus(String status);

    void setIsPlaying(boolean isPlaying);

    void setInProgress(boolean isInProgress);
}
