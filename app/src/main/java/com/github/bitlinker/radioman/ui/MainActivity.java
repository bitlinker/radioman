package com.github.bitlinker.radioman.ui;

import android.content.Intent;
import android.os.Bundle;

import com.github.bitlinker.radioman.R;
import com.github.bitlinker.radioman.ui.moxyx.MvpXAppCompatActivity;
import com.github.bitlinker.radioman.ui.player.PlayerActivity;
import com.github.bitlinker.radioman.ui.settings.SettingsFragment;

public class MainActivity extends MvpXAppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

//        Intent intent = new Intent(this, PlayerActivity.class);
//        startActivity(intent);

        // TODO: cicierone
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content, new SettingsFragment())
                .commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
