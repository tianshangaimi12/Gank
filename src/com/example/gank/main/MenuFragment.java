package com.example.gank.main;

import com.example.gank.widget.MenuItem;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MenuFragment extends Fragment{
	private TextView mTextViewName;
	private Button mButtonExit;
	private MenuItem menuItemQueryAndroid;
	private MenuItem menuItemQueryIos;
	private MenuItem menuItemQueryFontDesign;
	private MenuItem menuItemQueryGoodThing;
	
	@Override
	@Nullable
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view=inflater.inflate(R.layout.fragment_menu, container,false);
		initView(view);
		return view;
	}
	
	public void initView(View view)
	{
		final MainActivity activity=(MainActivity)getActivity();
		mTextViewName=(TextView)view.findViewById(R.id.txt_menufragment_name);
		Intent intent=getActivity().getIntent();
		final String name=intent.getStringExtra("name");
		if(!TextUtils.isEmpty(name))
		{
			mTextViewName.setText(name);
		}
		else {
			mTextViewName.setText(getResources().getString(R.string.noname_txt));
		}
		mButtonExit=(Button)view.findViewById(R.id.btn_return_login);
		mButtonExit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(getActivity(),LoginActivity.class);
				startActivity(intent);
				getActivity().finish();
			}
		});
		menuItemQueryAndroid=(MenuItem)view.findViewById(R.id.menu_query_android);
		menuItemQueryAndroid.setText("Android");
		menuItemQueryAndroid.setImg(R.drawable.android);
		menuItemQueryAndroid.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(TextUtils.isEmpty(name))
				{
					Toast.makeText(getActivity(), getResources().getString(R.string.no_permission_toast), Toast.LENGTH_SHORT).show();
				}
				else {
					activity.showTypeNewsFragment(TypeNewsFragment.ANDROID);
				}
			}
		});
		menuItemQueryIos=(MenuItem)view.findViewById(R.id.menu_query_ios);
		menuItemQueryIos.setText("Ios");
		menuItemQueryIos.setImg(R.drawable.ios);
		menuItemQueryIos.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(TextUtils.isEmpty(name))
				{
					Toast.makeText(getActivity(), getResources().getString(R.string.no_permission_toast), Toast.LENGTH_SHORT).show();
				}
				else {
					activity.showTypeNewsFragment(TypeNewsFragment.IOS);
				}
			}
		});
		menuItemQueryFontDesign=(MenuItem)view.findViewById(R.id.menu_query_fontdesign);
		menuItemQueryFontDesign.setText(getResources().getString(R.string.fontdesign));
		menuItemQueryFontDesign.setImg(R.drawable.net);
		menuItemQueryFontDesign.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(TextUtils.isEmpty(name))
				{
					Toast.makeText(getActivity(), getResources().getString(R.string.no_permission_toast), Toast.LENGTH_SHORT).show();
				}
				else {
					activity.showTypeNewsFragment(TypeNewsFragment.FONTDESIGN);
				}
			}
		});
		menuItemQueryGoodThing=(MenuItem)view.findViewById(R.id.menu_query_goodthing);
		menuItemQueryGoodThing.setText(getResources().getString(R.string.goodthings));
		menuItemQueryGoodThing.setImg(R.drawable.beautifullady);
		menuItemQueryGoodThing.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(TextUtils.isEmpty(name))
				{
					Toast.makeText(getActivity(), getResources().getString(R.string.no_permission_toast), Toast.LENGTH_SHORT).show();
				}
				else {
					activity.showTypeNewsFragment(TypeNewsFragment.GOODTHING);
				}
			}
		});
		
	}
}
