package com.github.bitlinker.radioman.business.schedulers;

import io.reactivex.Scheduler;

public interface SchedulersProvider {
    Scheduler ui();

    Scheduler io();

    Scheduler computation();
}
