package com.turkcell.curiosample;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.turkcell.curio.CurioClient;
import com.turkcell.curio.ICustomIdRegisterListener;
import com.turkcell.curio.IUnregisterListener;

public class BlankActivity extends Activity {

	private static final String TAG = "BlankActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i(TAG, "onCreate called. isFinishing: " + isFinishing());
		setContentView(R.layout.activity_blank);

		Button btnSendEvent = (Button)findViewById(R.id.sendEvent);
		btnSendEvent.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				CurioClient.getInstance(BlankActivity.this).sendEvent("Test Event", "testEvent");
			}
		});

		Button btnSendCustomId = (Button)findViewById(R.id.sendCustomId);
		btnSendCustomId.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				CurioClient.getInstance(BlankActivity.this).sendCustomId("sampleCustomId", new ICustomIdRegisterListener() {
					@Override
					public void onCustomIdRegisterResponse(boolean isSuccessful, int statusCode) {
						Log.i(TAG, "Register response is: " + isSuccessful + ", statusCode: " + statusCode);
					}
				});
			}
		});

		Button btnUnregister = (Button)findViewById(R.id.unregister);
		btnUnregister.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				CurioClient.getInstance(BlankActivity.this).unregisterFromNotificationServer(new IUnregisterListener() {
					@Override
					public void onUnregisterResponse(boolean isSuccessful, int statusCode) {
						Log.i(TAG, "Unregister response is: " + isSuccessful + ", statusCode: " + statusCode);
					}
				});
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.blank, menu);
		return true;
	}


	@Override
	protected void onStart() {
		super.onStart();
		CurioClient.getInstance(this).startScreen(this, "Blank Activity", "blank");
		Log.i(TAG, "onStart called. isFinishing: " + isFinishing());
	}

	@Override
	protected void onResume() {
		super.onResume();
		Log.i(TAG, "onResume called. isFinishing: " + isFinishing());
	}

	@Override
	protected void onPause() {
		super.onPause();
		Log.i(TAG, "onPause called. isFinishing: " + isFinishing());
	}

	@Override
	protected void onStop() {
		super.onStop();
		CurioClient.getInstance(this).endScreen(this);
		Log.i(TAG, "onStop called. isFinishing: " + isFinishing());
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.i(TAG, "onDestroy called. isFinishing: " + isFinishing());
	}
}
