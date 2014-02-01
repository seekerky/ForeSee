/*
 * Copyright (C) 2009 The Android Open Source Project
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
package com.foresee;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutionException;

/**
 * Helper methods to simplify talking with and parsing responses from a
 * lightweight Wiktionary API. Before making any requests, you should call
 * {@link #prepareUserAgent(Context)} to generate a User-Agent string based on
 * your application package name and version.
 */
public class TomTomHelper {
	private static final String TAG = "TomTomHelper";
	private static final String TOMTOM_CAMERA = "http://api.tomtom.com/trafficcams/getfullcam/%s.jpg?key=m9mqubrbycvw7dzuv9v5ctg3";

	protected Drawable GetCameraImage(int cameraId) {
		try {
			try {
				return new GetCameraImageAsync().execute(new Integer[] {cameraId}).get();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		} catch (Exception e) {
			System.out.println("Exc=" + e);
			return null;
		}
	}

	private class GetCameraImageAsync extends AsyncTask<Integer, Integer, Drawable> {
		protected Drawable doInBackground(Integer... cameraId) {
			try {
				try {
					// Read the response as bytes
					ByteArrayOutputStream str = APIHelper.getUrlContent(String
							.format(TOMTOM_CAMERA, cameraId[0]));
					// Convert to input stream
					ByteArrayInputStream bais = new ByteArrayInputStream(
							str.toByteArray());
					// Create a new Drawable from the stream
					Drawable d = Drawable.createFromStream(bais, "src name");
					return d;
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			} catch (Exception e) {
				System.out.println("Exc=" + e);
				return null;
			}
		}
	}

}
