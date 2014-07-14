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
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

public class RegisterActivity extends AsyncTask<String, Void, String> {

	private Context context;
	private Activity activity;
	private ProgressDialog pd;

	public RegisterActivity(Context context, Activity activity) {
		this.activity = activity;
	}

	protected void onPreExecute() {
		pd = ProgressDialog.show(activity, "Registering...",
				"Please wait while we are registering you...");
	}

	@Override
	protected String doInBackground(String... arg0) {
		// TODO Auto-generated method stub
		try {
			String email = (String) arg0[0];
			String password = (String) arg0[1];
			// String link =
			// "http://192.168.40.36:2345/cs/dbandroid.php?username="
			// +username+"&password="+password;
			String link = "http://sharkz91.0fees.us/tele/reg.php?email='"
					+ email + "'&pass='" + password + "'";
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
		pd.cancel();
		// Toast.makeText(activity, result, Toast.LENGTH_SHORT).show();
		if (result.equals("true")) {
			activity.startActivity(new Intent(activity, Login.class));
			activity.finish();
		} else {

			Toast.makeText(activity, "This email already have an account",
					Toast.LENGTH_SHORT).show();
		}
	}
}
