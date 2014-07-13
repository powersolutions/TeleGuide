package com.snapdragons.teleguide;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class Popular extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.popular);
		grid = (GridView) findViewById(R.id.popularGrid);
		image = (ImageView) findViewById(R.id.imageView1);
		image.setVisibility(View.VISIBLE);
		//grid.setVisibility(View.GONE);
		init();
	}

	ImageView image;
	GridView grid;
	String xml;
	Document doc = null;

	static final List<String> text = new ArrayList<String>();
	static final List<String> img = new ArrayList<String>();

	public static Boolean state = false;

	private void init() {
		if (text.size() == 0 && img.size() == 0) {
			try {

				// read the xml from the web

				readXml();

				// convert string lists to string arrays
				loadDate();

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {

			loadDate();
		}

	}

	private void loadDate() {

		image.setVisibility(View.GONE);
		grid.setVisibility(View.VISIBLE);

		String[] itemArray1 = new String[text.size()];
		String[] returnedArray1 = text.toArray(itemArray1);
		String[] itemArray2 = new String[img.size()];
		String[] returnedArray2 = img.toArray(itemArray2);

		grid.setAdapter(new TodayAdapter(this, returnedArray1, returnedArray2));
		grid.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "Clicked",
						Toast.LENGTH_LONG).show();

			}
		});
	}

	private Boolean checkcon() {

		Thread timer = new Thread() {
			public void run() {
				try {
					Boolean stat = checkInternet();
					if (stat == true) {

						InetAddress testHost = InetAddress
								.getByName("https://www.google.lk/?gws_rd=cr,ssl&ei=Ew-lU5zXO8eeugSa8ILYBg");
						Boolean testState = testHost.isReachable(1000);
						if (testState == true) {
							state = true;

						}

					} else {
						state = false;
					}

				} catch (Exception e) {
					e.printStackTrace();
				} finally {

				}
			}
		};
		timer.start();

		// loaidng image to the imageview by link

		return state;
	}

	private Boolean checkInternet() {
		ConnectivityManager con = (ConnectivityManager) getApplicationContext()
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (con != null) {
			NetworkInfo[] info = con.getAllNetworkInfo();
			if (info != null) {
				for (int i = 0; i < info.length; i++) {
					if (info[i].getState() == NetworkInfo.State.CONNECTED) {
						return true;
					}
				}
			}
		}
		return false;
	}

	private void getXml() {

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {

			DocumentBuilder db = dbf.newDocumentBuilder();

			URL url = new URL("http://sharkz91.0fees.us/tele/shows.xml");
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
			}
		}
	}
}
