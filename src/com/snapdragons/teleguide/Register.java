package com.snapdragons.teleguide;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Register extends Activity implements OnClickListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);
		init();
		
	}
	
	Button register, cancel;
	TextView email, pass, repass;

	private void init() {
		// TODO Auto-generated method stub
		register=(Button)findViewById(R.id.btnRegister);
		register.setOnClickListener(this);
		
		cancel=(Button)findViewById(R.id.btnCancel);
		cancel.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btnCancel:
			finish();
			break;
		}
	}

}
