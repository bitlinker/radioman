package com.github.bitlinker.radioman.service;

import android.content.Context;
import android.os.PowerManager;

public class WakeLockWrapper {
    private final PowerManager.WakeLock wakeLock;

    public WakeLockWrapper(Context context, String tag) {
        PowerManager powerManager = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        wakeLock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, tag);
    }

    public void acquire() {
        wakeLock.acquire();
    }

    public void acquire(long timeout) {
        wakeLock.acquire(timeout);
    }

    public void release() {
        wakeLock.release();
    }
}
