package com.snapdragons.teleguide;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends Activity implements OnClickListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		init();
	}

	Button login, cancel, register;
	TextView email, pass;
	//private static String status;
	
	private void init() {
		login = (Button)findViewById(R.id.btnlogin);
		login.setOnClickListener(this);
		
		email = (TextView)findViewById(R.id.txt_loginEmail);
		pass =(TextView)findViewById(R.id.txt_loginPass);
		
		register=(Button)findViewById(R.id.btn_register);
		register.setOnClickListener(this);
		
		cancel = (Button)findViewById(R.id.btncancel);
		cancel.setOnClickListener(this);
	}

	public void test(){
		startActivity(new Intent(Login.this, MainActivity.class));
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btnlogin:
			String username = email.getText().toString();
			String password = pass.getText().toString();
			new loginActivity(this, Login.this).execute(username, password);
			
			break;
		case R.id.btn_register:
			startActivity(new Intent(Login.this, Register.class));
			break;
		case R.id.btncancel:
			finish();
			break;
		
		}
	}

}
