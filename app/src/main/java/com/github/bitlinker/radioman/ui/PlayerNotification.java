package com.github.bitlinker.radioman.ui;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.widget.RemoteViews;

import com.github.bitlinker.radioman.R;

public class PlayerNotification {
    private static final int PLAYER_NOTIFICATION_ID = 999;
    private static final String PLAYER_CHANNEL_ID = "PLAYER_CHANNEL_ID";

    private final Notification notificaction;

    public PlayerNotification(Context context) {
        //RemoteViews notificationLayout = new RemoteViews(context.getPackageName(), R.layout.notification_player);

        Intent notificationIntent = new Intent(context, PlayerActivity.class);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, notificationIntent, 0);

        //NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);

        notificaction = new NotificationCompat.Builder(context, PLAYER_CHANNEL_ID)
                .setAutoCancel(false)
                .setChannelId(PLAYER_CHANNEL_ID)
                .setContentTitle("Music Player")
                //.setTicker("Music Player")

                .setContentText("My Music")
                .setSmallIcon(R.mipmap.ic_launcher)
//                .setStyle(new NotificationCompat.DecoratedCustomViewStyle())
//                .setCustomContentView(notificationLayout)
                .setContentIntent(pendingIntent)
                //.setOngoing(true)
                .addAction(android.R.drawable.ic_media_pause,"Pause", pendingIntent)
                .build();
    }

    public Notification getNotificaction() {
        return notificaction;
    }

    public int getId() {
        return PLAYER_NOTIFICATION_ID;
    }
}
