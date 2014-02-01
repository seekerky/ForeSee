package com.foresee;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.graphics.*;
import java.io.*;
import android.util.*;
import java.net.*;
import java.util.concurrent.ExecutionException;

import android.graphics.drawable.*;
import android.widget.*;
import android.app.*;
import android.view.View.OnClickListener;
import android.view.View;

public class MainActivity extends Activity {
	ProgressDialog pd;
	Button rb;
	ImageView imgView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		//Find the controls
		rb = (Button) findViewById(R.id.refreshButton);
		imgView = (ImageView) findViewById(R.id.imageView1);

		rb.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				RenderCamera(9822);
			}
		});

		// 1st Render
		RenderCamera(9822);
	}

	//Render the camera
	private void RenderCamera(int cameraId) {
		try {
			TomTomHelper th = new TomTomHelper();
			Drawable drawable = th.GetCameraImage(cameraId);
			imgView.setImageDrawable(drawable);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
