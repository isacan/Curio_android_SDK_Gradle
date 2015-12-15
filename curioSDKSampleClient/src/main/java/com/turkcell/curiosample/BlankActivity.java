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
import com.turkcell.curio.IUserTagsResponseListener;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

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

		Button btnEndEvent = (Button)findViewById(R.id.endEvent);
		btnEndEvent.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				CurioClient.getInstance(BlankActivity.this).endEvent("Test Event", "testEvent", 35000);
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


		Button btnSendTags = (Button)findViewById(R.id.sendTags);

		final Map<String, String> tagMap = new HashMap<String, String>();
		tagMap.put("age", "32");
		tagMap.put("gender", "male");
		tagMap.put("color", "blue");

		btnSendTags.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				CurioClient.getInstance(BlankActivity.this).sendUserTags(tagMap, new IUserTagsResponseListener() {
					@Override
					public void onSendUserTagsResponse(boolean isSuccessful, int statusCode) {
						Log.i(TAG, "Send user tag response is: " + isSuccessful + ", statusCode: " + statusCode);
					}

					@Override
					public void onGetUserTagsResponse(Map<String, String> tagMap, int statusCode) {

					}
				});
			}
		});

		Button btnGetTags = (Button)findViewById(R.id.getTags);

		btnGetTags.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				CurioClient.getInstance(BlankActivity.this).getUserTags(new IUserTagsResponseListener() {
					@Override
					public void onSendUserTagsResponse(boolean isSuccessful, int statusCode) {

					}

					@Override
					public void onGetUserTagsResponse(Map<String, String> tagMap, int statusCode) {
						Log.i(TAG, "Get user tags response is: " + (tagMap == null ? null : new JSONObject(tagMap)) + ", statusCode: " + statusCode);
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
