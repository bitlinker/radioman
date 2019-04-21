package com.github.bitlinker.radioman.business;

import com.github.bitlinker.radioman.business.history.HistoryRepo;
import com.github.bitlinker.radioman.domain.HistoryItem;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

public class HistoryInteractor {
    private final HistoryRepo historyRepo;

    public HistoryInteractor(HistoryRepo historyRepo) {
        this.historyRepo = historyRepo;
    }

    public Completable add(HistoryItem item) {
        return historyRepo.add(item);
        // TODO: maintain db size here...
    }

    public Flowable<List<HistoryItem>> getAll() {
        return historyRepo.getAll();
    }

    public Completable deleteAll() {
        return historyRepo.deleteAll();
    }
}
