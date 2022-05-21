package com.example.dailyselfie;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class NotificationReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Notification notification;
        NotificationManagerCompat notificationManagerCompat;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel("myCH", "My Channel", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager notificationManager = (NotificationManager) context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(notificationChannel);
            NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "myCH")
                    .setContentTitle("Daily Selfie")
                    .setContentText("Time for another selfie")
                    .setSmallIcon(R.drawable.icon_camera)
                    .setAutoCancel(true);
            notification = builder.build();
            notificationManagerCompat = NotificationManagerCompat.from(context);
            notificationManagerCompat.notify(1, notification);
        }
    }
}