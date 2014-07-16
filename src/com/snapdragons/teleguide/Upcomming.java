package com.snapdragons.teleguide;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;

public class Upcomming extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.upcomming);
		grid = (GridView) findViewById(R.id.grid_upcoming);
		image = (ImageView) findViewById(R.id.imag_upcoming);
		image.setVisibility(View.VISIBLE);
		init();
	}
	ImageView image;
	GridView grid;
	
	private void init() {

		/*if (text.size() == 0 && img.size() == 0) {
			try {

				// read the xml from the web

				readXml();

				// convert string lists to string arrays
				loadDate();

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {

			loadDate();
		}*/
		String uid=((MyApplication)this.getApplication()).getuid();
		new UpcomingActivity(this, this, image, grid).execute(uid);
	}
}
