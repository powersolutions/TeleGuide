package com.snapdragons.teleguide;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class Show extends Activity {

	String showName, showId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.show);

		Bundle gotBasket = getIntent().getExtras();
		showName = gotBasket.getString("key");
		showId=gotBasket.getString("id");

		init();
	}

	TextView title, etitle, date, time, channel;
	ListView list;

	List<String> li = new ArrayList<String>();

	private void init() {
		// TODO Auto-generated method stub
		title = (TextView) findViewById(R.id.txt_showTitle);
		title.setText(showName);
		list = (ListView) findViewById(R.id.listView1);
		etitle = (TextView) findViewById(R.id.txt_epiTitle);
		date=(TextView)findViewById(R.id.txt_showAirDate);
		time=(TextView)findViewById(R.id.txt_showAirTime);
		channel=(TextView)findViewById(R.id.txt_showChannel);

		new ShowActivity(this, this, list, etitle,date, time, channel).execute(showId);
	}

}
