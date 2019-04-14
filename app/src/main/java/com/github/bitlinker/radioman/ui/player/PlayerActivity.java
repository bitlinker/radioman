package com.github.bitlinker.radioman.ui.player;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.github.bitlinker.radioman.R;
import com.github.bitlinker.radioman.service.PlayerService;
import com.github.bitlinker.radioman.ui.moxyx.MvpXAppCompatActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.core.content.ContextCompat;

public class PlayerActivity extends MvpXAppCompatActivity implements PlayerView {
    private TextView tvTitle;
    private ImageView ivIcon;
    private TextView tvName;
    private TextView tvStatus;
    private ImageButton btnHistory;
    private FloatingActionButton btnPlay;
    private ImageButton btnChooseStream;

    @InjectPresenter
    PlayerPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        tvTitle = findViewById(R.id.tvTitle);
        ivIcon = findViewById(R.id.ivIcon);
        tvName = findViewById(R.id.tvName);
        tvStatus = findViewById(R.id.tvStatus);
        btnHistory = findViewById(R.id.btnHistory);
        btnPlay = findViewById(R.id.btnPlay);
        btnChooseStream = findViewById(R.id.btnChooseStream);

        btnPlay.setOnClickListener(v -> presenter.onPlayPauseClicked());
        btnHistory.setOnClickListener(v -> presenter.onHistoryClicked());
        btnChooseStream.setOnClickListener(v -> presenter.onChooseStreamClicked());

        // TODO: start on play only; business wrapper
        Intent intent = new Intent(this, PlayerService.class);
        ContextCompat.startForegroundService(this, intent);
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

    @Override
    public void setInProgress(boolean isInProgress) {
        btnPlay.setEnabled(!isInProgress);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
