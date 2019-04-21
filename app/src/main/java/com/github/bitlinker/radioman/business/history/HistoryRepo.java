package com.github.bitlinker.radioman.business.history;

import com.github.bitlinker.radioman.domain.HistoryItem;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

// TODO: db facade
public interface HistoryRepo {
    Completable add(HistoryItem item);

    Flowable<List<HistoryItem>> getAll();

    Completable deleteAll();
}
