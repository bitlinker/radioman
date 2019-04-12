package com.github.bitlinker.radioman.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.github.bitlinker.radioman.domain.RadioStream;
import com.github.bitlinker.radioman.ui.PlayerNotification;

public class PlayerService extends Service {

    // TODO: global hack; rm
    public static PlayerService INSTANCE;

    private PlayerCore playerCore;

    private PlayerNotification playerNotification;

    private WakeLockWrapper playerWakeLock;

    private boolean isPlaying;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        playerNotification = new PlayerNotification(getApplicationContext());

        playerWakeLock = new WakeLockWrapper(getApplicationContext(), "PlayerService::wakelock");
        playerWakeLock.acquire();

        initPlayer();

        INSTANCE = this;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        startForeground(playerNotification.getId(), playerNotification.getNotificaction());
        return START_STICKY;
    }

    private void initPlayer() {
        playerCore = new PlayerCore(getApplicationContext());
    }

    public void play(RadioStream stream) {
        playerCore.play(stream.getUrl());
        //playerCore.play("http://mobil.metal-only.de:8000/");
        // com.google.android.exoplayer2.source.UnrecognizedInputFormatException: None of the available extractors (MatroskaExtractor, FragmentedMp4Extractor, Mp4Extractor, Mp3Extractor, AdtsExtractor, Ac3Extractor, TsExtractor, FlvExtractor, OggExtractor, PsExtractor, WavExtractor, AmrExtractor) could read the stream.
        isPlaying = true;

    }

    public void stop() {
        playerCore.stop();
        isPlaying = false;
        // TODO: call stop self if needed
    }

    @Override
    public void onDestroy() {
        playerCore.close();
        playerWakeLock.release();
        super.onDestroy();
    }

    public boolean isPlaying() {
        return isPlaying;
    }
}
