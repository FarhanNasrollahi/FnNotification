package com.example.maznotification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.graphics.Color;
import android.media.AudioAttributes;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;

import androidx.annotation.RequiresApi;

@RequiresApi(api = Build.VERSION_CODES.O)
public class FnChannel {

    private final NotificationChannel notificationChannel;
    String ChannelId = "";

    public FnChannel(String ChannelId, String ChannelName, int fnPriority) {
        this.ChannelId = ChannelId;
        int importance = 0;
        switch (fnPriority) {
            case FnPriority.MAX:
                importance = NotificationManager.IMPORTANCE_MAX;
                break;
            case FnPriority.HIGH:
                importance = NotificationManager.IMPORTANCE_HIGH;
                break;
            case FnPriority.DEFAULT:
                importance = NotificationManager.IMPORTANCE_DEFAULT;
                break;
            case FnPriority.LOW:
                importance = NotificationManager.IMPORTANCE_LOW;
                break;
            case FnPriority.MIN:
                importance = NotificationManager.IMPORTANCE_MIN;
                break;
        }
        notificationChannel = new NotificationChannel(ChannelId, ChannelName, importance);
        notificationChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
    }

    public void setLockScreenVisibility(int fnVisibility) {
        switch (fnVisibility) {
            case FnVisibility.PUBLIC:
                notificationChannel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
                break;
            case FnVisibility.PRIVATE:
                notificationChannel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
                break;
            case FnVisibility.SECRET:
                notificationChannel.setLockscreenVisibility(Notification.VISIBILITY_SECRET);
                break;
        }
    }

    public void setEnabledLight(boolean b) {
        notificationChannel.enableLights(b);
    }

    public void setLightColor(int color) {
        notificationChannel.setLightColor(color);
    }

    public void setEnabledBubbles(boolean b) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            notificationChannel.setAllowBubbles(b);
        }
    }

    public void setEnabledBadge(boolean b) {
        notificationChannel.setShowBadge(b);
    }

    public void setEnabledVibrate(boolean b) {
        notificationChannel.enableVibration(b);
    }

    public void setSound(Uri uri) {
        AudioAttributes attributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                .build();
        notificationChannel.setSound(uri, attributes);
    }

    public String getChannelId() {
        return ChannelId;
    }

    public NotificationChannel getNotificationChannel() {
        return notificationChannel;
    }


    public static class Builder {

        String ChannelName = "";
        String ChannelId = "";

        int fnPriority;
        int fnVisibility = FnVisibility.PUBLIC;

        int lightColor = Color.TRANSPARENT;

        boolean enabledLight = false;
        boolean enabledBubbles = false;
        boolean enabledBadge = false;
        boolean enabledVibrate = false;

        Uri sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        public Builder(String ChannelId, String ChannelName, int fnPriority) {
            this.ChannelId = ChannelId;
            this.ChannelName = ChannelName;
            this.fnPriority = fnPriority;
        }


        public Builder setLockScreenVisibility(int fnVisibility) {
            this.fnVisibility = fnVisibility;
            return this;
        }

        public Builder setEnabledLight(boolean b) {
            enabledLight = b;
            return this;
        }

        public Builder setLightColor(int color) {
            lightColor = color;
            return this;
        }


        public Builder setEnabledBubbles(boolean b) {
            enabledBubbles = b;
            return this;
        }

        public Builder setEnabledBadge(boolean b) {
            enabledBadge = b;
            return this;
        }

        public Builder setEnabledVibrate(boolean b) {
            enabledVibrate = b;
            return this;
        }

        public Builder setSound(Uri uri) {
            this.sound = uri;
            return this;
        }

        public FnChannel build() {
            FnChannel f = new FnChannel(ChannelId, ChannelName, fnPriority);
            f.setSound(sound);
            f.setLightColor(lightColor);
            f.setEnabledLight(enabledLight);
            f.setEnabledVibrate(enabledVibrate);
            f.setEnabledBadge(enabledBadge);
            f.setEnabledBubbles(enabledBubbles);
            f.setLockScreenVisibility(fnVisibility);
            return f;
        }

    }

}
