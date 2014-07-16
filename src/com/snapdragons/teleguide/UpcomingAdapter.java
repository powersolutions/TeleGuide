package com.snapdragons.teleguide;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class UpcomingAdapter extends BaseAdapter {

	private Context context;
	private final ArrayList<String> todayValues;
	private final ArrayList<String> link;

	/*public TodayAdapter(Context context, String[] todayValues, String[] link) {
		this.context = context;
		this.todayValues = todayValues;
		this.link = link;
	}*/
	public UpcomingAdapter(Context context, ArrayList<String> todayValues, ArrayList<String> link) {
		this.context = context;
		this.todayValues = todayValues;
		this.link = link;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return todayValues.size();
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
			gridview = inflater.inflate(R.layout.upcomingitem, null);

			// set value into text view
			TextView textview = (TextView) gridview
					.findViewById(R.id.grid_item_label_upcoming);

			textview.setText(todayValues.get(position));

			// set image
			ImageView image = (ImageView) gridview
					.findViewById(R.id.grid_item_image_upcoming);

			//String today = todayValues.get(position);
			String link1 = link.get(position);
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
