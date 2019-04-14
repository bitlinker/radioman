package com.github.bitlinker.radioman.player;

import java.util.Map;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public class PlayerMetadata {
    public static final String KEY_TITLE = "KEY_TITLE";

    private final Map<String, String> metadata;
}

