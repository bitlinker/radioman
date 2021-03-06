package com.github.bitlinker.radioman.ui.player;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.github.bitlinker.radioman.R;
import com.github.bitlinker.radioman.di.Injector;
import com.github.bitlinker.radioman.service.PlayerService;
import com.github.bitlinker.radioman.ui.common.BaseFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import dagger.Lazy;

public class PlayerFragment extends BaseFragment implements PlayerView {
    private Toolbar toolbar;
    private TextView tvTitle;
    private ImageView ivIcon;
    private TextView tvName;
    private TextView tvStatus;
    private ImageButton btnHistory;
    private ImageView ivProgress;
    private FloatingActionButton btnPlay;
    private ImageButton btnChooseStream;

    @InjectPresenter
    PlayerPresenter presenter;

    @Inject
    Lazy<PlayerPresenter> presenterProvider;

    @ProvidePresenter
    public PlayerPresenter providePresenter() {
        return presenterProvider.get();
    }

    public static PlayerFragment newInstance() {
        return new PlayerFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Injector.getInstance().getUIComponent(getActivity()).inject(this);
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_player, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        toolbar = view.findViewById(R.id.toolbar);
        tvTitle = view.findViewById(R.id.tvTitle);
        ivIcon = view.findViewById(R.id.ivIcon);
        tvName = view.findViewById(R.id.tvName);
        tvStatus = view.findViewById(R.id.tvStatus);
        btnHistory = view.findViewById(R.id.btnHistory);
        ivProgress = view.findViewById(R.id.ivProgress);
        btnPlay = view.findViewById(R.id.btnPlay);
        btnChooseStream = view.findViewById(R.id.btnChooseStream);

//        new Handler().postDelayed(() -> {
//            Drawable drawable = getActivity().getResources().getDrawable(R.drawable.playpause, null);
//            btnPlay.setImageDrawable(drawable);
//            ((Animatable) drawable).start();
//        }, 3000);


        btnPlay.setOnClickListener(v -> presenter.onPlayPauseClicked());
        btnHistory.setOnClickListener(v -> presenter.onHistoryClicked());
        btnChooseStream.setOnClickListener(v -> presenter.onChooseStreamClicked());

        toolbar.inflateMenu(R.menu.menu_settings);
        toolbar.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.menu_settings_settings) {
                presenter.onSettingsClicked();
                return true;
            }
            return false;
        });
        toolbar.setNavigationOnClickListener(v -> presenter.onMinimizeClicked());

        // TODO: ugly!
        Drawable drawable = ivProgress.getDrawable();
        if (drawable instanceof Animatable) {
            ((Animatable) drawable).start();
        }

        // TODO: start on play only; business wrapper
        Intent intent = new Intent(getActivity(), PlayerService.class);
        ContextCompat.startForegroundService(getActivity(), intent);
    }

    @Override
    public void setTitle(String title) {
        tvTitle.setText(title);
    }

    @Override
    public void setImage(Bitmap image) {
        ivIcon.setImageBitmap(image);
    }

    @Override
    public void setName(String name) {
        tvName.setText(name);
    }

    @Override
    public void setStatus(String status) {
        tvStatus.setText(status);
    }

    @Override
    public void setIsPlaying(boolean isPlaying) {
        btnPlay.setImageResource(isPlaying ?
                R.drawable.ic_play_arrow_black_24dp :
                R.drawable.ic_pause_black_24dp
        );
    }

    public void showChooseStreamDialog() {
        DialogFragment df = new DialogFragment();
        // TODO!
    }

    @Override
    public void setInProgress(boolean isInProgress) {
        btnPlay.setEnabled(!isInProgress);
    }

    @Override
    public void navigateBack() {
        getMainNavigator().back();
    }

    @Override
    public void navigateToSettings() {
        getMainNavigator().toSettingsScreen();
    }

    @Override
    public void navigateToHistory() {
        getMainNavigator().toHistoryScreen();
    }
}
