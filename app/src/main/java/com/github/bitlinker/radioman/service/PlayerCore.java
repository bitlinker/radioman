package com.github.bitlinker.radioman.service;

import android.content.Context;
import android.net.Uri;

import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;

import java.io.Closeable;

public class PlayerCore implements Closeable {
    //private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.86 Safari/537.36"; // TODO: utils
    private static final String USER_AGENT = "foobar2000/1.3.16"; // TODO: make it customizable
    private final SimpleExoPlayer player;
    private final DataSource.Factory dataSourceFactory;
    private final ExtractorsFactory extractorsFactory;

    // TODO: this is stream player:
//        ExoPlayer exoPlayer = ExoPlayer.Factory.newInstance(numRenderers, minBufSize, maxBufSize);
//        String url = "http://mp3.nashe.ru:80/ultra-128.mp3";
//        Uri uri = Uri.parse(url);
//        Allocator allocator = new DefaultAllocator(BUFFER_SEGMENT_SIZE);
//        DefaultDataSourceFactory.
//        DataSource dataSource = new DefaultUriDataSource(getApplicationContext(), USER_AGENT);
//        ExtractorSampleSource sampleSource = new ExtractorSampleSource(uri, dataSource, allocator, BUFFER_SEGMENT_COUNT * BUFFER_SEGMENT_SIZE);
//        audioRenderer = new MediaCodecAudioTrackRenderer(sampleSource);
//        exoPlayer.addListener(this);
//        exoPlayer.sendMessage(audioRenderer, MediaCodecAudioTrackRenderer.MSG_SET_VOLUME, volume);
//        exoPlayer.prepare(audioRenderer);
//        exoPlayer.setPlayWhenReady(true);

    public PlayerCore(Context context) {
        dataSourceFactory = new DefaultDataSourceFactory(context, null, new TrustAllHttpDataSourceFactory(USER_AGENT, null));
        extractorsFactory = new DefaultExtractorsFactory();

        TrackSelector trackSelector = new DefaultTrackSelector();
        player = ExoPlayerFactory.newSimpleInstance(context, trackSelector);
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
