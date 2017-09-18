package com.example.gank.widget;

import com.example.gank.main.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MenuItem extends RelativeLayout{

	private TextView mTextView;
	public MenuItem(Context context, AttributeSet attrs) {
		super(context, attrs);
		LayoutInflater.from(context).inflate(R.layout.menu_item, this);
		mTextView=(TextView)findViewById(R.id.txt_menufragment_item);
		//mTextView.setText(getResources().getString(R.string.query_bytype_menu));
	}
	
	public void setText(String text)
	{
		mTextView.setText(text);
	}
	
	
}
