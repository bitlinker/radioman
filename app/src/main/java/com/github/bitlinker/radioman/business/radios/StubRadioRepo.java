package com.github.bitlinker.radioman.business.radios;

import com.github.bitlinker.radioman.domain.Radio;
import com.github.bitlinker.radioman.domain.RadioStream;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

public class StubRadioRepo implements RadioRepo {
    // TODO: read from here:
    // https://raw.githubusercontent.com/bitlinker/radioman/master/static/radio.json

    // TODO: multiple radios/streams support
    // TODO: bg:
    // http://player.radioultra.ru/promo/ultrabackgr.jpg


    @Override
    public Flowable<List<Radio>> getAll() {
        return Flowable.just(Collections.singletonList(
                Radio.builder()
                        .id("1")
                        .name("Ultra")
                        .imgUrl("http://player.radioultra.ru/images/logos/ultra.jpg")
                        .streams(Arrays.asList(
                                RadioStream.builder()
                                        .bitrate(64)
                                        .url("https://nashe1.hostingradio.ru:18000/ultra-64.mp3")
                                        .build(),
                                RadioStream.builder()
                                        .bitrate(128)
                                        .url("https://nashe1.hostingradio.ru:18000/ultra-128.mp3")
                                        .build(),
                                RadioStream.builder()
                                        .bitrate(256)
                                        .url("https://nashe1.hostingradio.ru:80/ultra-256")
                                        .build()
                        ))
                        .build()
                )
        );
    }

    @Override
    public Single<Radio> getById(String id) {
        return getAll().firstOrError().map(radios -> radios.get(0)); // TODO
    }

    @Override
    public Completable add(Radio radio) {
        return Completable.complete(); // TODO
    }
}
