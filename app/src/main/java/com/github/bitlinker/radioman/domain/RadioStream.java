package com.github.bitlinker.radioman.domain;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
@EqualsAndHashCode
public class RadioStream {
    private final int bitrate;
    private final String url;
}
