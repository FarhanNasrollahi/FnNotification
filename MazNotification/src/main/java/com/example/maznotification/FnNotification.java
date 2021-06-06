package com.example.maznotification;

import android.annotation.SuppressLint;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.AudioManager;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;


/**
 * Class Name : FnNotification
 * Developer : Farhan Nasrollahi
 * Create Date : 2021 / june / 03
 * LastUpdate Date : 2021 / june / 03
 * Easy Notification Class for All Api
 **/


public class FnNotification {

    // notification Channel Id Default
    private final static String default_notification_channel_id = "default";
    // notification sound uri
    Uri sound;

    Context context;
    private final NotificationCompat.Builder mBuilder;
    private final NotificationManager mNotificationManager;

    // Class Constructor
    public FnNotification(Context context) {
        this.context = context;
        mBuilder = new NotificationCompat.Builder(context, default_notification_channel_id);
        mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        initDefault();
    }

    @SuppressLint("WrongConstant")
    private void initDefault() {
        mBuilder.setSmallIcon(R.drawable.ic_launcher_background);
        mBuilder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        mBuilder.setVisibility(NotificationCompat.VISIBILITY_PUBLIC);
        mBuilder.setBadgeIconType(R.drawable.ic_launcher_background);
        mBuilder.setSilent(false);
    }

    public void setTitle(String title) {
        mBuilder.setContentTitle(title);
    }

    public void setText(String text) {
        mBuilder.setContentText(text);
    }

    public void setSound(Uri sound) {
        this.sound = sound;
    }

    public void setAutoCancel(boolean b) {
        mBuilder.setAutoCancel(b);
    }

    public void setLightColor(int color) {
        mBuilder.setLights(color, 100, 100);
    }

    public void setSmallIcon(int drawableId) {
        mBuilder.setSmallIcon(drawableId);
        mBuilder.setBadgeIconType(drawableId);
    }

    public void setLargeIcon(Bitmap bitmap) {
        mBuilder.setLargeIcon(bitmap);
    }

    public void setOnGoing(boolean b) {
        mBuilder.setOngoing(b);
    }

    public void setCategory(String category) {
        mBuilder.setCategory(category);
    }

    public void setPriority(int fnPriority) {
        switch (fnPriority) {
            case FnPriority.MAX:
                mBuilder.setPriority(NotificationCompat.PRIORITY_MAX);
                break;
            case FnPriority.HIGH:
                mBuilder.setPriority(NotificationCompat.PRIORITY_HIGH);
                break;
            case FnPriority.DEFAULT:
                mBuilder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
                break;
            case FnPriority.LOW:
                mBuilder.setPriority(NotificationCompat.PRIORITY_LOW);
                break;
            case FnPriority.MIN:
                mBuilder.setPriority(NotificationCompat.PRIORITY_MIN);
                break;
        }
    }

    public void addAction(int pendingIntentMode, Intent intent, int requestCode, int icon, String name) {
        PendingIntent pendingIntent = null;
        switch (pendingIntentMode) {
            case FnPendingIntentMode.SERVICE:
                pendingIntent = PendingIntent.getService(context, requestCode, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                break;
            case FnPendingIntentMode.ACTIVITY:
                pendingIntent = PendingIntent.getActivity(context, requestCode, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                break;
            case FnPendingIntentMode.BROADCAST:
                pendingIntent = PendingIntent.getBroadcast(context, requestCode, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                break;
        }
        NotificationCompat.Action action = new NotificationCompat.Action.Builder(
                icon,
                name,
                pendingIntent).build();

        mBuilder.addAction(action);
    }

    public void setDeleteIntent(int pendingIntentMode, Intent intent, int requestCode) {
        PendingIntent pendingIntent = null;

        switch (pendingIntentMode) {
            case FnPendingIntentMode.SERVICE:
                pendingIntent = PendingIntent.getService(context, requestCode, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                break;
            case FnPendingIntentMode.ACTIVITY:
                pendingIntent = PendingIntent.getActivity(context, requestCode, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                break;
            case FnPendingIntentMode.BROADCAST:
                pendingIntent = PendingIntent.getBroadcast(context, requestCode, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                break;
        }

        mBuilder.setDeleteIntent(pendingIntent);
    }

    public void setContentIntent(int pendingIntentMode, Intent intent, int requestCode) {
        PendingIntent pendingIntent = null;

        switch (pendingIntentMode) {
            case FnPendingIntentMode.SERVICE:
                pendingIntent = PendingIntent.getService(context, requestCode, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                break;
            case FnPendingIntentMode.ACTIVITY:
                pendingIntent = PendingIntent.getActivity(context, requestCode, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                break;
            case FnPendingIntentMode.BROADCAST:
                pendingIntent = PendingIntent.getBroadcast(context, requestCode, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                break;
        }
        mBuilder.setContentIntent(pendingIntent);
    }

    public void setFullScreenIntent(Intent intent, int requestCode) {
        PendingIntent pendingIntent = PendingIntent.getActivity(context, requestCode, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setFullScreenIntent(pendingIntent, true);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void show(int notificationId, FnChannel fnChannel) {
        mBuilder.setSound(sound, AudioManager.STREAM_NOTIFICATION);
        mBuilder.setChannelId(fnChannel.getChannelId());
        mNotificationManager.createNotificationChannel(fnChannel.getNotificationChannel());
        mNotificationManager.notify(notificationId, mBuilder.build());
    }

    public void show(int notificationId) {
        mBuilder.setSound(sound);
        mNotificationManager.notify(notificationId, mBuilder.build());
    }

    public static class Builder {

        Uri sound;

        Context context;
        NotificationCompat.Builder mBuilder;
        NotificationManager mNotificationManager;

        public Builder(Context context) {
            this.context = context;
            mBuilder = new NotificationCompat.Builder(context, default_notification_channel_id);
            mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            initDefault();
        }

        @SuppressLint("WrongConstant")
        private void initDefault() {
            mBuilder.setSmallIcon(R.drawable.ic_launcher_background);
            mBuilder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
            mBuilder.setVisibility(NotificationCompat.VISIBILITY_PUBLIC);
            mBuilder.setBadgeIconType(R.drawable.ic_launcher_background);
            mBuilder.setSilent(false);
        }

        public Builder setTitle(String title) {
            mBuilder.setContentTitle(title);
            return this;
        }

        public Builder setText(String text) {
            mBuilder.setContentText(text);
            return this;
        }

        public Builder setSound(Uri sound) {
            this.sound = sound;
            return this;
        }

        public Builder setAutoCancel(boolean b) {
            mBuilder.setAutoCancel(b);
            return this;
        }

        public Builder setLightColor(int color) {
            mBuilder.setLights(color, 100, 100);
            return this;
        }

        public Builder setSmallIcon(int drawableId) {
            mBuilder.setSmallIcon(drawableId);
            mBuilder.setBadgeIconType(drawableId);
            return this;
        }

        public Builder setLargeIcon(Bitmap bitmap) {
            mBuilder.setLargeIcon(bitmap);
            return this;
        }

        public Builder setOnGoing(boolean b) {
            mBuilder.setOngoing(b);
            return this;
        }

        public Builder setCategory(String category) {
            mBuilder.setCategory(category);
            return this;
        }

        public Builder setPriority(int fnPriority) {
            switch (fnPriority) {
                case FnPriority.MAX:
                    mBuilder.setPriority(NotificationCompat.PRIORITY_MAX);
                    break;
                case FnPriority.HIGH:
                    mBuilder.setPriority(NotificationCompat.PRIORITY_HIGH);
                    break;
                case FnPriority.DEFAULT:
                    mBuilder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
                    break;
                case FnPriority.LOW:
                    mBuilder.setPriority(NotificationCompat.PRIORITY_LOW);
                    break;
                case FnPriority.MIN:
                    mBuilder.setPriority(NotificationCompat.PRIORITY_MIN);
                    break;
            }
            return this;
        }

        public Builder addAction(int pendingIntentMode, Intent intent, int requestCode, int icon, String name) {
            PendingIntent pendingIntent = null;

            switch (pendingIntentMode) {
                case FnPendingIntentMode.SERVICE:
                    pendingIntent = PendingIntent.getService(context, requestCode, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                    break;
                case FnPendingIntentMode.ACTIVITY:
                    pendingIntent = PendingIntent.getActivity(context, requestCode, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                    break;
                case FnPendingIntentMode.BROADCAST:
                    pendingIntent = PendingIntent.getBroadcast(context, requestCode, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                    break;
            }

            NotificationCompat.Action action = new NotificationCompat.Action.Builder(
                    icon,
                    name,
                    pendingIntent).build();

            mBuilder.addAction(action);
            return this;
        }

        public Builder setDeleteIntent(int pendingIntentMode, Intent intent, int requestCode) {
            PendingIntent pendingIntent = null;

            switch (pendingIntentMode) {
                case FnPendingIntentMode.SERVICE:
                    pendingIntent = PendingIntent.getService(context, requestCode, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                    break;
                case FnPendingIntentMode.ACTIVITY:
                    pendingIntent = PendingIntent.getActivity(context, requestCode, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                    break;
                case FnPendingIntentMode.BROADCAST:
                    pendingIntent = PendingIntent.getBroadcast(context, requestCode, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                    break;
            }

            mBuilder.setDeleteIntent(pendingIntent);
            return this;
        }

        public Builder setContentIntent(int pendingIntentMode, Intent intent, int requestCode) {
            PendingIntent pendingIntent = null;

            switch (pendingIntentMode) {
                case FnPendingIntentMode.SERVICE:
                    pendingIntent = PendingIntent.getService(context, requestCode, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                    break;
                case FnPendingIntentMode.ACTIVITY:
                    pendingIntent = PendingIntent.getActivity(context, requestCode, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                    break;
                case FnPendingIntentMode.BROADCAST:
                    pendingIntent = PendingIntent.getBroadcast(context, requestCode, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                    break;
            }
            mBuilder.setContentIntent(pendingIntent);
            return this;
        }

        public Builder setFullScreenIntent(Intent intent, int requestCode) {
            PendingIntent pendingIntent = PendingIntent.getActivity(context, requestCode, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            mBuilder.setFullScreenIntent(pendingIntent, true);
            return this;
        }

        @RequiresApi(api = Build.VERSION_CODES.O)
        public void build(int notificationId, FnChannel fnChannel) {
            mBuilder.setSound(sound, AudioManager.STREAM_NOTIFICATION);
            mBuilder.setChannelId(fnChannel.getChannelId());
            mNotificationManager.createNotificationChannel(fnChannel.getNotificationChannel());
            mNotificationManager.notify(notificationId, mBuilder.build());
        }

        public void build(int notificationId , String ChannelID) {
            mBuilder.setSound(sound);
            mBuilder.setChannelId(ChannelID);
            mNotificationManager.notify(notificationId, mBuilder.build());
        }

    }

}
