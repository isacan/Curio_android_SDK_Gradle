
package com.turkcell.curiosample;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;

/**
 * !!!!!
 *
 * Important: This is just a sample reference implementation.
 *
 * Please DO NOT copy and paste this class and code to your real life projects,
 * since it may cause issues on your project.
 *
 * Please write your own code and implementation.
 *
 * !!!!!
 */
public class PushNotificationBroadcastReceiver extends WakefulBroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		ComponentName comp = new ComponentName(context.getPackageName(), PushNotificationIntentService.class.getName());
		startWakefulService(context, (intent.setComponent(comp)));
		setResultCode(Activity.RESULT_OK);
	}
}
