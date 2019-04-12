package com.github.bitlinker.radioman.business;

import com.github.bitlinker.radioman.domain.Radio;
import com.github.bitlinker.radioman.domain.RadioStream;

import java.util.Arrays;

public class RadioRepo {

    // TODO: multiple radios/streams support
    // TODO: bg:
    // http://player.radioultra.ru/promo/ultrabackgr.jpg
    public Radio getRadio() {
        return Radio.builder()
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
                .build();
    }
}
