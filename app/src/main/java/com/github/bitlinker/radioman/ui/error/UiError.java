package com.github.bitlinker.radioman.ui.error;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
@EqualsAndHashCode
public class UiError {
    public enum Type {
        IO,
        NETWORK,
        UNKNOWN
    }

    private final Type type;
    private final Throwable base;
}
