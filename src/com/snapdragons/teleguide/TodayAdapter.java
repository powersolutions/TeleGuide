package com.snapdragons.teleguide;

import java.io.IOException;
import java.net.URL;

import android.R.string;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class TodayAdapter extends BaseAdapter {

	private Context context;
	private final String[] todayValues;

	public TodayAdapter(Context context, String[] todayValues) {
		this.context = context;
		this.todayValues = todayValues;
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

			String today = todayValues[position];

			Bitmap bmp;
			String link1 = "http://a3.mzstatic.com/us/r30/Purple4/v4/64/98/14/6498149a-8ef3-4953-5949-793592900081/icon_256.png";
			try {

				if (today.equals("Show1")) {
					URL url = new URL(link1);
					bmp = BitmapFactory.decodeStream(url.openConnection()
							.getInputStream());
					image.setImageBitmap(bmp);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			gridview = (View) convertView;
		}
		return gridview;
	}

}
