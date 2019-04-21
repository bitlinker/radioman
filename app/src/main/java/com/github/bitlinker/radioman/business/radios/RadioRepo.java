package com.github.bitlinker.radioman.business.radios;

import com.github.bitlinker.radioman.domain.Radio;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

public interface RadioRepo {
    Flowable<List<Radio>> getAll();

    Single<Radio> getById(String id);

    Completable add(Radio radio);
}
