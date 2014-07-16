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
		// grid.setVisibility(View.GONE);
		init();
	}

	ImageView image;
	GridView grid;
	String xml;
	Document doc = null;

	static final ArrayList<String> text = new ArrayList<String>();
	static final ArrayList<String> img = new ArrayList<String>();

	public static Boolean state = false;

	private void init() {
		/*
		 * if (text.size() == 0 && img.size() == 0) { try {
		 * 
		 * // read the xml from the web
		 * 
		 * readXml();
		 * 
		 * // convert string lists to string arrays loadDate();
		 * 
		 * } catch (Exception e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } } else {
		 * 
		 * loadDate(); }
		 */

		new PopularActivity(this, this, image, grid).execute();
	}

}
