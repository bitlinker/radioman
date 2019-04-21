package com.github.bitlinker.radioman.business.history;

import com.github.bitlinker.radioman.domain.HistoryItem;

import java.io.IOError;
import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

public class StubHistoryRepo implements HistoryRepo {
    @Override
    public Completable add(HistoryItem item) {
        return Completable.complete();
    }

    @Override
    public Flowable<List<HistoryItem>> getAll() {
        return Flowable.just(Collections.singletonList(
                HistoryItem.builder()
                        .id("id")
                        .title("Test item")
                        .radioTitle("Test radio")
                        .date(new Date())
                        .build()
        ));
    }

    @Override
    public Completable deleteAll() {
        return Completable.error(new IOException("fck!"));
    }
}
