package com.github.bitlinker.radioman.player;

public interface PlayerStateCallback {
    void onStateChanged(PlayerState state);

    void onMetadataChanged(PlayerMetadata metadata);
}
