package com.snapdragons.teleguide;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

public class ShowSaveActiviy extends AsyncTask<String, Void, String> {

	Context context;
	Activity activity;

	public ShowSaveActiviy(Context context, Activity activity) {
		this.context = context;
		this.activity = activity;
	}

	@Override
	protected String doInBackground(String... arg0) {
		// TODO Auto-generated method stub
		try {
			String username = (String) arg0[0];
			String showID = (String) arg0[1];
			// String link =
			// "http://192.168.40.36:2345/cs/dbandroid.php?username="
			// +username+"&password="+password;
			String link = "http://sharkz91.0fees.us/tele/addShow.php?show="
					+ showID + "&user=" + username;
			URL url = new URL(link);
			HttpClient client = new DefaultHttpClient();
			HttpGet request = new HttpGet();
			request.setURI(new URI(link));
			HttpResponse response = client.execute(request);
			BufferedReader in = new BufferedReader(new InputStreamReader(
					response.getEntity().getContent()));

			StringBuffer sb = new StringBuffer("");
			String line = "";
			while ((line = in.readLine()) != null) {
				sb.append(line);
				break;
			}
			in.close();
			return sb.toString();
		} catch (Exception e) {
			return new String("Exception: " + e.getMessage());
		}

	}

	@Override
	protected void onPostExecute(String result) {
		// TODO Auto-generated method stub
		if(result.equals("true")){
			Toast.makeText(activity, "Show added", Toast.LENGTH_SHORT).show();
		}else{
			Toast.makeText(activity, "Unable to add the show", Toast.LENGTH_SHORT).show();
		}
	}

}
