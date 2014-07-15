package com.snapdragons.teleguide;

import java.io.IOException;
import java.io.InputStream;
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

import android.R.integer;
import android.app.Activity;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ShowActivity extends AsyncTask<String, Void, ArrayList<String>> {

	private Context context;
	private ArrayList<String> list = new ArrayList<String>();
	private Activity activity;
	private ProgressDialog pd;
	private ListView lv;
	private TextView title, date, time, channel;

	public ShowActivity(Context content, Activity activity, ListView lv,
			TextView title, TextView date, TextView time, TextView channel) {
		this.activity = activity;
		this.context = context;
		this.lv = lv;
		this.title = title;
		this.date = date;
		this.time = time;
		this.channel = channel;
	}

	@Override
	protected ArrayList<String> doInBackground(String... params) {
		String show = params[0];
		// TODO Auto-generated method stub
		ArrayList<String> testList = new ArrayList<String>();
		return testList;
	}

	protected void onPostExecute(final ArrayList<String> result) {
		ArrayAdapter<String> adp = new ArrayAdapter<String>(
				activity.getBaseContext(), R.layout.showlistitems, result);
		lv.setAdapter(adp);
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				title.setText(result.get(position));
			}
		});

	}

	String xml;
	Document doc = null;
	ArrayList<String> text = new ArrayList<String>();
	ArrayList<String> id = new ArrayList<String>();

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

			URL url = new URL("http://sharkz91.0fees.us/tele/popular.php");
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
				id.add(elem.getElementsByTagName("name").item(0)
						.getTextContent());

				/*
				 * img.add(elem.getElementsByTagName("link").item(0)
				 * .getTextContent());
				 */
			}
		}
	}

}
