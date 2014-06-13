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


public class MainActivity extends TabActivity {
	TabHost mTabHost;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mTabHost=getTabHost();
		
		
		TabSpec firstSpec=mTabHost.newTabSpec("First");
		firstSpec.setIndicator("Today Shows", null);
		Intent firstIntent=new Intent(MainActivity.this,FirstActivity.class);
		firstSpec.setContent(firstIntent);
		
		TabSpec secondSpec=mTabHost.newTabSpec("Second");
		secondSpec.setIndicator("Upcomming", null);
		Intent secondIntent=new Intent(MainActivity.this,SecondActivity.class);
		secondSpec.setContent(secondIntent);
		
		TabSpec thirdSpec=mTabHost.newTabSpec("Third");
		thirdSpec.setIndicator("Recent", null);
		Intent thirdIntent=new Intent(MainActivity.this,ThirdActivity.class);
		thirdSpec.setContent(thirdIntent);
		
		
		mTabHost.addTab(firstSpec);
		mTabHost.addTab(secondSpec);
		mTabHost.addTab(thirdSpec);
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
				Intent i = new Intent("com.example.protest.MYSHOWS");
				startActivity(i);
				
				break;
			case R.id.searchshows:
				Intent p = new Intent("com.example.protest.SEARCHSHOWS");
				startActivity(p);
				break;
				
			case R.id.viewshows:
				startActivity(new Intent(MainActivity.this, ViewShows.class));
				break;
				
				
			}
			return false;
		}	
	
        }
	

