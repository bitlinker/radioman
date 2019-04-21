package com.github.bitlinker.radioman.business;


import com.github.bitlinker.radioman.business.player.PlayerState;

import io.reactivex.Observable;

public class PlayerInteractor {
    // TODO: interact with player using this...
    // TODO: service connector here

    public Observable<PlayerState> getState() {
        return Observable.just(PlayerState.PLAYING); // TODO
    }

    public void play() {

    }

    public void pause() {

    }
}
