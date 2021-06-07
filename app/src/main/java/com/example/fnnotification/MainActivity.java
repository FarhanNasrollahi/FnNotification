package com.example.fnnotification;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.os.Build;
import android.os.Bundle;

import com.example.maznotification.FnChannel;
import com.example.maznotification.FnNotification;
import com.example.maznotification.FnPendingIntentMode;
import com.example.maznotification.FnPriority;
import com.example.maznotification.FnVisibility;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            new FnNotification.Builder(MainActivity.this)
                    .setTitle("salam")
                    .setText("test")
                    .setPriority(FnPriority.MAX)
                    .setAutoCancel(false)
                    .setOnGoing(true)
                    .build(5, new FnChannel.Builder("5", "FARHAN", FnPriority.MAX)
                            .setEnabledBubbles(true)
                            .setEnabledBadge(true)
                            .setEnabledLight(true)
                            .setLightColor(Color.TRANSPARENT)
                            .setLockScreenVisibility(FnVisibility.PUBLIC)
                            .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                            .build());
        }
    }
}