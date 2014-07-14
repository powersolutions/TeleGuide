package com.snapdragons.teleguide;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
		
		email=(TextView)findViewById(R.id.txt_reg_email);
		pass = (TextView)findViewById(R.id.txt_reg_pass);
		repass =(TextView)findViewById(R.id.txt_reg_repass);
	}

	Boolean checkPass(){
		if(pass.getText().toString().equals(repass.getText().toString())){
			return true;
		}else
			return false;
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btnCancel:
			startActivity(new Intent(Register.this, Login.class));
			this.finish();
			break;
		case R.id.btnRegister:
			if(checkPass().equals(true)){
				String uname = email.getText().toString();
				String password = pass.getText().toString();
				new RegisterActivity(this, Register.this).execute(uname, password);
			}else
				Toast.makeText(this, "Passwords don't match", Toast.LENGTH_SHORT).show();
			break;
		}
	}

}
