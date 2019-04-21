package com.github.bitlinker.radioman.domain;

import java.util.List;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
@EqualsAndHashCode
public class Radio {
    private final String id;
    private final String name;
    private final String imgUrl;
    private final List<RadioStream> streams;
}
