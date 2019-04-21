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
//        if (PlayerService.INSTANCE.isPlaying()) {
//            PlayerService.INSTANCE.stop();
//        } else {
//            PlayerService.INSTANCE.play(repo.getAll().get(0).getStreams().get(1));
//        }
    }

    public void pause() {

    }
}
