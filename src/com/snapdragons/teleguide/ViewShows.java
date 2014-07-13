package com.snapdragons.teleguide;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.os.Build;

public class ViewShows extends TabActivity {
	TabHost mTabHost;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.viewshows);
		mTabHost = getTabHost();

		TabSpec firstSpec = mTabHost.newTabSpec("First");
		firstSpec.setIndicator("Trending", null);
		Intent firstIntent = new Intent(this, Trending.class);
		firstSpec.setContent(firstIntent);

		TabSpec secondSpec = mTabHost.newTabSpec("Second");
		secondSpec.setIndicator("Popular", null);
		Intent secondIntent = new Intent(this, Popular.class);
		secondSpec.setContent(secondIntent);

		mTabHost.addTab(firstSpec);
		mTabHost.addTab(secondSpec);

	}
	
	@Override
	public boolean onCreateOptionsMenu(android.view.Menu menu) {
		// TODO Auto-generated method stub
		super.onCreateOptionsMenu(menu);
		MenuInflater blowUp = getMenuInflater();
		blowUp.inflate(R.menu.main12, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {

		case R.id.myshows:
			startActivity(new Intent(ViewShows.this, MyShows.class));

			break;
		case R.id.searchshows:
			startActivity(new Intent(ViewShows.this, SearchShows.class));
			break;

		case R.id.viewshows:
			startActivity(new Intent(ViewShows.this, ViewShows.class));
			break;
		case R.id.reload:
			
			finish();
			startActivity(getIntent());
			break;

		}
		return false;
	}
}
