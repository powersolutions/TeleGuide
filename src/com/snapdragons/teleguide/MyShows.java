package com.snapdragons.teleguide;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;

public class MyShows extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.myshows);
		grid = (GridView) findViewById(R.id.grid_myshows);
		image = (ImageView) findViewById(R.id.image_myshows);
		image.setVisibility(View.VISIBLE);
		
		init();
	}
	ImageView image;
	GridView grid;
	
	private void init(){
		String uid = ((MyApplication) this.getApplication()).getuid();
		new MyShowActivity(this, this, image, grid).execute(uid);
	}
}
