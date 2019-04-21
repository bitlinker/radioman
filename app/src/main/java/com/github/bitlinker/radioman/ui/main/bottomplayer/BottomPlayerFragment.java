package com.github.bitlinker.radioman.ui.main.bottomplayer;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.github.bitlinker.radioman.R;
import com.github.bitlinker.radioman.di.Injector;
import com.github.bitlinker.radioman.ui.common.BaseFragment;
import com.github.bitlinker.radioman.ui.player.PlayerPresenter;
import com.github.bitlinker.radioman.ui.player.PlayerView;

import javax.inject.Inject;

import androidx.annotation.Nullable;
import dagger.Lazy;

public class BottomPlayerFragment extends BaseFragment implements PlayerView {
    private TextView tvTitle;
    private TextView tvName;
    private ImageButton btnPlayPause;

    @InjectPresenter
    PlayerPresenter presenter;

    @Inject
    Lazy<PlayerPresenter> presenterProvider;

    @ProvidePresenter
    public PlayerPresenter providePresenter() {
        return presenterProvider.get();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Injector.getInstance().getUIComponent(getActivity()).inject(this);
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.bottomplayer_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvTitle = view.findViewById(R.id.tvTitle);
        tvName = view.findViewById(R.id.tvName);
        btnPlayPause = view.findViewById(R.id.btnPlayPause);

        // TODO: custom presenter for the bottom player
        //presenter.onPlayPauseClicked();
        // TODO
        //presenter.onPlayPauseClicked();

        view.setOnClickListener(v -> {
            // TODO: via business
            getMainNavigator().toPlayerScreen();
        });
    }

    @Override
    public void setTitle(String title) {
        tvTitle.setText(title);
    }

    @Override
    public void setImage(Bitmap image) {

    }

    @Override
    public void setName(String name) {
        tvName.setText(name);
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

    @Override
    public void navigateBack() {
        // Do nothing
    }

    @Override
    public void navigateToSettings() {
        // Do nothing
    }

    @Override
    public void navigateToHistory() {
        // Do nothing
    }
}
