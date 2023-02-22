package com.example.ats.Utils;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.ContextWrapper;
import android.content.Context;
import android.net.Uri;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.ats.R;

public class NotificationHelper extends ContextWrapper {
    private static final String ATS_APP_ID ="com.example.ats";
    private static final String ATS_APP_NAME = "Ambulance Tracking System";

    private NotificationManager manager;
    public NotificationHelper(Context base){
        super(base);
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O)
            createChannel();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createChannel() {
        NotificationChannel atschannel = new NotificationChannel(ATS_APP_ID,ATS_APP_NAME,NotificationManager.IMPORTANCE_DEFAULT);
        atschannel.enableLights(false);
        atschannel.enableVibration(true);
        atschannel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);

        getManager().createNotificationChannel(atschannel);
    }

    public NotificationManager getManager() {
        if(manager==null)
            manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        return manager;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Notification.Builder getRealTimeNotification(String title, String content, Uri defaultSound) {
        return new Notification.Builder(getApplicationContext(),ATS_APP_ID)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentTitle(title)
                .setContentText(content)
                .setSound(defaultSound)
                .setAutoCancel(false);
    }
}
