/*
 * Copyright (C) 2014 Turkcell
 * 
 * Created by Can Ciloglu on 10 Haz 2014
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.turkcell.curio.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import org.json.JSONArray;

import java.util.List;
import java.util.UUID;


/**
 * Utility class which holds static methods.
 * 
 * @author Can Ciloglu
 *
 */
public class CurioUtil {
	
	/**
	 * Generates a version 1 Universally Unique Identifier.
	 * 
	 * @return id String.
	 */
	public static String generateTimeBasedUUID(long timeStamp){
		return UUIDGenerator.generateTimeBasedUUID(timeStamp).toString();
	}
	
	/**
	 * Generates a version 4 Universally Unique Identifier.
	 * 
	 * @return
	 */
	public static String generateRandomUUID(){
		return UUID.randomUUID().toString();
	}
	
	/**
	 * Gets request type according to the request url.
	 * 
	 * @param url
	 * @return
	 */
	public static int getRequestType(String url){
		int type = -1;
		if(url.endsWith(Constants.SERVER_URL_SUFFIX_SESSION_START)){
			type = 0;
		}else if(url.endsWith(Constants.SERVER_URL_SUFFIX_SESSION_END)){
			type = 1;
		}else if(url.endsWith(Constants.SERVER_URL_SUFFIX_SCREEN_START)){
			type = 2;
		}else if(url.endsWith(Constants.SERVER_URL_SUFFIX_SCREEN_END)){
			type = 3;
		}else if(url.endsWith(Constants.SERVER_URL_SUFFIX_SEND_EVENT)){
			type = 4;
		}else if(url.endsWith(Constants.SERVER_URL_SUFFIX_EVENT_END)){
			type = 7;
		}
		
		return type;
	}

	public static String getInstalledApps(Context context){
		List<ApplicationInfo> appList = context.getPackageManager().getInstalledApplications(PackageManager.GET_META_DATA);

		JSONArray appArray = new JSONArray();
		for(ApplicationInfo appInfo : appList){
			JSONArray appInfoJson = new JSONArray();
			appInfoJson.put(appInfo.packageName);
			appInfoJson.put(appInfo.loadLabel(context.getPackageManager()));
			appArray.put(appInfoJson);
		}

		return appArray.toString();
	}

	public static void setAsFirstTimeUse(Context context){
		SharedPreferences sharedPreferences = context.getSharedPreferences(Constants.SHARED_PREF_NAME_CURIO, Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sharedPreferences.edit();
		editor.putBoolean(Constants.SHARED_PREF_KEY_FIRST_TIME_USE, false);
		editor.apply();
	}

	public static boolean isFirstTimeUse(Context context) {
		SharedPreferences sharedPreferences = context.getSharedPreferences(Constants.SHARED_PREF_NAME_CURIO, Context.MODE_PRIVATE);
		return sharedPreferences.getBoolean(Constants.SHARED_PREF_KEY_FIRST_TIME_USE, true);
	}
}
