package com.github.bitlinker.radioman.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.media.session.MediaControllerCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;

import com.github.bitlinker.radioman.domain.RadioStream;
import com.github.bitlinker.radioman.player.PlayerCore;
import com.github.bitlinker.radioman.player.PlayerMetadata;
import com.github.bitlinker.radioman.player.PlayerState;
import com.github.bitlinker.radioman.player.PlayerStateCallback;
import com.github.bitlinker.radioman.ui.notification.PlayerNotification;

// TODO: MediaBrowserService?
public class PlayerService extends Service {
    public static final String ACTION_PLAY = "action_play";
    public static final String ACTION_STOP = "action_stop";

    private static final String MEDIA_SESSION_TAG = "PlayerService";

    // TODO: global hack; rm
    public static PlayerService INSTANCE;

    private PlayerCore playerCore;

    private PlayerNotification playerNotification;

    private WakeLockWrapper playerWakeLock;

    private boolean isPlaying;

    private MediaSessionCompat mediaSession;

    private MediaControllerCompat mediaController;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        playerWakeLock = new WakeLockWrapper(getApplicationContext(), "PlayerService::wakelock");
        playerWakeLock.acquire();

        mediaSession = new MediaSessionCompat(this, MEDIA_SESSION_TAG);
        playerNotification = new PlayerNotification(getApplicationContext(), mediaSession);

        mediaController = new MediaControllerCompat(this, mediaSession);
        //mediaController.getTransportControls().play();
        // TODO: listen in ui like this
        /*mediaController.registerCallback(new MediaControllerCompat.Callback() {

        });*/

        // TODO

        initPlayer();
        mediaSession.setPlaybackState(new PlaybackStateCompat.Builder()
                .setState(PlaybackStateCompat.STATE_STOPPED, 0, 1.F)
                .build()
        );

        mediaSession.setFlags(MediaSessionCompat.FLAG_HANDLES_MEDIA_BUTTONS);

        INSTANCE = this;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        handleIntent(intent);
        startForeground(playerNotification.getId(), playerNotification.getNotificaction());
        return START_STICKY;
    }

    private void handleIntent(Intent intent) {
        if (intent == null || intent.getAction() == null)
            return;

        String action = intent.getAction();

        if (action.equalsIgnoreCase(ACTION_PLAY)) {
            mediaController.getTransportControls().play();
        } else if (action.equalsIgnoreCase(ACTION_STOP)) {
            mediaController.getTransportControls().stop();
        }
    }


    private void initPlayer() {
        playerCore = new PlayerCore(getApplicationContext(), new PlayerStateCallback() {
            @Override
            public void onStateChanged(PlayerState state) {
                // TODO
            }

            @Override
            public void onMetadataChanged(PlayerMetadata metadata) {
                // TODO
            }
        });
    }

    public void play(RadioStream stream) {
        playerCore.play(stream.getUrl());
        mediaSession.setPlaybackState(new PlaybackStateCompat.Builder()
                .setState(PlaybackStateCompat.STATE_PLAYING, 0, 1.F)
                .build()
        );
        //playerCore.play("http://mobil.metal-only.de:8000/");
        // com.google.android.exoplayer2.source.UnrecognizedInputFormatException: None of the available extractors (MatroskaExtractor, FragmentedMp4Extractor, Mp4Extractor, Mp3Extractor, AdtsExtractor, Ac3Extractor, TsExtractor, FlvExtractor, OggExtractor, PsExtractor, WavExtractor, AmrExtractor) could read the stream.
        isPlaying = true;

        // TODO: player state callback

        mediaSession.setCallback(new MediaSessionCompat.Callback() {
            @Override
            public void onPrepare() {
                super.onPrepare();
                // TODO: call player?
            }

            @Override
            public void onPlay() {
                super.onPlay();
            }

            @Override
            public void onStop() {
                super.onStop();
            }
        });
    }

    public void stop() {
        playerCore.stop();
        isPlaying = false;
        // TODO: call stop self if needed
        mediaSession.setPlaybackState(new PlaybackStateCompat.Builder()
                .setState(PlaybackStateCompat.STATE_STOPPED, 0, 1.F)
                .build()
        );
    }

    // TODO: rm
    public boolean isPlaying() {
        return isPlaying;
    }

    @Override
    public void onDestroy() {
        playerCore.close();
        playerWakeLock.release();
        mediaSession.release();
        super.onDestroy();
    }
}
