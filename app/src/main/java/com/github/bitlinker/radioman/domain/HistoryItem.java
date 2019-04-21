package com.github.bitlinker.radioman.domain;

import java.util.Date;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
@EqualsAndHashCode
public class HistoryItem {
    private final String id;
    private final String title;
    private final String radioTitle;
    private final Date date;
}
