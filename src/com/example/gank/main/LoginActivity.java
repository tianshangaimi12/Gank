package com.example.gank.main;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity{
	private EditText mEditTextName;
	private EditText mEditTextPwd;
	private Button mButtonLogin;
	private TextView mTextViewRegister;
	private TextView mTextViewLogin;
	private CheckBox mCheckBoxRemember;
	private ImageButton mImageButtonDeleteName;
	private ImageButton mImageButtonDeletePwd;
	private String name;
	private String pwd;
	private boolean isRemember;
	private SharedPreferences sharedPreferences;
	private SharedPreferences.Editor editor;
	
	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_login);
		initData();
		initView();
	}
	
	public void initData()
	{
		sharedPreferences=getSharedPreferences("user_info", MODE_PRIVATE);
		editor=sharedPreferences.edit();
		name=sharedPreferences.getString("name", "");
		pwd=sharedPreferences.getString("pwd", "");
		isRemember=sharedPreferences.getBoolean("isRemember", false);
	}
	
	public void initView()
	{
		mButtonLogin=(Button)findViewById(R.id.btn_login);
		mButtonLogin.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				name=sharedPreferences.getString("name", "");
				pwd=sharedPreferences.getString("pwd", "");
				String nameString=mEditTextName.getText().toString();
				String pwdString=mEditTextPwd.getText().toString();
				boolean isRememberInfo=mCheckBoxRemember.isChecked();
				if(nameString.equals(name)&&pwdString.equals(pwd))
				{
					editor.putBoolean("isRemember", isRememberInfo);
					editor.commit();
					Intent intent=new Intent(LoginActivity.this,MainActivity.class);
					intent.putExtra("name", name);
					startActivity(intent);
					finish();
				}
				else {
					Toast.makeText(LoginActivity.this, getResources().getString(R.string.nouser_toast), Toast.LENGTH_SHORT).show();
				}
			}
		});
		mImageButtonDeleteName=(ImageButton)findViewById(R.id.imgbtn_delete_name);
		mImageButtonDeleteName.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(!mEditTextName.getText().toString().equals(""))
					mEditTextName.setText("");
			}
		});
		mImageButtonDeletePwd=(ImageButton)findViewById(R.id.imgbtn_delete_pwd);
		mImageButtonDeletePwd.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(!mEditTextPwd.getText().toString().equals(""))
					mEditTextPwd.setText("");
			}
		});
		mEditTextName=(EditText)findViewById(R.id.edt_name);
		mEditTextPwd=(EditText)findViewById(R.id.edt_pwd);
		mCheckBoxRemember=(CheckBox)findViewById(R.id.checkbox_remember);
		if(isRemember)
		{
			mEditTextName.setText(name);
			mEditTextPwd.setText(pwd);
			mCheckBoxRemember.setChecked(true);
		}
		mTextViewRegister=(TextView)findViewById(R.id.txt_register);
		mTextViewRegister.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				AlertDialog.Builder dBuilder=new AlertDialog.Builder(LoginActivity.this);
				View view=getLayoutInflater().inflate(R.layout.dialog_register, null);
				dBuilder.setView(view);
				dBuilder.create();
				final Dialog dialog=dBuilder.show();
				final EditText editTextName=(EditText)view.findViewById(R.id.edt_register_name);
				final EditText editTextPwd=(EditText)view.findViewById(R.id.edt_register_pwd);
				Button buttonRegister=(Button)view.findViewById(R.id.btn_register);
				buttonRegister.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						String name=editTextName.getText().toString();
						String pwd=editTextPwd.getText().toString();
						editor.putString("name", name);
						editor.putString("pwd", pwd);
						editor.commit();
						dialog.dismiss();
					}
				});
			}
		});
		mTextViewLogin=(TextView)findViewById(R.id.txt_guest_login);
		mTextViewLogin.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(LoginActivity.this,MainActivity.class);
				startActivity(intent);
				finish();
			}
		});
	}
	
	
}
