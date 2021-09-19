package com.example.dalelidwaai;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.core.app.NotificationCompat;

public class NotificationMassage extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent arg1) {
        showNotification(context);
    }

    private void showNotification(Context context) {
        Log.i("notification", "visible");

        PendingIntent contentIntent = PendingIntent.getActivity(context, 0,
                new Intent(context, NotificationMassage.class), 0);

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(R.drawable.ic_notifications_black_24dp)
                        .setContentTitle("Medication reminder")
                        .setChannelId("123")
                        .setContentText("Remember to take your medications");
        mBuilder.setContentIntent(contentIntent);
        mBuilder.setDefaults(Notification.DEFAULT_SOUND);
        mBuilder.setAutoCancel(true);
        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(1, mBuilder.build());
    }
}