# FnNotification for easy Notification in Android

for Example Code for Make Notification

new FnNotification.Builder(MainActivity.this)
                    .setTitle("Hello")
                    .setText("Example Notification")
                    .setPriority(FnPriority.MAX)
                    .setAutoCancel(false)
                    .setOnGoing(true)
                    .build(5, new FnChannel.Builder("5", "CHANNEL_5", FnPriority.MAX)
                            .setEnabledBubbles(true)
                            .setEnabledBadge(true)
                            .setEnabledLight(true)
                            .setLightColor(Color.TRANSPARENT)
                            .setLockScreenVisibility(FnVisibility.PUBLIC)
                            .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                            .build());
