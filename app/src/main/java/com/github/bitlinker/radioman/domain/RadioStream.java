package com.github.bitlinker.radioman.domain;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RadioStream {
    private final int bitrate;
    private final String url;

}
