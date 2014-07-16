package com.snapdragons.teleguide;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class TodayActivity extends AsyncTask<String, Void, ArrayList<String>> {

	private Context context;
	private Activity activity;
	private ImageView image;
	private GridView grid;
	private ProgressDialog pd;
	private String uid;

	public TodayActivity(Context context, Activity activity, ImageView image,
			GridView grid) {
		this.context = context;
		this.activity = activity;
		this.image = image;
		this.grid = grid;
		
	}

	protected void onPreExecute() {
		pd = ProgressDialog.show(activity, "Loading...", "");
	}

	@Override
	protected ArrayList<String> doInBackground(String... params) {
		// TODO Auto-generated method stub
		uid=params[0];
		try {
			readXml();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected void onPostExecute(ArrayList<String> result) {
		// TODO Auto-generated method stub
		try {
			loadDate();
			pd.cancel();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	String xml;
	Document doc = null;

	ArrayList<String> text = new ArrayList<String>();
	ArrayList<String> img = new ArrayList<String>();
	ArrayList<String> showID = new ArrayList<String>();

	private void loadDate() {

		image.setVisibility(View.GONE);
		grid.setVisibility(View.VISIBLE);

		grid.setAdapter(new TodayAdapter(activity, text, img));
		/*grid.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				// Toast.makeText(getApplicationContext(),
				// itemArray1[position].toString(),
				// Toast.LENGTH_LONG).show();
				// startActivity(new Intent(TodayShow.this, Show.class));

				String showName = text.get(position).toString();
				// Toast.makeText(getApplicationContext(), showID.get(position),
				// Toast.LENGTH_SHORT).show();
				Bundle basket = new Bundle();
				basket.putString("key", showName);
				basket.putString("id", showID.get(position));
				Intent a = new Intent(activity, Show.class);
				a.putExtras(basket);
				// startActivity(a);
				activity.startActivityForResult(a, 0);
			}
		});*/
	}

	private void readXml() {
		getXml();
		doc.getDocumentElement();

		NodeList list = doc.getElementsByTagName("show");

		for (int i = 0; i < list.getLength(); i++) {
			Node node = list.item(i);
			if (node.getNodeType() == node.ELEMENT_NODE) {
				Element elem = (Element) node;

				text.add(elem.getElementsByTagName("name").item(0)
						.getTextContent());
				img.add(elem.getElementsByTagName("link").item(0)
						.getTextContent());
				showID.add(elem.getElementsByTagName("id").item(0)
						.getTextContent());

			}
		}
	}

	private void getXml() {
		// get data from web

		// create the local xml file
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {

			DocumentBuilder db = dbf.newDocumentBuilder();
			
			URL url = new URL("http://sharkz91.0fees.us/tele/today.php?id="+uid);
			InputStream stream = url.openStream();
			// doc = docBuilder.parse(stream);
			// InputSource is = new InputSource();
			// is.setCharacterStream(new StringReader(xml));
			doc = db.parse(stream);

		} catch (ParserConfigurationException e) {
			Log.e("Error: ", e.getMessage());
			// return null;
		} catch (SAXException e) {
			Log.e("Error: ", e.getMessage());
			// return null;
		} catch (IOException e) {
			Log.e("Error: ", e.getMessage());
			// return null;
		}

	}
}
