package com.snapdragons.teleguide;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import android.R.string;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.text.InputFilter.LengthFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class TodayAdapter extends BaseAdapter {

	private Context context;
	private final String[] todayValues;
	private final String[] link;

	public TodayAdapter(Context context, String[] todayValues, String[] link) {
		this.context = context;
		this.todayValues = todayValues;
		this.link = link;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return todayValues.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub

		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View gridview;

		if (convertView == null) {
			gridview = new View(context);

			// set layout from todayItem
			gridview = inflater.inflate(R.layout.todayitem, null);

			// set value into text view
			TextView textview = (TextView) gridview
					.findViewById(R.id.grid_item_label);

			textview.setText(todayValues[position]);

			// set image
			ImageView image = (ImageView) gridview
					.findViewById(R.id.grid_item_image);

			//String today = todayValues.get(position);
			String link1 = link[position];
			Bitmap bmp;
			
			try {
				URL url = new URL(link1);
				bmp = BitmapFactory.decodeStream(url.openConnection()
						.getInputStream());
				image.setImageBitmap(bmp);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			gridview = (View) convertView;
		}
		//todayValues.removeAll(todayValues);
		//link.removeAll(link);
		return gridview;
		
	}

}
