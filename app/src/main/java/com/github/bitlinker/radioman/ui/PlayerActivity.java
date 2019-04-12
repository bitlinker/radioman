package com.github.bitlinker.radioman.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.github.bitlinker.radioman.R;
import com.github.bitlinker.radioman.business.RadioRepo;
import com.github.bitlinker.radioman.service.PlayerCore;
import com.github.bitlinker.radioman.service.PlayerService;

public class PlayerActivity extends AppCompatActivity {
    private Button btnPlay;

    private final RadioRepo repo = new RadioRepo();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        btnPlay = findViewById(R.id.btnPlay);

        btnPlay.setOnClickListener(v -> {
            if (PlayerService.INSTANCE.isPlaying()) {
                PlayerService.INSTANCE.stop();
                btnPlay.setText("Play");
            } else {
                PlayerService.INSTANCE.play(repo.getRadio().getStreams().get(1));
                btnPlay.setText("Stop");
            }
        });

        Intent intent = new Intent(this, PlayerService.class);
        startService(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
