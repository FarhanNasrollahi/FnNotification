<h1>FnNotification</h1>

Add this in your app's build.gradle file(Using Android Studio and Gradle):

    allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
  

    dependencies {
	         implementation 'com.github.FarhanNasrollahi:FnNotification:0.1.0'
	}

For use Code :

```
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

```



|  Set  | |
| ------------ | - |
| setTitle | String |
| setText | String |
| setSound | Uri |
| setAutoCancel | Boolean |
| setLightColor | int |
| setSmallIcon | int |
| setLargeIcon | Bitmap |
| setOnGoing | Boolean |
| setCategory | Categorys |
| setPriority | FnPriority |
| addAction |  |
| setDeleteIntent |  |
| setContentIntent |  |
| setFullScreenIntent |  |
| show |  |

 

