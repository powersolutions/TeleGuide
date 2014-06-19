package com.snapdragons.teleguide;

import com.snapdragons.teleguide.TodayAdapter;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

public class TodayShow extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.today);
		init();
	}
	
	GridView grid;
	static final String[] name = new String[]{"test"};
	private void init(){
		grid = (GridView)findViewById(R.id.gridView1);
		grid.setAdapter(new TodayAdapter(this,name));
		
		grid.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "Clicked", Toast.LENGTH_LONG).show();
				
			}
		});
	}
}
