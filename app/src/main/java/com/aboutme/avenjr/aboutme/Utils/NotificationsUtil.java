package com.aboutme.avenjr.aboutme.Utils;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

import com.aboutme.avenjr.aboutme.R;

public class NotificationsUtil {

    public static void createNotification(Context context, String title, String description,int drawable) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("myChannelId", "My Channel", importance);
            channel.setDescription("Reminders");

            // Register the channel with the notifications manager
            NotificationManager mNotificationManager =
                    (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            mNotificationManager.createNotificationChannel(channel);

            NotificationCompat.Builder mBuilder =
            // Builder class for devices targeting API 26+ requires a channel ID
            new NotificationCompat.Builder(context, "myChannelId")
                            .setSmallIcon(drawable)
                            .setContentTitle(title)
                            .setContentText(description);
        } else {
            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context)
                    .setSmallIcon(drawable)
                    .setContentTitle(title)
                    .setContentText(description)
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT);
            NotificationManager mNotificationManager =
                    (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            // mId allows you to update the notification later on.
            mNotificationManager.notify(12, mBuilder.build());
        }
    }
}
