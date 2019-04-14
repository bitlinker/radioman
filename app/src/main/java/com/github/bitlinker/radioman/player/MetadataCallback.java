package com.github.bitlinker.radioman.player;

public interface MetadataCallback {
    String KEY_AUDIO_INFO_CHANNELS = "KEY_AUDIO_INFO_CHANNELS";
    String KEY_AUDIO_INFO_SAMPLERATE = "KEY_AUDIO_INFO_SAMPLERATE";
    String KEY_AUDIO_INFO_BITRATE = "KEY_AUDIO_INFO_BITRATE";
    String KEY_AUDIO_BITRATE = "KEY_AUDIO_BITRATE";
    String KEY_AUDIO_DESCRIPTION = "KEY_AUDIO_DESCRIPTION";
    String KEY_AUDIO_GENRE = "KEY_AUDIO_GENRE ";

    //void onMetadataUpdate(String key, String value);
    void onMetadataUpdate(String key, String value);
}
