package com.github.bitlinker.radioman.domain;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Radio {
    private final String name;
    private final String imgUrl;
    private final List<RadioStream> streams;
}
