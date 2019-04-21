package com.github.bitlinker.radioman.player;

import android.content.Context;
import android.net.Uri;

import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.RenderersFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.analytics.AnalyticsListener;
import com.google.android.exoplayer2.audio.AudioListener;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultAllocator;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;

import java.io.Closeable;

public class PlayerCore implements Closeable {
    //private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.86 Safari/537.36"; // TODO: utils
    private static final String USER_AGENT = "foobar2000/1.3.16"; // TODO: make it customizable
    private final SimpleExoPlayer player;
    private final DataSource.Factory dataSourceFactory;
    private final ExtractorsFactory extractorsFactory;

    public PlayerCore(Context context, PlayerStateCallback callback) {
        dataSourceFactory = new DefaultDataSourceFactory(context, null, new TrustAllHttpDataSourceFactory(USER_AGENT, null));
        extractorsFactory = new DefaultExtractorsFactory();

        TrackSelector trackSelector = new DefaultTrackSelector();

        DefaultLoadControl loadControl = new DefaultLoadControl.Builder()
                .setBufferDurationsMs(
                        DefaultLoadControl.DEFAULT_MIN_BUFFER_MS,
                        DefaultLoadControl.DEFAULT_MAX_BUFFER_MS,
                        DefaultLoadControl.DEFAULT_BUFFER_FOR_PLAYBACK_MS,
                        DefaultLoadControl.DEFAULT_BUFFER_FOR_PLAYBACK_AFTER_REBUFFER_MS
                )
                .createDefaultLoadControl();

        RenderersFactory renderersFactory = new DefaultRenderersFactory(context);
        player = ExoPlayerFactory.newSimpleInstance(context, renderersFactory, trackSelector, loadControl);

        // TODO: callback

        /*player.addAnalyticsListener(new AnalyticsListener() {
        });*/
        player.addListener(new Player.EventListener() {

            @Override
            public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
                //SimpleExoPlayer.STATE_BUFFERING
                int k = 0;
            }

            @Override
            public void onPlayerError(ExoPlaybackException error) {
                int k = 0;
            }
        });

        player.addMetadataOutput(metadata -> {
            Metadata.Entry entry = metadata.get(0);
            // TODO: check
        });
    }

    public void play(String url) {
        MediaSource mediaSource = new ExtractorMediaSource(Uri.parse(url), dataSourceFactory, extractorsFactory, null, null);
        player.prepare(mediaSource);
        player.setPlayWhenReady(true);
    }

    public void stop() {
        player.stop();
    }

    @Override
    public void close() {
        stop();
        player.release();
    }
}
