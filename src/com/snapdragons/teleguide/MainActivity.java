package com.snapdragons.teleguide;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
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
	}

