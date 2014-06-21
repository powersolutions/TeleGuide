package com.snapdragons.teleguide;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.snapdragons.teleguide.TodayAdapter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.media.MediaRouter.VolumeCallback;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
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

	// local variables
	GridView grid;
	String xml;
	Document doc = null;

	static final List<String> temp = new ArrayList<String>();
	static final List<String> templ = new ArrayList<String>();

	public static Boolean state = false;

	static final String[] test = new String[] { "Android", "iOS" };
	static final String[] test1 = new String[] {
			"http://i.stack.imgur.com/aZkGv.jpg",
			"http://i.stack.imgur.com/aZkGv.jpg" };

	private void init() {
		grid = (GridView) findViewById(R.id.gridView1);

		try {

			// read the xml from the web

			 readXml();

			// convert string lists to string arrays

			String[] itemArray1 = new String[temp.size()];
			String[] returnedArray1 = temp.toArray(itemArray1);
			String[] itemArray2 = new String[templ.size()];
			String[] returnedArray2 = templ.toArray(itemArray2);

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

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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
		// get data from web
		/*
		 * try { // defaultHttpClient DefaultHttpClient httpClient = new
		 * DefaultHttpClient(); HttpPost httpPost = new HttpPost(
		 * "http://sharkz91.0fees.us/tele/shows.xml");
		 * 
		 * HttpResponse httpResponse = httpClient.execute(httpPost); HttpEntity
		 * httpEntity = httpResponse.getEntity(); xml =
		 * EntityUtils.toString(httpEntity);
		 * 
		 * } catch (UnsupportedEncodingException e) { e.printStackTrace(); }
		 * catch (ClientProtocolException e) { e.printStackTrace(); } catch
		 * (IOException e) { e.printStackTrace(); }
		 */
		// create the local xml file
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

				temp.add(elem.getElementsByTagName("name").item(0)
						.getTextContent());
				templ.add(elem.getElementsByTagName("link").item(0)
						.getTextContent());
			}
		}
	}

}
