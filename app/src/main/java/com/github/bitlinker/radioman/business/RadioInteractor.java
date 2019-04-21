package com.github.bitlinker.radioman.business;

import com.github.bitlinker.radioman.business.radios.RadioRepo;
import com.github.bitlinker.radioman.domain.Radio;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

public class RadioInteractor {
    private final RadioRepo radioRepo;

    public RadioInteractor(RadioRepo radioRepo) {
        this.radioRepo = radioRepo;
    }

    public Flowable<List<Radio>> getRadios() {
        return radioRepo.getAll();
    }

    public Single<Radio> getRadioById(String id) {
        return radioRepo.getById(id);
    }

    public Completable add(Radio radio) {
        return radioRepo.add(radio);
    }
}
