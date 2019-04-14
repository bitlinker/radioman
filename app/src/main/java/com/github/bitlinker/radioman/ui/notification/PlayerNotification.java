package com.github.bitlinker.radioman.ui.notification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.widget.RemoteViews;

import com.github.bitlinker.radioman.R;
import com.github.bitlinker.radioman.ui.player.PlayerActivity;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.media.session.MediaButtonReceiver;

public class PlayerNotification {
    private static final int PLAYER_NOTIFICATION_ID = 999;

    private final Notification notificaction;

    public PlayerNotification(Context context, MediaSessionCompat mediaSession) {
        RemoteViews notificationLayout = new RemoteViews(context.getPackageName(), R.layout.notification_player);

        Intent notificationIntent = new Intent(context, PlayerActivity.class);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, notificationIntent, 0);

        String channelId;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            channelId = createNotificationChannel(context,
                    "RADIOMAN_ID", // TODO: package
                    "Radioman",
                    "Radioman player notification channel"); // TODO: resources
        } else {
            // If earlier version channel ID is not used
            // https://developer.android.com/reference/android/support/v4/app/NotificationCompat.Builder.html#NotificationCompat.Builder(android.content.Context)
            channelId = "";
        }

        /*NotificationCompat.Action stopAction = new NotificationCompat.Action(
                R.drawable.ic_stop_black_24dp,
                "stop",
                MediaButtonReceiver.buildMediaButtonPendingIntent(context, PlaybackStateCompat.ACTION_PAUSE)
        );

        NotificationCompat.Action playAction = new NotificationCompat.Action(
                R.drawable.ic_play_arrow_black_24dp,
                "play",
                MediaButtonReceiver.buildMediaButtonPendingIntent(context, PlaybackStateCompat.ACTION_PLAY)
        );*/

        androidx.media.app.NotificationCompat.MediaStyle style = new androidx.media.app.NotificationCompat.MediaStyle();
        style.setMediaSession(mediaSession.getSessionToken());
        //style.setShowActionsInCompactView(0, 1);

        notificaction = new NotificationCompat.Builder(context, channelId)
                .setAutoCancel(false)
                .setContentTitle("Artist - track")
                .setContentText("Radio ULTRA")
                .setOngoing(true) // TODO
                .setCategory(Notification.CATEGORY_SERVICE)
                .setOnlyAlertOnce(true)
                .setStyle(style)
                .setLargeIcon(((BitmapDrawable) context.getDrawable(R.drawable.player_bg)).getBitmap())
                .setSubText("status here")
                .setSmallIcon(R.mipmap.ic_launcher) // TODO: transparent?
                //.addAction(stopAction)
                //.addAction(playAction)
                .setDeleteIntent(MediaButtonReceiver.buildMediaButtonPendingIntent(context, PlaybackStateCompat.ACTION_STOP))
                .build();
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private static String createNotificationChannel(Context context,
                                                    String id,
                                                    String name,
                                                    String description) {
        NotificationChannel channel = new NotificationChannel(id, name, NotificationManager.IMPORTANCE_NONE);
        channel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
        channel.setDescription(description);
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (manager != null) {
            manager.createNotificationChannel(channel);
        } // TODO: handle error
        return id;
    }


    public Notification getNotificaction() {
        return notificaction;
    }

    public int getId() {
        return PLAYER_NOTIFICATION_ID;
    }

    public void setContentTitle(String title) {

    }

    public void setContentText(String description) {

    }

    public void setStatusText(String status) {

    }

    public void setImage(Bitmap bitmap) {

    }
}
