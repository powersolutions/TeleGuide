package com.snapdragons.teleguide;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;

public class Trending extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.trending);
		grid = (GridView) findViewById(R.id.grid_trending);
		image = (ImageView) findViewById(R.id.image_trending);
		image.setVisibility(View.VISIBLE);
		init();
	}
	ImageView image;
	GridView grid;
	
	private void init() {

		new PopularActivity(this, this, image, grid).execute();
	}
}
