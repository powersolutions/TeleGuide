package com.snapdragons.teleguide;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.MediaController;
import android.widget.VideoView;
import android.content.*;

public class Splash extends Activity implements OnClickListener {

	VideoView videoView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);

		init();

		new CountDownTimer(17000, 1000) { // adjust the milli seconds here

			@Override
			public void onTick(long millisUntilFinished) {
				// TODO Auto-generated method stub

			}

			public void onFinish() {
				startActivity(new Intent(Splash.this,MainActivity.class));
				
				
			}

		}.start();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}

	private void init() {
		String uriPath = "android.resource://" + getPackageName() + "/"
				+ R.raw.mm;

		videoView = (VideoView) findViewById(R.id.videoView1);
		// MediaController mediaController = new MediaController(this);
		// mediaController.setAnchorView(videoView);
		// URI either from net
		Uri video = Uri.parse(uriPath);
		// videoView.setMediaController(mediaController);
		videoView.setVideoURI(video);
		videoView.start();

		videoView.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		startActivity(new Intent(Splash.this, MainActivity.class));
	}

}
