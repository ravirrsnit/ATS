package com.example.ats.Service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.IBinder;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import com.example.ats.R;
import com.example.ats.Utils.Common;
import com.example.ats.Utils.NotificationHelper;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;
import java.util.Random;

public class MyFCMService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        if(remoteMessage.getData() != null)
        {
            if(Build.VERSION.SDK_INT >=Build.VERSION_CODES.O)
                sendNotificationWithChannel (remoteMessage);
            else
                sendNotification(remoteMessage);
        }
    }

    private void sendNotification(RemoteMessage remoteMessage) {
        Map<String,String> data =remoteMessage.getData();
        String title = "Friend Requests";
        String content = "New friends request from " +data.get(Common.FROM_NAME);

        Uri defaultSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder builder= new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentTitle(title)
                .setContentText(content)
                .setSound(defaultSound)
                .setAutoCancel(false);
        NotificationManager manager =(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(new Random().nextInt(),builder.build());
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void sendNotificationWithChannel(RemoteMessage remoteMessage) {
        Map<String,String> data =remoteMessage.getData();
        String title = "Friend Requests";
        String content = "New friends request from " +data.get(Common.FROM_NAME);

        NotificationHelper helper;
        Notification.Builder builder;

        Uri defaultSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        helper = new NotificationHelper(this);
        builder = helper.getRealTimeNotification(title,content,defaultSound);

        helper.getManager().notify(new Random().nextInt() ,builder.build());
    }

    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user != null)
        {

            final DatabaseReference tokens = FirebaseDatabase.getInstance()
                    .getReference(Common.TOKENS);
            tokens.child(user.getUid()).setValue(s);
        }
    }
}
